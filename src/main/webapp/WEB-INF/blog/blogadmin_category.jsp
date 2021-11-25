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
<div align="center">
    <h1>카테고리 작성</h1>
    <form action="/blogAdmin/adminCategory/${blogId}" method="post">
        <input value="${blogId}" name="blogId" hidden>
        카테고리 명 : <input type="text" name="categoryName" /> <br>
        보이기 유형 : <input type="radio" name="displayType" value="titleOnly" checked="checked">제목
        <input type="radio"	name="displayType" value="titleCont">제목 + 내용
        포스트 수 : <input type="text" name="cntDisplayPost" />
        설명 : <input type="text" name="description" />
        <input type="submit" value="확인">
    </form>
</div>

</body>
</html>