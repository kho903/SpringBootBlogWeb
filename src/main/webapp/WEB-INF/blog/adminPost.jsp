<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>포스트 생성</title>
</head>
<body>
<div align="center" style="background-color: deepskyblue; height:20%">
    <br>
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
<div align="center">
    <h1>글 작성</h1>
    <form
            <c:if test="${post == null}">
                action="/blogAdmin/adminPost"
            </c:if>
            <c:if test="${post != null}">
                action="/blogAdmin/updatePost/${post.postId}"
            </c:if>
            method="post">
    제목 : <input type="text" name="title" style="width: 50%;"
            <c:if test="${post != null}">
                        value="${post.title}"
            </c:if>
    >
    <select name="categoryId">
        <c:forEach items="${categoryList}" var="category">
            <option value="${category.categoryId}">
                ${category.categoryName}
            </option>
        </c:forEach>
    </select><br>
    내용 : <input type="text" name="content" style="width: 60%; height: 30%"
            <c:if test="${post != null}">
            value="${post.content}"
            </c:if>
    ><br>
        <input type="submit">
    </form>
</div>

</body>
</html>