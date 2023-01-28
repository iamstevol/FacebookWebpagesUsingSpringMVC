package com.iamstevol.facebookCloneSpringMVC.mapper;

import com.iamstevol.facebookCloneSpringMVC.entity.Comment;
import com.iamstevol.facebookCloneSpringMVC.entity.Like;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LikePosts {

    private Long postId;
    private String title;
    private String body;
    private User user;
    private List<Comment> listOfComments = new ArrayList<>();
    private List<Like> postLikes = new ArrayList<>();
    private boolean likedPost;
//    private boolean gelikedPost;
}
