<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- WEB-INF 밑의 리소스들은 브라우저에서 호출해도 불러올 수 없도록 정해져있음 --%>
<form action="save" method="post"> <!-- 상대경로 사용(보통은 절대경로가 더 좋음) -->
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
