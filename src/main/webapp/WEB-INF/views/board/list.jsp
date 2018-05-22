<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2018. 5. 20.
  Time: PM 6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Board</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>글 번호</th>
        <th>제목</th>
        <th>등록일</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${boards}">
    <tr>
        <td>${board.id}</td>
        <td><a href="/java/boards/${board.id}">${board.title}</a></td>
        <td>${board.registerDate}</td>
        <td>${board.viewCount}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<button id="new_post">글 작성</button>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"/>
<script>
    $(document).ready(function(){
        $("#new_post").click(function() {
            console.log("click");
            location.href = "/java/boards/new";
        })
    });
</script>
</body>
</html>