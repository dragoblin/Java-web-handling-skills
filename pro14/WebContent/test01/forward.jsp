<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  	request.setCharacterEncoding("utf-8");
	// 회원 가입창의 request에 대해 다시 주소 정보를 바인딩
  	request.setAttribute("address","서울시 강남구");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward</title>
</head>
<body>
	<!-- member2.jsp로 포워딩 -->
	<jsp:forward page="member2.jsp"></jsp:forward>
</body>
</html>