<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   	request.setCharacterEncoding("utf-8");
%> 

<%!
	// login.jsp로 전달할 오류 메세지를 '선언!'
  	String msg = "아이디를 입력하지 않았습니다. 아이디를 입력해 주세요.";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>
<%
	// 로그인 시 입력한 이름을 가져옴
   	String userID = request.getParameter("userID");
   	if (userID.length()==0) {
%>

<%-- 이름을 입력하지 않았을 경우 <jsp:param> 액션 태그를 이용해 오류 메세지를 login.jsp로 전달 --%>
   	<jsp:forward  page="login2.jsp" >		
      	<jsp:param name="msg" value="<%=msg %>" />
   	</jsp:forward>

<%	
  	}
%>
</body>
</html>