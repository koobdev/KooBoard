<%@page import="java.util.Date"%>
<%@page import="javax.tools.JavaCompiler"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.Kcompany.Kboard.vo.MemberVO"%>
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
<!-- 부트스트랩4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<%
	String time = "2020-01-01 12:00:00";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date date = sdf.parse(time);
	System.out.println("gpsdate :" + date);
	Timestamp tsp = new Timestamp(date.getTime());
%>

	<!-- 메인 -->
	<div class="container text-center">
		<h2 align="center"> 글 쓰기 </h2>
		<div class="text-right">
			<form>
				<p>${member.memId}님 </p>&nbsp;&nbsp;<span class="bar">|</span> &nbsp;&nbsp;
				<button onclick="location.href='openList'" type="button" class="btn btn-primary"> 글 목록 </button>
				<button onclick="location.href='memLogout'" type="button" class="btn btn-danger"> 로그아웃 </button>
			</form>
		</div><br/><br/>
	</div>
	<div class="container">
	
	<div class="row">
		<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="card">
					<!-- 작성글 헤더(글 제목, 글 정보) -->
					<form id="write" action="writeBoard" method="post">
						<div class="card-header">
							<input id="title" name="b_title" type="text" class="form-control">
						</div>
						<!-- 작성글 바디(글 내용) -->
						<div class="card-body">
							<input type="hidden" id="b_memId" name="b_memId" value="${member.memId}">
							<div class="well">
								<textarea id="content" name="b_content" class="form-control" cols="60" rows="10"></textarea>
							</div><br/>
							<!-- 수정완료 버튼 -->
							<div class="text-center">
								<button id="writeSubmit" type="submit" class="btn btn-primary">완료</button>
								<input type="button" class="btn btn-warning" onclick="location.href='openList'" value="취소"/>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>







