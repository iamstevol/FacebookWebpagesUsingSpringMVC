package com.iamstevol.facebookCloneSpringMVC.service;

import com.iamstevol.facebookCloneSpringMVC.entity.Post;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import com.iamstevol.facebookCloneSpringMVC.repository.PostRepository;
import com.iamstevol.facebookCloneSpringMVC.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;
    private Post post;
    private User user;

    @BeforeEach
    void setUp() {
        post = Post.builder()
                .postId(1L)
                .title("one")
                .body("kdfn")
                .build();

        user = new User();
        user.setFirstName("user");
        user.setLastName("Decagon");
        user.setEmail("user@gmail.com");
        user.setGender("Male");
        user.setPassword("1234");
    }

    @Test
    public void whenValidId_thenGetPost() {
        Mockito.when(postRepository.findByPostId(1L)).thenReturn(post);
        Post postId = postService.getPostById(1L);
        verify(postRepository, times(1)).findByPostId(1L);
        assertNotNull(postId);
        assertEquals(post, postId);
    }

    @Test
    public void shouldAddPost() {
        when(postRepository.save(any(Post.class))).thenReturn(post);
        postService.addPost(user, post);
        verify(postRepository, times(1)).save(any(Post.class));
        /*
        ArgumentCaptor is a class in the Mockito library. It is used to capture arguments passed to
        a mock object's methods. It allows you to verify that the correct arguments were passed to
        a method and to retrieve the captured argument values for further assertions or other processing.
         */
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postRepository, times(1)).save(postCaptor.capture());
        Post capturedPost = postCaptor.getValue();
        assertNotNull(capturedPost);
        assertEquals(user, capturedPost.getUser());
        assertEquals(post.getTitle(), capturedPost.getTitle());
        assertEquals(post.getBody(), capturedPost.getBody());
    }

    public void shouldUpdatePost() {

    }
}