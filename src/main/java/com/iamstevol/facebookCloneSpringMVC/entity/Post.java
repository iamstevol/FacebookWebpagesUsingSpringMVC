package com.iamstevol.facebookCloneSpringMVC.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> listOfComments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
}
