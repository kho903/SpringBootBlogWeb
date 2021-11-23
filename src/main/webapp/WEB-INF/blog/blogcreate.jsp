<%@page contentType="text/html; charset=EUC-KR" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
</head>
<body>
<center>
    <a href="/"><img src="images/logo.jpg"/></a>
    <form action="blogcreate" method="post">
        블로그 제목 : <input type="text" name="title" /> <br>
        블로그 태그 : <input type="text" name="tag" />
        <input type="submit" value="블로그 생성">
    </form>
    <a href="/">인덱스 페이지로 이동</a>
</center>
</body>
</html>