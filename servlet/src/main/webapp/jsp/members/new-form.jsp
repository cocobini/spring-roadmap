<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%-- JSP파일에는 이 라인이 반드시 있어야 함 --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
