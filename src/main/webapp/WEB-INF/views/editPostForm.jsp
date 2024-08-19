<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
</head>
<body>
    <h1>게시글 수정</h1>
    <form action="<c:url value='/posts/${post.postId}/edit' />" method="post">
        <input type="hidden" name="postId" value="${post.postId}">
        <label for="title">제목:</label><br>
        <input type="text" id="title" name="title" value="${post.title}" required><br>
        <label for="content">내용:</label><br>
        <textarea id="content" name="content" required>${post.content}</textarea><br>
        <input type="submit" value="게시글 수정">
    </form>
    <a href="<c:url value='/posts/${post.postId}' />">취소</a>
</body>
</html>l>