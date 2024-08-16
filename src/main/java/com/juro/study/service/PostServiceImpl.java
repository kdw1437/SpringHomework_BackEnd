package com.juro.study.service;

import com.juro.study.mapper.PostMapper;
import com.juro.study.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> getAllPosts(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.getAllPosts(offset, size);
    }

    @Override
    public Post getPostById(Long id) {
        return postMapper.getPostById(id);
    }

    @Override
    public void createPost(Post post) {
        postMapper.insertPost(post);
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public void deletePost(Long id) {
        postMapper.deletePost(id);
    }

    @Override
    public int getTotalPostCount() {
        return postMapper.getTotalPostCount();
    }
}