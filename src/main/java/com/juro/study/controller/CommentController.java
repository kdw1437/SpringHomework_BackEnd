package com.juro.study.controller;

import com.juro.study.model.Comment;
import com.juro.study.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}")
    public String getCommentsByPostId(@PathVariable Long postId, Model model) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        model.addAttribute("comments", comments);
        model.addAttribute("postId", postId);
        return "commentList";
    }

    @PostMapping("/post/{postId}")
    public String createComment(@PathVariable Long postId, @ModelAttribute Comment comment, HttpSession session) {
        String author = (String) session.getAttribute("loggedInUser");
        if (author == null) {
            return "redirect:/user/login";
        }
        comment.setPostId(postId);
        comment.setAuthor(author);
        commentService.createComment(comment);
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/{id}/delete")
    public String deleteComment(@PathVariable Long id, @RequestParam Long postId, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        Comment comment = commentService.getCommentById(id);
        if (loggedInUser == null || !loggedInUser.equals(comment.getAuthor())) {
            return "redirect:/user/login";
        }
        commentService.deleteComment(id);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/edit/{id}")
    public String editCommentForm(@PathVariable Long id, Model model, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        Comment comment = commentService.getCommentById(id);
        if (loggedInUser == null || !loggedInUser.equals(comment.getAuthor())) {
            return "redirect:/user/login";
        }
        model.addAttribute("comment", comment);
        return "commentForm";
    }

    @PostMapping("/edit/{id}")
    public String updateComment(@PathVariable Long id, @ModelAttribute Comment comment, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        Comment existingComment = commentService.getCommentById(id);
        if (loggedInUser == null || !loggedInUser.equals(existingComment.getAuthor())) {
            return "redirect:/user/login";
        }
        comment.setCommentId(id);
        comment.setAuthor(loggedInUser);
        comment.setPostId(existingComment.getPostId()); // Ensure postId is set
        commentService.updateComment(comment);
        return "redirect:/posts/" + comment.getPostId();
    }
}