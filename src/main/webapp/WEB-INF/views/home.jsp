<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
<title>회원 정보</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩3 -->
<link rel="stylesheet" href="<%=cp%>/resources/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="<%=cp%>/resources/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container-fluid text-center"><br/>
	<p>Server Time is : ${serverTime}</p><br/><br/>
	
	<c:if test="${empty member}">
		<% 
			response.sendRedirect(cp + "/memLoginForm"); 
		%>
	</c:if>
	<c:if test="${!empty member}">
		<h2> 회원정보 </h2><br/>
		<div class="col-md-6 col-md-offset-3">
			<div class="row">
			  <div class="col-md-4"><a href="${cp}/memLogout" type="button" class="btn btn-info btn-block"> 로그아웃 </a></div>
			  <div class="col-md-4"><a href="${cp}/memModifyForm" type="button" class="btn btn-warning btn-block"> 회원정보 수정 </a></div>
			  <div class="col-md-4"><a href="${cp}/memRemoveForm" type="button" class="btn btn-danger btn-block"> 회원 삭제 </a></div>
			</div><br/>
			<button onclick="location.href='openList'" type="button" class="btn btn-primary btn-block">뒤로가기</button>
		</div>
	</c:if>
</div>
</body>
</html>
