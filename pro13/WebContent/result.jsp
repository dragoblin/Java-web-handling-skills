<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   	request.setCharacterEncoding("utf-8");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>
<%
   	String userID = request.getParameter("userID");
   	if (userID.length()==0) {
   		
   		// RequestDispatcher를 사용해 포워딩하지 않아도 됨
    	/*
      	RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");  
      	dispatch.forward(request, response);
    	*/
    	
%>

	<%-- ID를 입력하지 않았으면 다시 <jsp:forward> 태그를 사용해 로그인창으로 포워딩 --%>
   	<jsp:forward page="login.jsp" />
   	
<%	
  	}
%>
  	<h1>환영합니다 <%=userID %>님!! </h1>
</body>
</html>