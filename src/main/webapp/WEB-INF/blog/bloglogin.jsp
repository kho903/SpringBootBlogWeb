<%@page contentType="text/html; charset=EUC-KR" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>로그인</title>
</head>
<body>
    <center>
    <a href="/"><img src="images/logo.jpg"/></a>
    <form action="login" method="post">
        아이디 : <input type="text" name="id" />
        패스워드 : <input type="password" name="password" /><br><br>
        <input type="submit" value="로그인">
    </form>
    </center>
</body>
</html>