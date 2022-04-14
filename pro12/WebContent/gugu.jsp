<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
  	int dan=Integer.parseInt(request.getParameter("dan"));	// 전송된 단수를 구함
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 출력창</title>
</head>
<body>
	<table border=1 width=800 >
    	<tr align=center bgcolor="#FFFF66"> 
        	<!-- 전송된 단수를 출력 -->
        	<td colspan=2><%=dan %>단 출력</td>
     	</tr>
<%
	for (int i=1; i<10; i++) {	// for 반복문을 이용해 테이블의 각 행에 연속해서 구구단을 출력
%> 
	    <tr align=center> 
	    	<td width=400> 
	    		<%=dan %> * <%=i %>    
	  		</td>
	  		<td width=400>
	  	    	<%=i*dan %>
	  		</td>
	  	</tr>
<%
	}
%>
	</table>
</body>
</html>