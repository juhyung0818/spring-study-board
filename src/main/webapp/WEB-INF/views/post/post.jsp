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
    <title>게시글 ${post.id}</title>
</head>
<body>

<form action="/java/posts/modify" method="POST">
    <table border="1">
        <thead>
            <th>${post.id}</th>
            <th><input type="text" value="${post.title}" name="title"/> </th>
            <th>${post.registerDate}</th>
            <th>${post.viewCount}</th>
        </thead>
        <tbody>
            <td colspan="4">
                <textarea rows="4" cols="50" id="content" name="content" >${post.content}</textarea>
            </td>
        </tbody>
    </table>
    <input type="hidden" value="${post.id}" name="id"/>
    <input type="submit" value="수정" />
    <input id="list" type="button" value="목록" />
</form>
</body>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"/>
<script>
    $("#list").on("click", function() {
        console.log("click")
        location.href = "/java/posts";
    })
</script>
</html>
