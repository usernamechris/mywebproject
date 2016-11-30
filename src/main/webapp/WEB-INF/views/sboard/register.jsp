<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
REGISTER BOARD<br/><br/>
<form role="form" method="post">
<div class="form-group">
	Title <input type="text" name="title" class="form-control"><br><br/>
	Content<textarea name="content" rows="3" class="form-control" placeholder="Enter..."></textarea><br/>
	Writer<input type="text" name="writer" class="form-control" /><br/>
	<button type="submit" class="form-control">submit</button>
</div>
</form>
</body>
</html>