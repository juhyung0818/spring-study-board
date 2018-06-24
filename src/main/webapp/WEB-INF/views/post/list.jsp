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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Board</title>
</head>
<body>
<div style="width:60%; margin: 0 auto;">
<img src="/resources/img/logo-jstagram.png">
    <c:forEach var="post" items="${posts}">
        <div style="border-style: groove;" id="${post.id}" class="post-id">
            <div class="simple-profile" style="padding-left: 10px;">
                    ${post.writer}
            </div>
            <div class="image" style="border-top: groove; border-bottom: groove;">
                <c:if test="${post.image == null}">
                    <img src="/resources/img/default.png" width="200px" height="300px"
                         style="margin-left: auto; margin-right: auto; display: block;">
                </c:if>
            </div>
            <div>
                <div class="share" style="padding-bottom: 5px">
                    <a href="#" class="like-button">
                        <img ${post.liked ? 'src="/resources/img/liked.png" data-liked=true' : 'src="/resources/img/like.png" data-liked=false'}
                                width="32px" height="32px" >
                    </a>
                    <a href="#" class="comment-button">
                        <img src="/resources/img/comment.png" width="32px" height="32px">
                    </a>
                    <div class="like">
                        좋아요 <span class="like-count">${post.likeCount}</span> 개
                    </div>
                </div>
                <div class="text" style="padding-bottom: 5px">${post.text}</div>
                <div class="date">
                    <p style="color: gray; font-size: small">${post.registerDate}</p>
                </div>
            </div>
        </div>
        <br><br>
    </c:forEach>

<button id="new_post">글 작성</button>
</div>
</body>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<script>
    $(document).ready(function(){
        $("#new_post").click(function() {
            console.log("click");
            location.href = "/java/posts/new";
        })
    });

    $(".like-button").click(function() {
        event.preventDefault();
        var self = $(this);
        var id = self.closest(".post-id").prop("id");
        var likeImg = self.children("img");
        var liked = likeImg.data("liked");
        var likeCount = self.closest(".share").find(".like-count");
        var count = parseInt(likeCount.html());

        $.ajax({
            type : "PUT",
            url : "/java/posts/" + id + "/like"
        }).done(function() {
            if(liked) {
                likeImg.attr("src", "/resources/img/like.png");
                likeImg.data("liked", false);
                likeCount.html(count - 1);

            } else {
                likeImg.attr("src", "/resources/img/liked.png");
                likeImg.data("liked", true);
                likeCount.html(count + 1);
            }
        });
    })
</script>
</html>