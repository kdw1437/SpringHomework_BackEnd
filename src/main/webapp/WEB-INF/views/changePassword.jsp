<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
</head>
<body>
    <h2>비밀번호 변경</h2>
    <form action="${pageContext.request.contextPath}/user/changePassword" method="post">
        <div>
            <label for="author">사용자명:</label>
            <input type="text" id="author" name="author" required>
        </div>
        <div>
            <label for="oldPassword">이전 비밀번호:</label>
            <input type="password" id="oldPassword" name="oldPassword" required>
        </div>
        <div>
            <label for="newPassword">새 비밀번호:</label>
            <input type="password" id="newPassword" name="newPassword" required>
        </div>
        <div>
            <input type="submit" value="비밀번호 변경">
        </div>
    </form>
    <p>
        <a href="${pageContext.request.contextPath}/user/login">로그인으로 돌아가기</a>
    </p>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>