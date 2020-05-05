<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>글 작성</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩3 -->
<link rel="stylesheet" href="<%=cp%>/resources/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="<%=cp%>/resources/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container-fluid text-center">
	<h2> 로그인 </h2><br/>
	<div class="container text-center">
		<div id="loginWrap" class="row">
			<div class="col-md-6 col-md-offset-3">
				<!-- 로그인 폼-->
				<!-- ajax로 form 제출시 action 경로 생략 -->
				<form id="login" action="${cp}/memLogin" method="post">
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
					<button type="submit" class="btn btn-primary btn-block">로그인</button>
				</form>
				
				<!-- 로그인 옵션 --><br/>
				<div id="loginOption">
					<a href="#">아이디 찾기</a>
					<span class="bar">|</span>
					<a href="#">비밀번호 찾기</a>
					<span class="bar">|</span>
					<a href="${cp}/memJoinForm">회원가입</a>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>