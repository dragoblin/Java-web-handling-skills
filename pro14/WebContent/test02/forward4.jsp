<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  	request.setCharacterEncoding("utf-8");
  	request.setAttribute("id","hong");
  	request.setAttribute("pwd", "1234");
  	session.setAttribute("name", "홍길동");
  	application.setAttribute("email", "hong@test.com");
  	
  	// request에 address 속성 이름으로 바인딩
  	//request.setAttribute("address","서울시 강남구");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward4</title>
</head>
<body>
	<jsp:forward page="member4.jsp" />
</body>
</html>