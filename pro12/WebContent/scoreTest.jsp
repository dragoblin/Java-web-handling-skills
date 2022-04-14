<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	// 전송된 시험 점수를 가져옴
	int score=Integer.parseInt(request.getParameter("score"));
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점수 출력창</title>
</head>
<body>
	<h1>시험점수 <%=score %>점</h1><br>
<%
	if (score>=90) {	// 90점 이상이면 A를 출력
%>
   		<h1>A학점입니다.</h1>
<%
 	} else if (score>=80 && score<90) {	// 80~90점 사이면 B를 출력
%>
    	<h1> B학점입니다.</h1>
<%
   	} else if (score>=70 && score<80) {	// 70~80점 사이면 C를 출력
%>
   		<h1> C학점입니다.</h1>
<%
   	} else if (score>=60 && score<70) {	// 60~70점 사이면 D를 출력
%>
   		<h1> D학점입니다.</h1>
<%
   	} else {	// 그 외 점수는 F를 출력
%>
   		<h1> F학점입니다.</h1>
<%
   	}
%>
	<br>
	<a href="scoreTest.html">시험점수입력</a>
</body>
</html>