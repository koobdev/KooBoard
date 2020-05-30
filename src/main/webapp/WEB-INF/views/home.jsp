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
		<script> alert("로그인 후 이용해주세요.") </script>
		<% 
			response.sendRedirect(cp + "/memLoginForm"); 
		%>
	</c:if>
	<c:if test="${!empty member}">
		<% 
			response.sendRedirect(cp + "/openList"); 
		%>
	</c:if>
</div>
</body>
</html>
