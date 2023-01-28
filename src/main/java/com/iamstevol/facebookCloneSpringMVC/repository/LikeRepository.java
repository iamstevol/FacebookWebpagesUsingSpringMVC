package com.iamstevol.facebookCloneSpringMVC.repository;

import com.iamstevol.facebookCloneSpringMVC.entity.Like;
import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Transactional
    void deleteLikeByPostAndUser(Post post, User user);

    List<Like> findAllByPost(Post post);

    void deleteAllByPost(Post post);
}
