<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>Correct Success</title>
</head>
<% 
	String cp = request.getContextPath();
%>
<body>
<script type="text/javascript">
	var cp = "<%=cp%>";
	alert("회원정보 수정 성공");
	window.location.href = cp + "/"
</script>
</body>
</html>
