package com.juro.study.service;

import com.juro.study.model.Post;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts(int page, int size);
    Post getPostById(Long id);
    void createPost(Post post);
    void updatePost(Post post);
    void deletePost(Long id);
    int getTotalPostCount();
}