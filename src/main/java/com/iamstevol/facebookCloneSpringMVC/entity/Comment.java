package com.iamstevol.facebookCloneSpringMVC.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentBody;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String comments;

    public Comment(String comment, Post post, User user){
        this.commentBody = comment;
        this.post = post;
        this.user = user;
    }

    public Comment(Long commentId, String comment) {
        this.commentId = commentId;
        this.commentBody = comment;
    }
}
