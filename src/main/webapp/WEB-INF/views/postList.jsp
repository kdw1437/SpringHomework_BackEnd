<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bulletin Board</title>
</head>
<body>
    <h1>Bulletin Board</h1>
    
    <c:if test="${not empty sessionScope.loggedInUser}">
        <p>Welcome, ${sessionScope.loggedInUser}! <a href="<c:url value='/user/logout' />">Logout</a></p>
        <a href="<c:url value='/posts/new' />">Create New Post</a>
    </c:if>
    <c:if test="${empty sessionScope.loggedInUser}">
        <p><a href="<c:url value='/user/login' />">Login</a> to create a new post</p>
    </c:if>

    <table border="1">
        <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Created Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="post" items="${posts}">
                <tr>
                    <td><a href="<c:url value='/posts/${post.postId}' />">${post.title}</a></td>
                    <td>${post.author}</td>
                    <td><fmt:formatDate value="${post.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <c:if test="${totalPages > 1}">
        <div class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <span>${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/posts?page=${i}&size=${pageSize}' />">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>