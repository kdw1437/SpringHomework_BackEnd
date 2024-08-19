<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${post.title}</title>
</head>
<body>
	<h1>${post.title}</h1>
	<p>작성자: ${post.author}</p>
	<p>
		작성일:
		<fmt:formatDate value="${post.createdDate}"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</p>
	<p>
		수정일:
		<fmt:formatDate value="${post.updatedDate}"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</p>
	<div>${post.content}</div>

	<c:if test="${sessionScope.loggedInUser eq post.author}">
		<a href="<c:url value='/posts/${post.postId}/edit' />">게시글 수정</a>
		<form action="<c:url value='/posts/${post.postId}/delete' />"
			method="post" style="display: inline;">
			<input type="submit" value="게시글 삭제"
				onclick="return confirm('정말로 이 게시글을 삭제하시겠습니까?');">
		</form>
	</c:if>

	<h2>댓글</h2>
	<c:forEach var="comment" items="${comments}">
		<div>
			<p>${comment.content}</p>
			<p>
				작성자: ${comment.author}, 작성일:
				<fmt:formatDate value="${comment.createdDate}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
			<c:if test="${sessionScope.loggedInUser eq comment.author}">
				<a href="<c:url value='/comments/edit/${comment.commentId}' />">수정</a>
				<form
					action="<c:url value='/comments/${comment.commentId}/delete' />"
					method="post" style="display: inline;">
					<input type="hidden" name="postId" value="${post.postId}">
					<input type="submit" value="삭제"
						onclick="return confirm('정말로 이 댓글을 삭제하시겠습니까?');">
				</form>
			</c:if>
		</div>
	</c:forEach>

	<c:if test="${not empty sessionScope.loggedInUser}">
		<h3>댓글 작성</h3>
		<form action="<c:url value='/comments/post/${post.postId}' />"
			method="post">
			<textarea name="content" required></textarea>
			<br> <input type="submit" value="댓글 작성">
		</form>
	</c:if>
	<c:if test="${empty sessionScope.loggedInUser}">
		<p>
			<a href="<c:url value='/user/login' />">로그인</a> 후 댓글을 작성할 수 있습니다
		</p>
	</c:if>

	<a href="<c:url value='/posts' />">게시글 목록으로 돌아가기</a>
</body>
</html>