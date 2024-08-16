package com.juro.study.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.juro.study.config.TestConfig;
import com.juro.study.model.Post;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class PostMapperTest {

	@Autowired
	private PostMapper postMapper;

	@Test
	@Transactional
	@Commit
	public void testInsertPost() {
		Post newPost = createTestPost();
		System.out.println("insert 전: " + newPost);

		postMapper.insertPost(newPost);
		System.out.println("insert 후: " + newPost);

		Post insertedPost = postMapper.getPostById(newPost.getPostId());
		System.out.println("Retrieved post: " + insertedPost);

	}

	private Post createTestPost() {
		Post newPost = new Post();
		newPost.setTitle("post 인서트 테스트");
		newPost.setContent("테스트 콘텐츠");
		newPost.setAuthor("테스트 저자");
		return newPost;
	}

	// Other test methods...
}