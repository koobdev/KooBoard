<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>회원가입</title>
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
		<h2> 회원가입 </h2><br/>
	</div>
	<div class="container-fluid text-center">
		<div id="joinWrap" class="row">
			<div class="col-md-3"></div>
				<div class="col-md-6">
					<form:form action="${cp}/memJoin" method="post" modelAttribute="joinMember">
						<!-- 아이디 -->
						<div class="input-group">
							<div class="input-group-prepend">	
								<span class="input-group-text">
									<i class="fas fa-user fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memId" class="form-control" placeholder="영문,숫자조합 6자리 이상 10자리 이하로 입력하세요." onkeypress="resetCheckId()"/>
							<!-- 중복확인 버튼 -->							
							<form:button type="button" class="btn btn-outline-danger float-right" onclick="chekMemId()">중복확인</form:button>
							<form:hidden path="checkId"/>
						</div>						
						<form:errors path="memId" style="color:red"/>
						<form:errors path="checkId" style="color:red"/>
						<!-- 비밀번호 -->
						<div class="input-group"> 	
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-key fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memPw" type="password" class="form-control" placeholder="영문,숫자조합 6자리 이상 10자리 이하로 입력하세요."/>						
						</div>
						<form:errors path="memPw" style="color:red"/>
						<!-- 이메일 -->
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-envelope fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memMail" type="email" class="form-control" placeholder="이메일 형식에 맞추어 주소를 입력하세요."/>							
						</div>
						<form:errors path="memMail" style="color:red"/>
						<!-- 전화번호 -->
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="fas fa-mobile-alt fa-fw" aria-hidden="true"></i>
								</span>
							</div>
							<form:input path="memPhone" class="form-control" placeholder="-를 제외한 휴대폰번호를 입력하세요."/>							
						</div>
						<form:errors path="memPhone" style="color:red"/>
						
						<form:button class="btn btn-success btn-block">회원가입</form:button>
					</form:form>
					<form id="back" action="${cp}/memLoginForm" method="post">
						<button type="submit" class="btn btn-primary btn-block">뒤로가기</button>
					</form>
					<!-- 로그인 옵션 --><br/>
					<div id="loginOption">
						<a href="#">아이디 찾기</a>
						<span class="bar">|</span>
						<a href="#">비밀번호 찾기</a>
					</div>
				</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
<script>
	// 중복확인 버튼 함수 
	function chekMemId(){
		
		var memId = $("#memId").val()
		
		if(memId.length == 0){
			alert("아이디를 입력해주세요.")
			return
		}
		
		$.ajax({
			url : "${cp}/memIdCheck/" + memId,
			type : "get",
			dataType : "text",
			success : function(result){
				// true가 넘어오면 chekId프로퍼티를 ture로 지정해줌으로써 유효성검사를 통과한다.
				if(result.trim() == "true"){
					alert("사용할 수 있는 아이디입니다.")
					$("#checkId").val("true")
				}else{
					alert("존재하는 아이디 입니다.")
					$("#checkId").val("false")
				}
			}
		})
	}
	
	// 다른 아이디를 입력하면 checkId에 false를 넣어서 중복검사를 재시행 해야함.
	function resetCheckId() {
		
		$("#checkId").val("false")
	}
</script>
</html>










