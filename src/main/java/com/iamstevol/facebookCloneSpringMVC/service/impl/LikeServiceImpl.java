package com.iamstevol.facebookCloneSpringMVC.service.impl;

import com.iamstevol.facebookCloneSpringMVC.entity.Like;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import com.iamstevol.facebookCloneSpringMVC.repository.LikeRepository;
import com.iamstevol.facebookCloneSpringMVC.repository.PostRepository;
import com.iamstevol.facebookCloneSpringMVC.service.LikeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;
    private PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }
    @Override
    public boolean likePost(User user, Long postId, String action) {
        boolean result = false;
        Post post = postRepository.findByPostId(postId);
        try{
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            if(action.equals("1")) {
                likeRepository.save(like);
                System.out.println("save");
            } else {
                likeRepository.deleteLikeByPostAndUser(post, user);
                System.out.println("delete");
            }
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteAllLikesInPost(Post post) {
        likeRepository.deleteAllByPost(post);
    }
}
