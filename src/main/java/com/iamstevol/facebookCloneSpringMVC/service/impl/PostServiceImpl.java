package com.iamstevol.facebookCloneSpringMVC.service.impl;

import com.iamstevol.facebookCloneSpringMVC.entity.Comment;
import com.iamstevol.facebookCloneSpringMVC.entity.Like;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import com.iamstevol.facebookCloneSpringMVC.mapper.LikePosts;
import com.iamstevol.facebookCloneSpringMVC.repository.CommentRepository;
import com.iamstevol.facebookCloneSpringMVC.repository.LikeRepository;
import com.iamstevol.facebookCloneSpringMVC.repository.PostRepository;
import com.iamstevol.facebookCloneSpringMVC.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, LikeRepository likeRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }


    @Override
    public void addPost(User user, Post post) {
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public List<LikePosts> getAllPost(User user) {
        List<LikePosts> listOfLikePosts = new ArrayList<>();
        List<Post> listOfPosts = postRepository.findAllByPostIdIsNotNullOrderByPostIdDesc();

        for(Post post : listOfPosts) {

            LikePosts likePost = new LikePosts();

            likePost.setUser(post.getUser());
            likePost.setBody(post.getBody());
            likePost.setPostId(post.getPostId());
            likePost.setTitle(post.getTitle());

            List<Like> listOfLikes = likeRepository.findAllByPost(post);
            List<Comment> listOfComment = commentRepository.findCommentByPost(post);

            System.out.println("lllllllll" + listOfComment);
            likePost.setPostLikes(listOfLikes);

            List<Like> listOfPostLikes = likeRepository.findAllByPost(post);

            for (Like like: listOfPostLikes) {
                System.out.println("wwwwww "+like.getUser().getUserId() );
                System.out.println("uuuuuuu "+user.getUserId());
                if (like.getUser().getUserId().equals(user.getUserId())) {
                    likePost.setLikedPost(true);
                    break;
                }
            }
            System.out.println("tttttttt" + listOfPostLikes);
            listOfLikePosts.add(likePost);
        }
        return listOfLikePosts;
    }

    @Override
    public void updatePost(Post post) {

        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {

        postRepository.delete(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findByPostId(id);
    }
}
