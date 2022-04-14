<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("utf-8");
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
	// 아이디가 정상적으로 입력되었는지 체크
	if (user_id == null || user_id.length()==0) {
%>
		아이디를 입력하세요.<br>
   		<a href="/pro12 /login.html">로그인하기</a>
<%
 	} else {
 		// ID를 입력한 경우 ID가 admin인지 다시 체크
    	if (user_id.equals("admin")) {
%>
			<!-- ID가 admin이면 관리자창을 나타냄 -->
			<h1>관리자로 로그인 했습니다.</h1>
	   		<form>
	     		<input type=button value="회원정보 삭제하기" />
	     		<input type=button value="회원정보 수정하기" />
	  		</form>
<%
		} else {
%>
    		<h1> 환영합니다. <%=user_id %> 님!!!</h1>
<%
    	}
 	}
%>
</body>
</html>