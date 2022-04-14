<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true" %>
<%-- 다른 JSP 페이지에서 예외 발생 시 예외를 처리하는 예외 페이지로 지정 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	====== toString() 내용 ======= <br>
	<!-- exception 내장 객체를 사용해 예외 처리 -->
	<h1><%=exception.toString() %> </h1>
	=============== getMessage()내용 ==========<br>
   	<h1><%=exception.getMessage() %> </h1>
   	============= printStackTrace() 내용 =======<br>
   	<!-- 이클립스 콘솔로 예외 메시지를 출력 -->
   	<h1><% exception.printStackTrace(); %> </h1>
   	
   	<h3>
   		숫자만 입력 가능합니다.다시 시도 하세요.
   		<a href='add.html'>다시하기</a>
   	</h3>
</body>
</html>