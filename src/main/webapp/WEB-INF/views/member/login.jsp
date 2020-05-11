<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>글 작성</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<% String cp = request.getContextPath(); %>
<!-- 부트스트랩4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<!-- font-awesome5 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid text-center">
		<h2> 로그인 </h2><br/>
	</div>
	<div class="container-fluid text-center">
		<div id="loginWrap" class="row">
			<div class="col-md-3"></div>
				<div class="col-md-6">
					<!-- 로그인 폼-->
					<form:form action="${cp}/memLogin" method="post" modelAttribute="loginMember">
						<!-- 아이디 -->
						<div id="idInputGroup" class="input-group">
							<div class="input-group-prepend">	
								<span class="input-group-text">
									<i class="fas fa-user fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memId" class="form-control" placeholder="아이디를 입력하세요."/>
						</div>
						
						<!-- 비밀번호 -->
						<div id="pwInputGroup" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-key fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memPw" type="password" class="form-control" placeholder="비밀번호를 입력하세요."/>	
						</div>
						
						<form:button class="btn btn-primary btn-block">로그인</form:button>
					</form:form>
					
					<!-- 로그인 옵션 --><br/>
					<div id="loginOption">
						<a href="#">아이디 찾기</a>
						<span class="bar">|</span>
						<a href="#">비밀번호 찾기</a>
						<span class="bar">|</span>
						<a href="${cp}/memJoinForm">회원가입</a>
					</div>
				</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>
