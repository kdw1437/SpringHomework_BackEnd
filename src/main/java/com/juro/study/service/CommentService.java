package com.juro.study.service;

import com.juro.study.model.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPostId(Long postId);
    Comment getCommentById(Long id);
    void createComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long id);
    List<Comment> getChildComments(Long parentCommentId);
}