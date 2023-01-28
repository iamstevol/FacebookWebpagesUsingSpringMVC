package com.iamstevol.facebookCloneSpringMVC.service;

import com.iamstevol.facebookCloneSpringMVC.entity.Like;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import com.iamstevol.facebookCloneSpringMVC.mapper.LikePosts;

import java.util.List;

public interface PostService {

    void addPost(User user, Post post);
    List<LikePosts> getAllPost(User user);
    void updatePost(Post post);
    void deletePost(Post post);
    Post getPostById(Long id);
}
