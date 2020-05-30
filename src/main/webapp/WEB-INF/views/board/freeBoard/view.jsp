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
<title>게시 글</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String cp = request.getContextPath(); %>
<!-- 부트스트랩3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 메인 -->
<div class="container text-center">
	<h2> 자유 게시판 </h2>
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
</div>
<div class="container">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div class="card">
				<!-- 작성글 헤더(글 제목, 글 정보) -->
				<div class="card-header" >
					<h3>${view.b_title}</h3>
					<div id="boardInfo" class="text-right">
						번호 ${view.b_index}
						<span class="bar">|</span>
						작성자 ${view.b_memId}
						<span class="bar">|</span>
						작성일 <fmt:formatDate pattern="yyyy-MM-dd" value="${view.b_createDate}" />
						<span class="bar">|</span>
						조회 ${view.b_hit}
						<span class="bar">|</span>
						추천 ${view.b_recommend}
						<span class="bar">|</span>
					</div>
				</div>
				
				<!-- 작성글 바디(글 내용) -->
				<div class="card-body">
					<!-- 글 수정 버튼 -->
					<div class="text-right">
						<c:set var="sessionId" value="<%=sessionId%>"/>
						<c:if test="${view.b_memId == sessionId}">
							<button type="button" class="btn btn-success" onclick="location.href='correctBoardForm?b_index=${view.b_index}'">글 수정</button>
							<button type="button" class="btn btn-danger" onclick="location.href='deleteBoard?b_index=${view.b_index}'">글 삭제</button>
						</c:if>
					</div><br/>
					
					<div class="well">
						<p>${view.b_content}
					</div>
					<!-- 추천 버튼 -->
					<div class="text-center">
						<form id="recommend" action="recommend?b_index=${view.b_index}" method="post">
							<button id="recommendSubmit" type="submit" class="btn btn-primary">추천 ${view.b_recommend}</button>
						</form><br/><br/>
					</div><br/><br/><br/>
					<!-- 작성글 댓글 -->
					<%
						String time = "2020-01-01 12:00:00";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						Date date = sdf.parse(time);
						System.out.println("gpsdate :" + date);
						Timestamp tsp = new Timestamp(date.getTime());
					%>
				</div>	
				
				<!-- 댓글 표시(기본화면) -->
				<div class="card-footer">
					<div id="show1">
						<div class="text-right"> 
							<button id="writeReply" class="btn btn-outline-secondary btn-sm">댓글쓰기</button>
						</div><br/>
						<div class="well text-center">
							<div class="text-left">댓글 수  ${viewReplyCnt}</div><br/>
								<table class="table">
								<thead>
									<tr>
										<th>작성자</th>
										<th>내용</th>
										<th>작성일</th>
										<th></th>
									</tr>
								</thead>
								<tbody align="left">
									<c:forEach items="${viewReply}" var="viewReply">
									<tr>
										<td>${viewReply.r_memId}</td>
										<td>${viewReply.r_content}</td>
										<td>${viewReply.r_createDate}</td>
										<!-- 댓글 작성자와 현재 로그인 이용자의 아이디가 같으면 버튼생성 -->
										<c:set var="sessionId" value="<%=sessionId%>"/>
										<c:if test="${viewReply.r_memId == sessionId}">
											<td align="right">
												<button id="writeCorrect" value="${viewReply.r_index}" class="btn btn-outline-danger btn-sm">댓글 수정</button>
												<button type="submit" class="btn btn-outline-danger btn-sm" onclick="location.href='replyDelete?r_index=${viewReply.r_index}&b_index=${view.b_index}'">댓글 삭제</button>
											</td>
										</c:if>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<ul class="pagination justify-content-center">
								<c:if test="${paging.prev}">
									<li class="page-item">
										<a class="page-link" href="openContent?b_index=${view.b_index}&page=${paging.startNum-1}">이전</a>
									</li>
								</c:if>
								<c:forEach begin="${paging.startNum}" end="${paging.endNum}" var="index">
									<li class="page-item" <c:out value="${paging.pc.page == index ? 'class=active' : ''}"/>>
										<a class="page-link" href="openContent?b_index=${view.b_index}&page=${index}">${index}</a>
									</li>
								</c:forEach>
						
								<c:if test="${paging.next && paging.endNum > 0}">
									<li class="page-item">
										<a class="page-link" href="openContent?b_index=${view.b_index}&page=${paging.endNum+1}">다음</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>	
					
					<!-- 댓글 쓰기 -->
					<div id="show2">
						<jsp:include page="../../reply/freeBoard/replyWrite.jsp" flush="false"/>
					</div>
					
					<!-- 댓글 수정 -->
					<div id="show3">
						<jsp:include page="../../reply/freeBoard/replyCorrect.jsp" flush="false"/>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-2"></div>
	</div>
</div>
	
<script type="text/javascript">	
	var val;
	$(document).ready(function() {
		$('#show1').show(); //페이지를 로드할 때 표시할 요소
		$('#show2').hide(); //페이지를 로드할 때 숨길 요소
		$('#show3').hide(); //페이지를 로드할 때 숨길 요소
			$('#writeReply').click(function(){
				$ ('#show1').hide(); 
				$ ('#show3').hide(); 
				$ ('#show2').show(); // show2 표시
				return false;
			});
			$('#writeCorrect').click(function(){
				
				val = parseInt($(this).attr("value"));
				document.replyCorrect.r_index.value = val;
				
				$ ('#show1').hide(); 
				$ ('#show2').hide(); 
				$ ('#show3').show(); // show3 표시
				return false;
			});
			$('#writeReply_back').click(function(){
				$ ('#show2').hide(); 
				$ ('#show3').hide(); 
				$ ('#show1').show(); // 처음화면으로 돌아감
				return false;
			});	
			$('#writeCorrect_back').click(function(){
				$ ('#show2').hide(); 
				$ ('#show3').hide(); 
				$ ('#show1').show();  // 처음화면으로 돌아감 
				return false;
			});	
		});
</script>

</body>
</html>