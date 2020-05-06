<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>회원정보 수정</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container-fluid text-center">
	<h2> 회원정보 수정 </h2><br/>
	<div class="container text-center">
		<div id="correctWrap" class="row">
			<div class="col-md-6 col-md-offset-3">
				<form id="correct" action="${cp}/memModify" method="post">
					<!-- 아이디 -->
					<div id="idInputGroup" class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input id="memId" type="text" class="form-control" name="memId" placeholder="4자리 이상 영문 아이디를 입력하세요">
					</div>
					<!-- 비밀번호 -->
					<div id="pwInputGroup" class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-lock"></i>
						</span>
						<input id="memPw" type="password" class="form-control" name="memPw" placeholder="4자리 이상 비밀번호를 입력하세요">
					</div>
					<!-- 이메일 -->
					<div id="mailInputGroup" class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-envelope"></i>
						</span>
						<input id="memMail" type="email" class="form-control" name="memMail" placeholder="이메일 주소를 입력하세요">
					</div>
					<!-- 전화번호 -->
					<div id="pnInputGroup" class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-phone"></i>
						</span>
						<input id="memPhone" type="text" class="form-control" name="memPhone" placeholder="'-'를 제외한 휴대폰번호를 입력하세요">
					</div>
					<button type="submit" class="btn btn-primary btn-block">회원정보 수정</button>
					<button onclick="location.href='<%=cp%>/'" type="button" class="btn btn-info btn-block">뒤로가기</button>
				</form>
				
				<!-- 로그인 옵션 --><br/>
				<div id="loginOption">
					<a href="#">아이디 찾기</a>
					<span class="bar">|</span>
					<a href="#">비밀번호 찾기</a>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
