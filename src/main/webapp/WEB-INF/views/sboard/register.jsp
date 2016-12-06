<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.fileDrop {
width: 80%;
height: 100px;
border: 1px dotted gray;
background-color: lightslategrey;
margin: auto;
}
</style>
<title>Insert title here</title>
</head>
<body>
REGISTER BOARD<br/><br/>
<form id='registerForm' role="form" method="post">
<div class="form-group">
	Title <input type="text" name="title" class="form-control"><br><br/>
	Content<textarea name="content" rows="3" class="form-control" placeholder="Enter..."></textarea><br/>
	Writer<input type="text" name="writer" class="form-control" /><br/>
</div>
<div>
	<label>File Drop Here</label>	
	<div class="fileDrop"></div>
</div>

<ul class="uploadedList">
</ul>
	<button type="submit" class="form-control">submit</button>
</form>


<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script id="template" type="text/x-handlebars-template">
	<li>
	  <span><img src="{{imgsrc}}" alt="Attachment"></span>
	  <div>
	  <a href="{{getLink}}">{{fileName}}</a>
	  <a href="{{fullName}}" class="delbtn"><i></i></a>
	  </div>
	</li>
</script>

<script>
var template = Handlebars.compile($("#template").html());

$(".fileDrop").on("dragenter dragover", function(event) {
	event.preventDefault();
});

$(".fileDrop").on("drop", function(event) {
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];
	
	var formData = new FormData();
	
	formData.append("file", file);
	
	$.ajax({
		url: '/uploadAjax',
		data: formData,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'post',
		success: function(data) {
			var fileInfo = getFileInfo(data);
			var html = template(fileInfo);
			$(".uploadedList").append(html);
		}
	});
});

$("#registerForm").submit(function(event) {
	event.preventDefault();
	
	var that = $(this);
	var str = "";
	
	$(".uploadedList .delbtn").each(function(index) {
		str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'> ";
	})
	
	that.append(str);
	that.get(0).submit(); // get(0)은 순수한 dom객체
});

</script>
<script type="text/javascript" src="/resources/js/upload.js"></script>


</body>
</html>