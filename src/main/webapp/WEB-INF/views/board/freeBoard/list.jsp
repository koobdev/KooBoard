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
<!-- 커스텀 css -->
<link rel="stylesheet" href="<%=cp%>/resources/css/boardStlye.css" >
<!-- font-awesome5 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<div id="wrapper">

<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li><a href="openList">자유 게시판</a></li>
   		<li><a href="imageOpenList">이미지 게시판</a></li>
  	</ul>
</div>

<div id="page-content-wrapper">
<div class="container-fluid text-center">
	<div class="row">
		<div class="col-md-2">
			<div class="input-group-prepend">	
				<span class="input-group-text">
					<i class="fas fa-bars" id="menu-toggle" style="cursor:pointer"></i>
				</span>
			</div>
		</div>
		<div class="col-md-8">
			<h2> 자유 게시판 </h2>
			<div class="text-right">
				<!-- 로그인 하지 않고 주소로 직접 접근하는 것을 방지한다. -->
				<c:if test="${empty member}">
					<script> 
						alert("로그인 후 이용해주세요.")
						window.location.href = "<%=cp%>/"
					</script>
				</c:if>
				<c:if test="${!empty member}">
						<p>${member.memId}님</p> &nbsp;&nbsp;<span class="bar">|</span> &nbsp;&nbsp;
						<button onclick="location.href='writeForm'" type="button" class="btn btn-success"> 글 작성 </button>
						<button onclick="location.href='openList'" type="button" class="btn btn-primary"> 글 목록 </button>
						<span class="bar">|</span>
						<form action="<%=cp %>/memModifyForm" method="post" style="display: inline">
					 		<input type="hidden" id="memId" name="memId" value="${member.memId}"/>
					 		<button type="submit" class="btn btn-warning">회원정보 수정</button>
					 	</form>
						<button onclick="location.href='memLogout'" type="button" class="btn btn-danger"> 로그아웃 </button>
				</c:if>
			</div><br/><br/>
	
			<table class="table table-hover" style=TABLE-layout:fixed>
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
					<c:forEach items="${list}" var="listValue">
					<tr onclick="selectRow(${listValue.b_index})" style="cursor:pointer">
						<td>${listValue.b_index}</td>
						<td style="text-overflow : ellipsis; overflow : hidden;">
							<nobr>
								${listValue.b_title}
							</nobr>
						</td>
						<td style="text-overflow : ellipsis; overflow : hidden;">
							<nobr>
								${listValue.b_content}
							</nobr>
						</td>
						<td>${listValue.b_hit}</td>
						<td>${listValue.b_recommend}</td>
						<td>${listValue.b_replyCount}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
			<!-- 게시글 페이징 -->
			<ul class="pagination justify-content-center">
				<c:if test="${paging.prev}">
					<li class="page-item">
						<a class="page-link" href="openList?page=${paging.startNum-1}">이전</a>
					</li>
				</c:if>
				<c:forEach begin="${paging.startNum}" end="${paging.endNum}" var="index">
					<li class="page-item" <c:out value="${paging.pc.page == index ? 'class=active' : ''}"/>>
						<a class="page-link" href="openList?page=${index}">${index}</a>
					</li>
				</c:forEach>
		
				<c:if test="${paging.next && paging.endNum > 0}">
					<li class="page-item">
						<a class="page-link" href="openList?page=${paging.endNum+1}">다음</a>
					</li>
				</c:if>
			</ul>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
</div>

</div>

</body>
<script>
	function selectRow(index){
		window.location.href = "openContent?b_index=" + index
	}
	
	$(document).ready(function(){
		$("#menu-toggle").click(function(e){
			e.preventDefault();
			$("#wrapper").toggleClass("menuDisplayed");
	 	});
	});
</script>
</html>





