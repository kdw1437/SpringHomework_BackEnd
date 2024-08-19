<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${post.postId == null ? 'Create' : 'Edit'} Post</title>
</head>
<body>
    <h1>${post.postId == null ? 'Create' : 'Edit'} Post</h1>
    <form:form action="${pageContext.request.contextPath}/posts" method="post" modelAttribute="post">
        <form:hidden path="postId" />
        <div>
            <form:label path="title">Title:</form:label>
            <form:input path="title" required="true" />
        </div>
        <div>
            <form:label path="content">Content:</form:label>
            <form:textarea path="content" required="true" />
        </div>
        <div>
            <input type="submit" value="${post.postId == null ? 'Create' : 'Update'}" />
        </div>
    </form:form>
    <a href="<c:url value='/posts' />">Back to Post List</a>
</body>
</html>