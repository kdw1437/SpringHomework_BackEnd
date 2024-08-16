package com.juro.study.service;

import com.juro.study.mapper.CommentMapper;
import com.juro.study.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.getCommentsByPostId(postId);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    public void createComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentMapper.deleteComment(id);
    }

    @Override
    public List<Comment> getChildComments(Long parentCommentId) {
        return commentMapper.getChildComments(parentCommentId);
    }
}