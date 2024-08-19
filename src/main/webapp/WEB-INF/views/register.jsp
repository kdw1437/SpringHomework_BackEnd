<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
    <h2>새 사용자 등록</h2>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        <div>
            <label for="author">사용자명:</label>
            <input type="text" id="author" name="author" required>
        </div>
        <div>
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="등록">
        </div>
    </form>
    <p>
        <a href="${pageContext.request.contextPath}/user/login">로그인으로 돌아가기</a>
    </p>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <a href="<c:url value='/posts' />">게시글 목록으로 돌아가기</a>
</body>
</html>