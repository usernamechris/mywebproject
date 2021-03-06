<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.fileDrop {
width: 100%;
height: 200px;
border: 1px dotted blue;
}

small {
margin-left: 3px;
font-weight: bold;
color: gray;
}
</style>
</head>
<body>

<h3>Ajax File Upload</h3>
<div class='fileDrop'></div>
<div class='uploadedList'></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(".fileDrop").on("dragenter dragover", function(event) {
	event.preventDefault(); // 이것 안하면 새창에서 파일이 열려버림
});

$(".fileDrop").on("drop", function(event) {
	event.preventDefault(); // 이것 안하면 새창에서 파일이 열려버림
	
	var files = event.originalEvent.dataTransfer.files;
	var file = files[0];

	console.log(file);
	
	var formData = new FormData();
	
	formData.append("file", file);
	
	$.ajax({
		url:'/uploadAjax',
		data: formData,
		dataType: 'text',
		processData: false, // 데이터를 일반 string으로 변환할건지(기본값 true). 파일전송을 위해선 반드시 false값이어야 한다. 따라서 $.post대신 $.ajax써서 옵션값 줌
		contentType: false, // 기본값 x-www-form-urlencoded. 파일전송을 위해선 반드시 false값이어야 한다. 따라서 $.post대신 $.ajax써서 옵션값 줌
		type: 'POST',
		success: function(data) {
			var str = "";
			
			console.log("uploadAjax successed");
			console.log("data: " + data);
			console.log("image type: " + checkImageType(data));
			
			if (checkImageType(data)) {
				str = "<div>"
					+ "<a href='displayFile?fileName=" + getImageLink(data) + "'>"
					+ "<img src='displayFile?fileName=" + data + "'/>"
					+ "</a><small data-src=" + data + ">X</small></div>";
			} else {
				str = "<div><a href='displayFile?fileName="
					+ data + "'>"
					+ getOriginalName(data) + "</a>"
					+ "<small data-src=" + data + ">X</small></div></div>";
			}
			
			$(".uploadedList").append(str);
		}
	});
});

$(".uploadedList").on("click", "small", function(event){
	var that = $(this);
	
	$.ajax({
		url: "deleteFile",
		type: "post",
		data: {fileName:$(this).attr("data-src")},
		dataType: "text",
		success:function(result) {
			if (result == 'deleted') {
				that.parent("div").remove();
			}
		}
	});
});

function checkImageType(fileName) {
	var pattern = /jpg|gif|png|jpeg/i; //i는 대소구분 없음
	return fileName.match(pattern);
}

function getOriginalName(fileName) {
	if (checkImageType(fileName)) {
		return;
	}
	
	var idx = fileName.indexOf("_") + 1;
	return fileName.substr(idx);
}

function getImageLink(fileName) {
	if (!checkImageType(fileName)) {
		return;
	}
	var front = fileName.substr(0, 12);
	var end = fileName.substr(14);
	
	return front + end;
}
</script>
</body>
</html>