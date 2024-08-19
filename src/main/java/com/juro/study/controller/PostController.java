package com.juro.study.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juro.study.model.Comment;
import com.juro.study.model.Post;
import com.juro.study.service.CommentService;
import com.juro.study.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    public String getAllPosts(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size) {
        List<Post> posts = postService.getAllPosts(page, size);
        int totalPosts = postService.getTotalPostCount();
        int totalPages = (int) Math.ceil((double) totalPosts / size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", size);

        return "postList";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            
            // Fetch comments for the post
            List<Comment> comments = commentService.getCommentsByPostId(id);
            model.addAttribute("comments", comments);
            
            return "postDetail";
        } else {
            return "error";
        }
    }

    @GetMapping("/new")
    public String newPostForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("post", new Post());
        return "postForm";
    }

    @PostMapping
    public String createPost(@ModelAttribute Post post, HttpSession session) {
        String author = (String) session.getAttribute("loggedInUser");
        if (author == null) {
            return "redirect:/user/login";
        }
        post.setAuthor(author);
        postService.createPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        Post post = postService.getPostById(id);
        if (loggedInUser == null || !loggedInUser.equals(post.getAuthor())) {
            return "redirect:/user/login";
        }
        model.addAttribute("post", post);
        return "editPostForm";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        Post existingPost = postService.getPostById(id);
        if (loggedInUser == null || !loggedInUser.equals(existingPost.getAuthor())) {
            return "redirect:/user/login";
        }
        
        // Update the existing post instead of creating a new one
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        // Don't update the author or creation date
        
        postService.updatePost(existingPost);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        Post post = postService.getPostById(id);
        if (loggedInUser == null || !loggedInUser.equals(post.getAuthor())) {
            return "redirect:/user/login";
        }
        postService.deletePost(id);
        return "redirect:/posts";
    }
}