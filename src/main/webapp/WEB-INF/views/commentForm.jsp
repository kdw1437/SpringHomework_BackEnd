<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
	<h1>댓글 수정</h1>

	<c:if test="${sessionScope.loggedInUser eq comment.author}">
		<form:form action="${pageContext.request.contextPath}/comments/edit/${comment.commentId}" method="post" modelAttribute="comment">
			<form:hidden path="commentId" />
			<form:hidden path="postId" />
			<form:hidden path="author" />
			<div>
				<form:label path="content">내용:</form:label>
				<form:textarea path="content" required="true" />
			</div>
			<div>
				<input type="submit" value="수정" />
			</div>
		</form:form>
	</c:if>

	<c:if test="${sessionScope.loggedInUser ne comment.author}">
		<p>이 댓글을 수정할 권한이 없습니다.</p>
	</c:if>

	<a href="<c:url value='/posts/${comment.postId}' />">게시글로 돌아가기</a>

	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>
</body>
</html>