<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
var result = '${msg}';

if(result == 'SUCCESS') {
	alert("처리가 완료되었습니다.");
}
</script>
</head>
<body>
<table class="table table-hover">
  <tr>
    <th >BNO</th>
    <th>TITLE</th>
    <th>WRITER</th>
    <th>REGDATE</th>
    <th>VIEWCNT</th>
  </tr>
  
  <c:forEach items="${list}" var="boardVO">
  <tr>
    <td>${boardVO.bno}</td>
    <td><a href='/board/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
    <td>${boardVO.writer}</td>
    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
    <td><span class="badge">${boardVO.viewcnt}</</span></td>
  </tr>
  </c:forEach>
</table>
</body>
</html>