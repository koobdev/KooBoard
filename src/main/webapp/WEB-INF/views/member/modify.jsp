<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>회원정보 수정</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- font-awesome5 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container text-center">
		<h2> 회원정보수정 </h2><br/>
	</div>
	<div class="container-fluid text-center">
		<div class="row">
			<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<form:form action="${cp}/memModify" method="post" modelAttribute="correctMember">
						<!-- 수정페이지에서는 checkId를 true값으로 념겨주어야 유효성검사를 피할 수 있다. -->
						<form:hidden path="checkId" value="true"/>
						<!-- 아이디 -->
						<div id="idInputGroup" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-user fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memId" type="text" class="form-control" readonly="true"/>
						</div>
						<form:errors path="memId" style="color:red"/>
						<!-- 비밀번호 -->
						<div id="pwInputGroup" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-key fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memPw" type="password" class="form-control" placeholder="영문,숫자조합 6자리 이상 10자리 이하로 입력하세요."/>
						</div>
						<form:errors path="memPw" style="color:red"/>
						<!-- 이메일 -->
						<div id="mailInputGroup" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-envelope fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memMail" type="email" class="form-control" placeholder="이메일 형식에 맞추어 주소를 입력하세요."/>
						</div>
						<form:errors path="memMail" style="color:red"/>
						<!-- 전화번호 -->
						<div id="pnInputGroup" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-mobile-alt fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memPhone" type="text" class="form-control" placeholder="-를 제외한 휴대폰번호를 입력하세요."/>
						</div>
						<form:errors path="memPhone" style="color:red"/>
						
						<form:button class="btn btn-primary btn-block">회원정보 수정</form:button>
						<input onclick="location.href='<%=cp%>/'" type="button" class="btn btn-info btn-block" value="뒤로가기"/>
					</form:form>
					
					<!-- 로그인 옵션 --><br/>
					<div id="loginOption">
						<a href="#">아이디 찾기</a>
						<span class="bar">|</span>
						<a href="#">비밀번호 찾기</a>
					</div>
				</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
</body>
</html>
