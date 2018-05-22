<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2018. 5. 22.
  Time: PM 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 ${board.id}</title>
</head>
<body>
    <table border="1">
        <thead>
            <th>${board.id}</th>
            <th>${board.title}</th>
            <th>${board.registerDate}</th>
            <th>${board.viewCount}</th>
        </thead>
        <tbody>
            <td colspan="4">${board.content}</td>
        </tbody>
    </table>
</body>
</html>
