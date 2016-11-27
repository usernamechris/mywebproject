<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp" %>


<c:if test="${pageMaker.prev}">
	<li><a href="listPage?page=${pageMaker.startPage - 1 }">&laquo;</a></li>
</c:if>

  
<c:forEach begin="${PageMaker.startPage}" end="${pageMaker.endPage }" var="idx">
	<li>
		<a href="listPage?page=${idx}">${idx}</a>
	</li>
</c:forEach>

<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	<li><a href="listPage?page=${pageMaker.endPage + 1 }">&raquo;</a></li>
</c:if>