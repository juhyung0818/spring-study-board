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
<div style="width:60%; margin: 0 auto;">
    <div style="border-style: groove;" id="${post.id}" class="post-id">
        <div class="simple-profile" style="padding-left: 10px; border-bottom: groove;">
            juhyung
        </div>
        <div id="images" style="height: 200px;width: 200px;"></div>
    </div>


</div>
<div style="width:60%; margin: 0 auto;">
    <form action="/java/posts/" method="post" enctype="multipart/form-data">
        <input multiple="multiple" type="file" name="multipartFiles" id="image-upload">
        <textarea rows="4" cols="50" id="text" name="text"></textarea>
        <input type="submit" value="등록">
    </form>


</div>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<script src="/resources/js/node_modules/jquery-multifile/jquery.MultiFile.min.js"></script>
<script>
    var sel_files = [];
    $(document).ready(function() {
        $("#image-upload").on("change", handleImgsFilesSelect);
    });
    function handleImgsFilesSelect(e){
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
        filesArr.forEach(function(f) {
            if(!f.type.match("image.*")){
                alert("이미지 파일만 가능합니다.");
                return;
            }
            sel_files.push(f);
            var reader = new FileReader();
            reader.onload = function(e){

                var img_html = "<img src=" + e.target.result + " width='200px' height='200px'/>";
                $("#images").append(img_html);
            }
            reader.readAsDataURL(f);
        });
    }
</script>
</body>
</html>

