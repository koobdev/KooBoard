<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>Logout Success</title>
</head>
<% 
	String cp = request.getContextPath();
%>
<body>
<script type="text/javascript">
	var cp = "<%=cp%>";
	alert("로그아웃 성공. 안녕히가세요!");
	window.location.href = cp + "/memLoginForm"
</script>
</body>
</html>
