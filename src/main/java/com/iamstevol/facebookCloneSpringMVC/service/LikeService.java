package com.iamstevol.facebookCloneSpringMVC.service;

import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.entity.User;

public interface LikeService {

    boolean likePost(User user, Long postId, String action);
    void deleteAllLikesInPost(Post post);
}
