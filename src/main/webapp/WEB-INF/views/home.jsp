<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
<title>회원 정보</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container-fluid text-center"><br/>
	<c:if test="${empty member}">
		<% 
			response.sendRedirect(cp + "/memLoginForm"); 
		%>
	</c:if>
	<c:if test="${!empty member}">
		<h2> 회원정보 </h2><br/>
		<div class="container-fluid text-center">
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
				  <div>
				  	<a href="${cp}/memLogout" type="button" class="btn btn-info btn-block"> 로그아웃 </a>
				 	<form action="${cp}/memModifyForm" method="post">
				 		<input type="hidden" id="memId" name="memId" value="${member.memId}"/>
				 		<button type="submit" class="btn btn-warning">회원정보 수정</button>
				 	</form>
				  	<a href="${cp}/memRemoveForm" type="button" class="btn btn-danger btn-block"> 회원 삭제 </a>
				  </div>
				  <div>
				 	 <button onclick="location.href='openList'" type="button" class="btn btn-primary btn-block">뒤로가기</button>
				  </div>
				</div><br/>
				<div class="col-sm-3"></div>
			</div>
		</div>
	</c:if>
</div>
</body>
</html>
