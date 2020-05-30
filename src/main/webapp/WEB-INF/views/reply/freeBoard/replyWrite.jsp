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
<html lang="ko">
<head>
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
	<div class="container-fluid text-center">
		<div class="well text-center">	
			<form id="replyWrite" action="replyWrite" method="post">
				<input type="hidden" name="r_index" value=0>
				<input type="hidden" name="r_memId" value="<%=sessionId%>">
				<input type="hidden" name="r_createDate" value="<%=tsp%>">
				<input type="hidden" name="b_index" value="${view.b_index}">
				<table class="table">
					<thead align="center">
						<tr>
							<th>댓글 작성</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<input type="text" name="r_content" placeholder="내용을 입력하세요." style="width:120%">
							</td>		
							<td align="right">
								<button type="submit" class="btn btn-outline-success btn-sm">댓글 등록</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>