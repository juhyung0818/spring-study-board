<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2018. 5. 22.
  Time: PM 5:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>새 게시글 작성</title>
</head>
<body>
    <form action="/java/boards" method="POST">
        <label for="title">title : </label><br>
        <input type="text" id="title" name="title"/>
        <br>
        <label for="content">content : </label> <br>
        <textarea rows="4" cols="50" id="content" name="content"></textarea>
        <input type="submit" value="등록">
    </form>
</body>
</html>
