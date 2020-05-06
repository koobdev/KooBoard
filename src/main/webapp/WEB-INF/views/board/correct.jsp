<%@page import="com.Kcompany.Kboard.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<div class="container text-center">
	<section>
		<div class="row">
			<!-- 메인 -->
			<div class="col-sm-offset text-left">
				<h2 align="center"> 글 수정하기 </h2>
					<div class="text-right">
						<form>
							<p>${member.memId}</p>님 &nbsp;&nbsp;<span class="bar">|</span> &nbsp;&nbsp;
							<button onclick="location.href='openList'" type="button" class="btn btn-primary"> 글 목록 </button>
							<button onclick="location.href='memLogout'" type="button" class="btn btn-danger"> 로그아웃 </button>
						</form>
					</div><br/><br/>
				<div class="row">
					<div class="panel-group">
						<div class="panel panel-default">
							<!-- 작성글 헤더(글 제목, 글 정보) -->
							<form id="correct" action="correctBoard" method="post">
								<div class="panel-heading">
									<input id="title" name="b_title" class="form-control" type="text" placeholder="${correctView.b_title}">
								</div>
							
								<!-- 작성글 바디(글 내용) -->
								<div class="panel-body">
									<input type="hidden" id="b_index" name="b_index" value="${correctView.b_index}">
									<input type="hidden" id="b_hit" name="b_hit" value="${correctView.b_hit}">
									<input type="hidden" id="b_recommand" name="b_recommand" value="${correctView.b_recommand}">
									<input type="hidden" id="b_replyCount" name="b_replyCount" value="${correctView.b_replyCount}">
									<input type="hidden" id="b_createDate" name="b_createDate" value="${correctView.b_createDate}">
									<input type="hidden" id="b_memId" name="b_memId" value="${correctView.b_memId}">
										<div class="well">
											<textarea class="form-control" id="content" name="b_content" cols="60" rows="10" placeholder="${correctView.b_content}"></textarea>
										</div>
										<!-- 수정완료 버튼 -->
										<div class="text-center">
											<button id="correctSubmit" type="submit" class="btn btn-primary">수정하기</button>
										</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>


</body>
</html>