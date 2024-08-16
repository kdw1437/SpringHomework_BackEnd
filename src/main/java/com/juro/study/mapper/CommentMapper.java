package com.juro.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juro.study.model.Comment;
@Mapper
public interface CommentMapper {
    List<Comment> getCommentsByPostId(Long postId);
    Comment getCommentById(Long id);
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long id);
    List<Comment> getChildComments(Long parentCommentId);
}