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
    <form action="http://localhost/upload.php" method="post" enctype="multipart/form-data">
        <label for="image-upload"></label>
        <input type="file" name="profile" id="image-upload" value="사진 업로드">
    </form>

    <div id="images"></div>

    <form action="/java/posts" method="POST">
        <label for="text">text : </label> <br>
        <textarea rows="4" cols="50" id="text" name="text"></textarea>
        <input type="submit" value="등록">
    </form>
</div>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
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

                var img_html = "<img src=" + e.target.result + " width='100px' height='100px'/>";
                $("#images").append(img_html);
            }
            reader.readAsDataURL(f);
        });
    }
</script>
</body>
</html>
