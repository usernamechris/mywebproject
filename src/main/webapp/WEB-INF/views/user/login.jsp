<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/views/include/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h2>Log in</h2>
	<form action = "/user/loginPost" method="post">
		<div class="form-group">
			<label for="uid">ID</label>
			<input type="text" class="form-control" name = "uid" placeholder="user id"/>
		</div>
		<div class="form-group">
			<label for="password">ID</label>
			<input type="password" class="form-control" name ="upw" placeholder="password">
		</div>
		<div class="form-group">
			<div class="checkbox">
				<label><input type="checkbox" name="useCookie"> Remember Me </label>
			</div>
		</div>
		<div class="form-group">
		<button type="submit" class="btn btn-default">Log In</button>
		</div>
	</form>
</div>
</body>
</html>