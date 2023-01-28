package com.iamstevol.facebookCloneSpringMVC.controller;

import com.iamstevol.facebookCloneSpringMVC.dto.ResponseDto;
import com.iamstevol.facebookCloneSpringMVC.entity.Comment;
import com.iamstevol.facebookCloneSpringMVC.entity.Like;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import com.iamstevol.facebookCloneSpringMVC.mapper.LikePosts;
import com.iamstevol.facebookCloneSpringMVC.service.CommentService;
import com.iamstevol.facebookCloneSpringMVC.service.LikeService;
import com.iamstevol.facebookCloneSpringMVC.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class PostController {


        private PostService postService;
        private CommentService commentService;
        private LikeService likeService;

        @Autowired
        public PostController(PostService postService, CommentService commentService, LikeService likeService) {
            this.postService = postService;
            this.commentService = commentService;
            this.likeService = likeService;
        }

        /**
         * Method to get the home page
         * @param request
         * @param model
         * @return
         */
        @GetMapping("/home")
        public String getHomePage(HttpServletRequest request, Model model) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("logUser");
            if (user==null) return "redirect:/";
            model.addAttribute("post", new Post());
            model.addAttribute("newComment", new Comment());
            model.addAttribute("loggedUser", user);
            model.addAttribute("postLikes", new Like());
            model.addAttribute("postDelete", new Post());

            List<LikePosts> listOfPosts = postService.getAllPost(user);

            model.addAttribute("listOfAllPosts", listOfPosts);
            return "home";
        }

        /**
         * Method to get the update post page
         * @param model
         * @param httpSession
         * @param postId
         * @return
         */
        @GetMapping("/updatepost")
        public String getUpdatePostPage(Model model, HttpSession httpSession, Long postId) {
            User user = (User) httpSession.getAttribute("logUser");
            if (user==null) return "redirect:/";

            Post post = postService.getPostById(postId);

            model.addAttribute("editpost", post);
            model.addAttribute("loggedUser", user);

            return "updatepost";
        }


        @PostMapping("/home_post")
        public String createPost(HttpSession httpSession, @ModelAttribute("post") Post post) {
            User user = (User) httpSession.getAttribute("logUser");
            postService.addPost(user, post);
            return "redirect:/home";
        }


        @PostMapping("/update")
        public String updatePost(HttpSession httpSession, Post post){
            Post newPost = postService.getPostById(post.getPostId());
            newPost.setBody(post.getBody());
            postService.updatePost(newPost);
            return "redirect:/home";
        }


        @PostMapping("/delete/{id}")
        public String deletePost(@PathVariable("id") Long id, HttpSession httpSession, RedirectAttributes redirectAttributes) {
            User user = (User) httpSession.getAttribute("logUser");
            ResponseDto response = new ResponseDto();
            if (user == null) {
                return "redirect:/index";
            }
            Post post = postService.getPostById(id);
            commentService.deleteAllCommentsInPost(post);
            likeService.deleteAllLikesInPost(post);
            postService.deletePost(post);
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            return "redirect:/home";
        }

    }

