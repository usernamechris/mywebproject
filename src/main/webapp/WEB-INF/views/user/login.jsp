<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action = "/user/loginPost" method="post">
	<div>
	<input type="text" name = "uid"/>
	</div>
	<div>
	<input type="password" name ="upw">
	</div>
	<div>
	<label><input type="checkbox" name="useCookie"> Remember Me </label>
	</div>
	<div>
	<button type="submit">Sign In</button>
	</div>
</form>
</body>
</html>