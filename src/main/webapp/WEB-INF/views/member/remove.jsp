<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>회원 탈퇴</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<!-- font-awesome5 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid text-center">
		<h2> 회원삭제 </h2><br/>
	</div>
	<div class="container-fluid text-center">
		<div id="loginWrap" class="row">
			<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<!-- 상단 메뉴버튼 -->
					<div class="text-right">
						<form action="${cp}/memModifyForm" method="post" style="display: inline">
					 		<input type="hidden" id="memId" name="memId" value="${member.memId}"/>
					 		<button type="submit" class="btn btn-warning">회원정보 수정</button>
				 		</form>
						<form style="display: inline">
							<button onclick="location.href='memLogout'" type="button" class="btn btn-danger"> 로그아웃 </button>
						</form>
					</div><br/><br/>
					
					<!-- 로그인 폼-->
					<!-- ajax로 form 제출시 action 경로 생략 -->
					<form id="remove" action="${cp}/memRemove" method="post">
						<!-- 아이디 -->
						<div id="idInputGroup" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-user fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<input id="memId" type="text" class="form-control" name="memId" placeholder="삭제할 아이디를 입력하세요.">
						</div>
						<!-- 비밀번호 -->
						<div id="pwInputGroup" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-key fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<input id="memPw" type="password" class="form-control" name="memPw" placeholder="비밀번호를 입력하세요.">
						</div>
						<button type="submit" class="btn btn-primary btn-block">로그인</button>
						<button onclick="location.href='openList'" type="button" class="btn btn-info btn-block">뒤로가기</button>
					</form>
					
				</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
</body>
</html>
