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
    <h1>카테고리 목록</h1>
    <div>
        <table border="1" width="80%">
                <tr>
                    <th>카테고리 이름</th>
                    <th>보이기 유형</th>
                    <th>포스트 수</th>
                    <th>설명</th>
                    <th>만든 날짜</th>
                    <th>삭제</th>
                </tr>
            <c:forEach items="${categoryList}" var="category">
                <tr align="center">
                    <td>${category.categoryName}</td>
                    <td>${category.displayType}</td>
                    <td>${category.cntDisplayPost}</td>
                    <td>${category.description}</td>
                    <td>${category.modifiedDate}</td>
                    <td><a href="/blogAdmin/deleteCategory/${blog.blogId}/${category.categoryId}">삭제</a> </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <h1>카테고리 작성</h1>
    <form action="/blogAdmin/adminCategory/${blogId}" method="post">
        <input value="${blogId}" name="blogId" hidden>
        카테고리 명 : <input type="text" name="categoryName" /> <br>
        보이기 유형 : <input type="radio" name="displayType" value="제목" checked="checked">제목
        <input type="radio"	name="displayType" value="제목 + 내용">제목 + 내용<br>
        포스트 수 : <input type="text" name="cntDisplayPost" /><br>
        설명 : <input type="text" name="description" />
        <input type="submit" value="확인">
    </form>
</div>

</body>
</html>