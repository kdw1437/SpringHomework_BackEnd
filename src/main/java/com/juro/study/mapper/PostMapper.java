package com.juro.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.juro.study.model.Post;
@Mapper
public interface PostMapper {
    List<Post> getAllPosts(@Param("offset") int offset, @Param("limit") int limit);
    Post getPostById(Long id);
    void insertPost(Post post);
    void updatePost(Post post);
    void deletePost(Long id);
    int getTotalPostCount();
}