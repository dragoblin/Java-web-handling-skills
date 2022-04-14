<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding( "utf-8" );
   String user_id = request.getParameter("user_id");
   String user_pw = request.getParameter("user_pw");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과출력창</title>
</head>
<body>
<%
	// ID가 정상적으로 입력되었는지 체크
	if(user_id==null || user_id.length()==0) {
%>
	<!-- ID를 입력하지 않았을 경우 다시 로그인창으로 이동 -->
	아이디를 입력하세요.<br>
	<a href="/pro12/login.html">로그인하기</a>
<%
	} else {
%>
	<!-- ID를 정상적으로 입력했을 경우 메세지를 표시 -->
	<h1> 환영합니다. <%=user_id %> 님!!!</h1>
<%
	}
%>
</body>
</html>