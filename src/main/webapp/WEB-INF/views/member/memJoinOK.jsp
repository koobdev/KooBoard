<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>Join Success</title>
</head>
<% 
	String cp = request.getContextPath();
%>
<body>
<script type="text/javascript">
	var cp = "<%=cp%>";
	alert("회원가입 성공!");
	window.location.href = cp + "/memLoginForm"
</script>
</body>
</html>
