package com.iamstevol.facebookCloneSpringMVC.repository;

import com.iamstevol.facebookCloneSpringMVC.entity.Comment;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentByCommentId(Long id);

    List<Comment> findCommentByPost (Post post);

    void deleteCommentByCommentId(Long commentId);

    void deleteAllByPost(Post post);
}
