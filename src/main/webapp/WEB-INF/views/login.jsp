<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <div>
            <label for="author">사용자명:</label>
            <input type="text" id="author" name="author" required>
        </div>
        <div>
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="로그인">
        </div>
    </form>
    <p>
        <a href="${pageContext.request.contextPath}/user/register">새 사용자 등록</a>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/user/changePassword">비밀번호 변경</a>
    </p>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <a href="<c:url value='/posts' />">게시글 목록으로 돌아가기</a>
</body>
</html>