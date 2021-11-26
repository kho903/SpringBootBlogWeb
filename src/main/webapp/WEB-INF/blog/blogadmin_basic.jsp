<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>블로그 관리</title>
</head>
<body>
<div align="center" style="background-color: deepskyblue; height:20%">
    <h1 style="color: white">${blog.title}</h1>
    <h3 style="color: white">${blog.tag}</h3>
    <div align="right"><br><br><br><br>
    <c:if test="${user == null}">
        <a href="/login"><b>로그인</b></a>
    </c:if>
    <c:if test="${user != null}">
        <a href="/logout"><b>로그아웃</b></a>
    </c:if>&nbsp;
    <a href="/blogMain/${blog.blogId}">내 블로그 메인</a>
    </div>
</div>
<div align="left">
    <a href="">기본설정</a>
    <a href="adminCategory/${blog.blogId}">카테고리</a>
    <a href="adminPost">글작성</a>
</div>
<br><br><br>
<div align="left">
    <form action="/blogAdmin/${blog.blogId}" method="post">
        블로그 제목 : <input type="text" name="title" /> <br>
        블로그 태그 : <input type="text" name="tag" />
        <input type="submit" value="확인">
    </form>
    <a href="/">인덱스 페이지로 이동</a>
</div>

</body>
</html>