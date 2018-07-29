<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2018. 7. 29.
  Time: PM 4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>User Profile</title>
</head>
<body>

<c:forEach var="post" items="${posts}">
    <div id="post.id">${post.content}</div>
    <div class="writer">${post.writer}</div>
</c:forEach>
${user.nickName}
</body>
</html>
