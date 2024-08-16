package com.juro.study.mapper;

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
import com.juro.study.model.Comment;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    @Transactional
    @Commit
    public void testInsertAndGetComment() {
        // 새로운 Comment를 만든다.
        Comment newComment = new Comment();
        newComment.setPostId(6L);  // postID가 5인 경우
        newComment.setParentCommentId(1L);  // 첫번째 comment인 경우, parentCommentId가 없으므로 null을 입력한다.
        newComment.setContent("테스트 커멘트 입니다.");
        newComment.setAuthor("테스트 저자 1");

        // comment를 insert한다.
        commentMapper.insertComment(newComment);

        // insertion 후에 commentId가 null이 아닌지 확인한다.
        assertNotNull(newComment.getCommentId(), "Comment ID가 null이 아닌지 확인한다.");

        // post에 대한 comment를 retrieve한다.
        List<Comment> comments = commentMapper.getCommentsByPostId(5L);

        // list가 비었는지 확인한다.
        assertFalse(comments.isEmpty(), "Comment list는 comment를 가지고 있어야 한다.");
        
        System.out.println("comment 내용:");
        for (Comment comment : comments) {
            System.out.println(comment);
        }

        // insert된 것과 retrieve된 것이 일치하는지 확인한다.
        Comment retrievedComment = comments.get(0);
        assertEquals(newComment.getContent(), retrievedComment.getContent());
        assertEquals(newComment.getAuthor(), retrievedComment.getAuthor());
        
    }
}