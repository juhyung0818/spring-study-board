<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2018. 5. 22.
  Time: PM 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>게시글 ${post.id}</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/comment.css" />
</head>
<body>
<div style="width:60%; margin: 0 auto;">
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
</div>

<div class="comments-container">
    <ul id="comments-list" class="comments-list">
        <c:forEach var="comment" items="${comments}">
        <li id="${comment.id}">
            <div class="comment-main-level">
                <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                <div class="comment-box">
                    <div class="comment-head">
                        <h6 class="comment-name"><a href="http://creaticode.com/blog">${comment.userName}</a></h6>
                        <span>${comment.registerDate}</span>
                        <i class="fa fa-reply"></i>
                        <i class="fa fa-heart"></i>
                    </div>
                    <div class="comment-content">
                        ${comment.content}
                    </div>
                </div>
            </div>
            <ul class="comments-list reply-list" id="reply-${comment.id}" data-parent="${comment.id}">
            <span class="child-comment">
            <c:forEach var="children" items="${comment.comments}">
                <li id="${comment.id}">
                    <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_2_zps7de12f8b.jpg" alt=""></div>
                    <div class="comment-box">
                        <div class="comment-head">
                            <h6 class="comment-name"><a href="http://creaticode.com/blog">user name</a></h6>
                                <span>${children.registerDate}</span>
                                <i class="fa fa-reply"></i>
                                <i class="fa fa-heart"></i>
                        </div>
                        <div class="comment-content">
                            ${children.content}
                        </div>
                    </div>
                </li>
            </c:forEach>
            </span>
                <li>
                    <div class="comment-main-level">
                        <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                        <div class="comment-box">
                            <div class="comment-head">
                                <h6>juhyung</h6>
                            </div>
                            <div class="comment-content">
                                <textarea class="auto-size new-comment" style="width: 90%; height: auto;"></textarea>
                                <button class="add-comment">등록</button>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </li>
        </c:forEach>
    </ul>
    <ul class="comments-list">
        <li>
            <div class="comment-main-level">
                <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                <div class="comment-box">
                    <div class="comment-head">
                        <h6>juhyung</h6>
                    </div>
                    <div class="comment-content">
                        <textarea id="new-comment" class="auto-size" style="width: 90%; height: auto;"></textarea>
                        <button id="add-comment">등록</button>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</div>
</body>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<script>
    $("#list").on("click", function() {
        location.href = "/java/posts";
    });

    $("#add-comment").on("click", function() {
        var commentList = $("#comments-list");
        var comment = {
            postId : "${post.id}",
            content : $("#new-comment").val(),
            parent : 0
        };
        var url = "/java/comments";
        $.ajax({
            type : "POST",
            url : url,
            data : JSON.stringify(comment),
            contentType : "application/json"
        }).done(function(comment) {
            console.log(comment);
            commentList.append(
            `<li id=` + comment.id + `>
                <div class="comment-main-level">
                <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                <div class="comment-box">
                    <div class="comment-head">
                        <h6 class="comment-name"><a href="http://creaticode.com/blog">` + "juhyung" + `</a></h6>
                        <span>` + comment.registerDate + `</span>
                        <i class="fa fa-reply"></i>
                        <i class="fa fa-heart"></i>
                    </div>
                    <div class="comment-content">`
                    + comment.content +
                    `</div>
                </div>
                </div>
            </li>`
            );
        });
    })

    $(".add-comment").on("click", function() {
        var that = $(this);
        var target = that.closest("ul");
        var parentId = target.data("parent");
        var content = that.siblings(".new-comment");
        var list = that.closest("ul").find("span.child-comment");

        var comment = {
            postId : "${post.id}",
            content : content.val(),
            parent : parentId
        };

        var url = "/java/comments";
        $.ajax({
            type : "POST",
            url : url,
            data : JSON.stringify(comment),
            contentType : "application/json"
        }).done(function(comment) {
            content.val("");
            list.append(
                `<li id=` + comment.id + `>
                    <div class="comment-main-level">
                    <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                    <div class="comment-box">
                        <div class="comment-head">
                            <h6 class="comment-name"><a href="http://creaticode.com/blog">` + "juhyung" + `</a></h6>
                            <span>` + comment.registerDate + `</span>
                            <i class="fa fa-reply"></i>
                            <i class="fa fa-heart"></i>
                        </div>
                        <div class="comment-content">`
                + comment.content +
                `</div>
                    </div>
                    </div>
                </li>`
            );
        })
    });
</script>
</html>