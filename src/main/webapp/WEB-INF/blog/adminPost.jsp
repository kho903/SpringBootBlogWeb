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
</div>
<div>
    <h1>글 작성</h1>
    <form action="">
    제목 : <input type="text">
    내용 : <input type="text">
        <input type="submit">
    </form>
</div>

</body>
</html>