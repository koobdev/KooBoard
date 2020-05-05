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
<!-- 부트스트랩3 -->
<link rel="stylesheet" href="<%=cp%>/resources/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="<%=cp%>/resources/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container text-center">
	<section>
		<div class="row">
			<!-- 메인 -->
			<div class="col-sm-offset text-left">
				<h2 align="center"> 글 쓰기 </h2>
					<div class="text-right">
						<form>
							<%
								MemberVO member = (MemberVO)session.getAttribute("member");
								String sessionId = member.getMemId();
							%>
							<%=sessionId%>님 &nbsp;&nbsp;<span class="bar">|</span> &nbsp;&nbsp;
							<button onclick="location.href='openList'" type="button" class="btn btn-primary"> 글 목록 </button>
							<button onclick="location.href='memLogout'" type="button" class="btn btn-danger"> 로그아웃 </button>
						</form>
					</div><br/><br/>
				<div class="row">
					<div class="panel-group">
						<div class="panel panel-default">
							<!-- 작성글 헤더(글 제목, 글 정보) -->
							<form id="write" action="writeBoard" method="post">
								<div class="panel-heading">
									<input id="title" name="b_title" type="text" class="form-control">
								</div>
								
								<%
									String time = "2020-01-01 12:00:00";
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
									Date date = sdf.parse(time);
									System.out.println("gpsdate :" + date);
									Timestamp tsp = new Timestamp(date.getTime());
								%>
								<!-- 작성글 바디(글 내용) -->
								<div class="panel-body">
									<input type="hidden" id="b_index" name="b_index" value=0>
									<input type="hidden" id="b_hit" name="b_hit" value=0>
									<input type="hidden" id="b_recommand" name="b_recommand" value=0>
									<input type="hidden" id="b_replyCount" name="b_replyCount" value=0>
									<input type="hidden" id="b_createDate" name="b_createDate" value="<%=tsp%>">
									<input type="hidden" id="b_memId" name="b_memId" value="user">
									
										<div class="well">
											<textarea id="content" name="b_content" class="form-control" cols="60" rows="10"></textarea>
										</div>
										<!-- 수정완료 버튼 -->
										<div class="text-center">
											<button id="correctSubmit" type="submit" class="btn btn-primary">완료</button>
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