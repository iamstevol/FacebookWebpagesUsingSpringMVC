package com.iamstevol.facebookCloneSpringMVC.service;

import com.iamstevol.facebookCloneSpringMVC.entity.Comment;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;

import java.util.List;

public interface CommentService {

    Comment getCommentById(Long id);
    void updateComment(Comment editComment);
    void saveComment(Comment comment);
    List<Comment> findCommentByPost(Post post);
    void deleteComment(Long commentId);
    void deleteAllCommentsInPost(Post post);
}
