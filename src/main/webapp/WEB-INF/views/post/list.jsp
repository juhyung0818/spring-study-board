<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2018. 5. 20.
  Time: PM 6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Board</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>글 번호</th>
        <th>제목</th>
        <th>등록일</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${posts}">
    <tr>
        <td>${post.id}</td>
        <td><a href="/java/posts/${post.id}">${post.title}</a></td>
        <td>${post.registerDate}</td>
        <td>${post.viewCount}</td>
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
