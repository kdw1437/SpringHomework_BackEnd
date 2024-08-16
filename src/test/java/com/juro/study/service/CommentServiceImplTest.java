package com.juro.study.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.juro.study.config.TestConfig;
import com.juro.study.model.Comment;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@Commit
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testCreateAndGetComment() {
        // 새로운 Comment 만들기
        Comment newComment = new Comment();
        newComment.setPostId(5L);
        newComment.setContent("서비스 레이어 테스트");
        newComment.setAuthor("김동욱");

        // Comment 만들기
        commentService.createComment(newComment);

        // comment 가져오기
        Comment retrievedComment = commentService.getCommentById(newComment.getCommentId());

        // 결과 확인
        System.out.println("Created and Retrieved Comment:");
        System.out.println("Comment ID: " + retrievedComment.getCommentId());
        System.out.println("Post ID: " + retrievedComment.getPostId());
        System.out.println("Content: " + retrievedComment.getContent());
        System.out.println("Author: " + retrievedComment.getAuthor());
    }

    @Test
    public void testGetCommentsByPostId() {
        
        List<Comment> comments = commentService.getCommentsByPostId(5L);

        System.out.println("Post ID 5에 대한 comment:");
        for (Comment comment : comments) {
            System.out.println("Comment ID: " + comment.getCommentId());
            System.out.println("Content: " + comment.getContent());
            System.out.println("Author: " + comment.getAuthor());
            System.out.println("--------------------");
        }
    }

    @Test
    public void testUpdateComment() {
        // ID 2 comment가 존재한다.
        Comment comment = commentService.getCommentById(2L);
        
        String updatedContent = "내용 업데이트 테스트";
        comment.setContent(updatedContent);

        commentService.updateComment(comment);

        Comment updatedComment = commentService.getCommentById(2L);
       
        System.out.println("업데이트된 Comment:");
        System.out.println("Comment ID: " + updatedComment.getCommentId());
        System.out.println("업데이트된 Content: " + updatedComment.getContent());
    }

    @Test
    public void testDeleteComment() {
        // 삭제될 커멘트 row 입력값
        Comment commentToDelete = new Comment();
        commentToDelete.setPostId(5L);
        commentToDelete.setContent("삭제될 커멘트");
        commentToDelete.setAuthor("김동욱");

        commentService.createComment(commentToDelete);
        
        
        Long commentId = commentToDelete.getCommentId();
        
        System.out.println("삭제될 commentId : " + commentId );
        // comment 삭제
        commentService.deleteComment(commentId);

        }
}