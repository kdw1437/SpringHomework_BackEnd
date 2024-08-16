package com.juro.study.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.juro.study.config.TestConfig;
import com.juro.study.model.Post;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@Commit
public class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Test
    public void testCreateAndGetPost() {
        Post newPost = new Post();
        newPost.setTitle("Post 테스트");
        newPost.setContent("testCreateAndGetPost 테스트입니다.");
        newPost.setAuthor("김동욱");

        postService.createPost(newPost);

        Post retrievedPost = postService.getPostById(newPost.getPostId());
        
        System.out.println("postId : " + retrievedPost.getPostId());
        System.out.println("Title : " + retrievedPost.getTitle());
        System.out.println("Author : " + retrievedPost.getAuthor());
        System.out.println("Content : " + retrievedPost.getContent());
       
    }

    @Test
    public void testGetAllPosts() {
        // Post를 위한 데이터 생성
//        for (int i = 0; i < 5; i++) {
//            Post post = new Post();
//            post.setTitle("Test Post " + i);
//            post.setContent("Content " + i);
//            post.setAuthor("Author " + i);
//            postService.createPost(post);
//        }

        List<Post> posts = postService.getAllPosts(1, 10);
        
        for (Post result : posts) {
        	System.out.println("postId : " + result.getPostId());
        	System.out.println("Title : " + result.getTitle());
        	System.out.println("Author : " + result.getContent());
        	System.out.println("Content : " + result.getAuthor());
        	System.out.println("------------------------------------");
        }
    }

    @Test
    public void testUpdatePost() {
        Post post = new Post();
        post.setTitle("Original Title");
        post.setContent("Original Content");
        post.setAuthor("Original Author");
        postService.createPost(post);

        post.setTitle("Updated Title");
        post.setContent("Updated Content");
        postService.updatePost(post);

        Post updatedPost = postService.getPostById(post.getPostId());
        assertEquals("Updated Title", updatedPost.getTitle(), "Title should be updated");
        assertEquals("Updated Content", updatedPost.getContent(), "Content should be updated");
    }

    @Test
    public void testDeletePost() {
        Post post = new Post();
        post.setTitle("Post to Delete");
        post.setContent("This post will be deleted");
        post.setAuthor("Delete Author");
        postService.createPost(post);

        Long postId = post.getPostId();
        postService.deletePost(postId);

        Post deletedPost = postService.getPostById(postId);
        assertNull(deletedPost, "Post should be null after deletion");
    }

    @Test
    public void testGetTotalPostCount() {
        int initialCount = postService.getTotalPostCount();

        // Create some test posts
        for (int i = 0; i < 5; i++) {
            Post post = new Post();
            post.setTitle("Test Post " + i);
            post.setContent("Content " + i);
            post.setAuthor("Author " + i);
            postService.createPost(post);
        }

        int newCount = postService.getTotalPostCount();
        System.out.println("증감 전 객체 수: " + initialCount);
        System.out.println("증감 후 객체 수: " + newCount);
    }
}