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
<title>댓글 작성</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩3 -->
<link rel="stylesheet" href="<%=cp%>/resources/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="<%=cp%>/resources/js/bootstrap.min.js"></script>

</head>
<body>

	<%
		MemberVO member = (MemberVO)session.getAttribute("member");
		String sessionId = member.getMemId();
	%>
	<%
		String time = "2020-01-01 12:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = sdf.parse(time);
		System.out.println("gpsdate :" + date);
		Timestamp tsp = new Timestamp(date.getTime());
	%>

	<div class="text-right"> 
		<button id="writeReply_back" class="btn btn-outline-secondary btn-sm" onclick="click_writeReply_back()">뒤로가기</button>
	</div><br/>
	<div class="well text-center">
		<form id="replyWrite" action="replyWrite" method="post">
			<input type="hidden" name="r_index" value=0>
			<input type="hidden" name="r_memId" value="<%=sessionId%>">
			<input type="hidden" name="r_createDate" value="<%=tsp%>">
			<input type="hidden" name="b_index" value="${view.b_index}">
				<table class="table">
				<thead>
					<tr>
						<th>댓글 작성</th>
					</tr>
				</thead>
				<tbody align="left">
					<tr>
						<td><input type="text" name="r_content" placeholder="내용을 입력하세요." size="120"></td>															
						<td align="right"><button type="submit" class="btn btn-outline-success btn-sm">댓글 등록</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>