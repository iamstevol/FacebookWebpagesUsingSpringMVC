package com.iamstevol.facebookCloneSpringMVC.service.impl;

import com.iamstevol.facebookCloneSpringMVC.entity.Comment;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.repository.CommentRepository;
import com.iamstevol.facebookCloneSpringMVC.repository.PostRepository;
import com.iamstevol.facebookCloneSpringMVC.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findCommentByCommentId(id);
    }

    @Override
    public void updateComment(Comment editComment) {
        commentRepository.save(editComment);

    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentByPost(Post post) {
        return commentRepository.findCommentByPost(post);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteCommentByCommentId(commentId);
    }

    @Override
    @Transactional
    public void deleteAllCommentsInPost(Post post) {
        commentRepository.deleteAllByPost(post);
    }
}
