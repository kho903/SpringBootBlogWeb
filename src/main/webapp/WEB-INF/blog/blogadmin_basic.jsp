<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog 홈</title>
</head>
<body>
<div align="center">
    <h1>${blog.title}</h1>
    <h3>${blog.tag}</h3>
</div>
<div>
    <c:if test="${user == null}">
        <a href="/login"><b>로그인</b></a>
    </c:if>
    <c:if test="${user != null}">
        <a href="/logout"><b>로그아웃</b></a>
    </c:if>
    <c:if test="${blog.userId == user.userId}">
        <a href="/blogAdmin/${blog.blogId}">블로그 관리</a>
    </c:if>
    <a href="/blogMain/${blog.blogId}">블로그 메인</a>
</div>
<div>
    <a href="">기본설정</a>
    <a href="adminCategory/${blog.blogId}">카테고리</a>
    <a href="adminPost">글작성</a>
</div>
<div>
    <form action="/blogAdmin/${blog.blogId}" method="post">
        블로그 제목 : <input type="text" name="title" /> <br>
        블로그 태그 : <input type="text" name="tag" />
        <input type="submit" value="확인">
    </form>
    <a href="/">인덱스 페이지로 이동</a>
</div>

</body>
</html>