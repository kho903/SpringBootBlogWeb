<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog 홈</title>
</head>
<body>
<div align="center" style="background-color: deepskyblue; height:20%">
    <h1 style="color: white">${blog.title}</h1>
    <h3 style="color: white">${blog.tag}</h3>
</div>
<div align="center">
    <c:if test="${user == null}">
        <a href="/login"><b>로그인</b></a>
    </c:if>
    <c:if test="${user != null}">
        <a href="/logout"><b>로그아웃</b></a>
    </c:if>
    <c:if test="${blog.userId == user.userId}">
        <a href="/blogAdmin/${blog.blogId}">블로그 관리</a>
    </c:if>
</div>
<div>
        <table border="1">
            <tr>
                <th>번호</th>
                <th>카테고리 아이디</th>
                <th>카테고리 이름</th>
                <th>내용</th>
                <th>만든 날짜</th>
            </tr>
            <c:forEach items="${postList}" var="post">
            <tr>
                <td>${post.postId}</td>
                <td>${post.categoryId}</td>
                <c:forEach items="${categoryList}" var="category">
                    <c:if test="${category.categoryId eq post.categoryId}">
                        <td>${category.categoryName}</td>
                    </c:if>
                </c:forEach>
<%--                <td>${post.categoryId}</td>--%>
                <td>${post.content}</td>
                <td>${post.createdDate}</td>
            </tr>
            </c:forEach>

        </table>
</div>

</body>
</html>