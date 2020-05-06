<%@page import="com.Kcompany.Kboard.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>글 목록</title>
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
	
	<h2> 글 목록 </h2>
	<div class="text-right">
		<form>
			<p>${member.memId}님</p> &nbsp;&nbsp;<span class="bar">|</span> &nbsp;&nbsp;
			<button onclick="location.href='writeForm'" type="button" class="btn btn-success"> 글 작성 </button>
			<button onclick="location.href='openList'" type="button" class="btn btn-primary"> 글 목록 </button>
			<span class="bar">|</span>
			<button onclick="location.href='<%=cp%>/'" type="button" class="btn btn-info"> 회원정보 </button>
			<button onclick="location.href='memLogout'" type="button" class="btn btn-danger"> 로그아웃 </button>
		</form>
	</div><br/><br/>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>조회수</th>
				<th>추천수</th>
				<th>댓글수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr>
				<td class="ellipsis">${list.b_index}</td>
				<td class="ellipsis">
				<a href="openContent?b_index=${list.b_index}">${list.b_title}</a>
				<td>${list.b_content}</td>
				<td>${list.b_hit}</td>
				<td>${list.b_recommand}</td>
				<td>${list.b_replyCount}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<div class="col-md-offset">
		<ul class="pagination">
			<c:if test="${paging.prev}">
				<li>
					<a href="openList?page=${paging.startNum-1}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${paging.startNum}" end="${paging.endNum}" var="index">
				<li <c:out value="${paging.pc.page == index ? 'class=active' : ''}"/>>
					<a href="openList?page=${index}">${index}</a>
				</li>
			</c:forEach>
	
			<c:if test="${paging.next && paging.endNum > 0}">
				<li>
					<a href="openList?page=${paging.endNum+1}">다음</a>
				</li>
			</c:if>
		</ul>
	</div>
		
	</section>
</div>
</body>
</html>