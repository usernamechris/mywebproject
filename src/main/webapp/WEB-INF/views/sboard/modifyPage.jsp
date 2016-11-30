<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<form role="form" action="modifyPage" method="post">
	<input type='hidden' name='page' value="${cri.page}">
	<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
	<input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">

<div class="box-body">
	<label>BNO</label><br/>
	<input type="text" class="form-control" name="bno" value="${boardVO.bno}" readonly="readonly">

	<label>Title</label><br/>
	<input type="text" class="form-control" name="title" value="${boardVO.title}" readonly="readonly">

	<label>Content</label><br/>
	<textarea name="content" class="form-control" rows="3">${boardVO.content}</textarea>
	
	<label>Writer</label><br/>
	<input type="text" class="form-control" name="writer" value="${boardVO.writer}" readonly="readonly" />
</div>
</form>

<button type="submit" class="btn btn-primary">SAVE</button>
<button type="submit" class="btn btn-warning">CANCEL</button>

<script>
$(document).ready(function() {
	var formObj = $("form[role='form']");
	console.log(formObj);
	
	$(".btn-warning").on("click", function() {
		self.location = "/sboard/list?page={cri.page}&perPageNum=${cri.perPageNum}"
				+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
	});

	$(".btn-primary").on("click", function() {
		formObj.submit();
	});

})
</script>