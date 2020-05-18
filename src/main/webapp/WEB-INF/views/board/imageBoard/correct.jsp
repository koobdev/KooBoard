<%@page import="com.Kcompany.Kboard.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>글 수정</title>
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
		<h2> 글 수정하기 </h2><br/>
	</div>	
	<div class="container-fluid text-center">
		<div class="row">
			<div class="col-md-3"></div>
				<div class="col-md-6">
					<div class="text-right">
						<form>
							<p>${member.memId}님 &nbsp;&nbsp;<span class="bar">|</span> &nbsp;&nbsp;
							<button onclick="location.href='openList'" type="button" class="btn btn-primary"> 글 목록 </button>
							<button onclick="location.href='memLogout'" type="button" class="btn btn-danger"> 로그아웃 </button>
						</form>
					</div><br/><br/>
					<div class="card">
						<!-- 작성글 헤더(글 제목, 글 정보) -->
						<form:form id="correct" action="imageCorrectBoard" method="post" modelAttribute="correctView" enctype="multipart/form-data">
							<div class="card-header">
								<form:input path="b_title" class="form-control"/>
							</div>
						
							<!-- 작성글 바디(글 내용) -->
							<div class="card-body">
								<form:hidden path="b_index"/>
								<form:hidden path="b_hit"/>
								<form:hidden path="b_recommend"/>
								<form:hidden path="b_replyCount"/>
								<form:hidden path="b_createDate"/>
								<form:hidden path="b_memId"/>
								<div class="well">
									<form:textarea path="b_content" class="form-control" cols="60" rows="10"/>
								</div><br/>
								<!-- 이미지 수정 -->
								<div class="card-footer">
									<form:label path="uploadFile">첨부 이미지</form:label>
									<form:input type="file" path="uploadFile" class="form-control" accept="image/*"/>
								</div>
								<!-- 수정완료 버튼 -->
								<div class="text-center">
									<form:button class="btn btn-primary">수정하기</form:button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>































