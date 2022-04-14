<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
  	request.setCharacterEncoding("UTF-8");
%>   
 
<html>
<head>

	<!-- static 폴더의 자바스크립트 파일 위치를 지정 -->
 	<script src="${contextPath}/js/scriptTest.js" type="text/javascript"></script>
 	
 	<meta charset="utf-8">
	<title>hello.JSP 페이지</title>
</head>
<body>
  	안녕하세요<br>
  	<h2>${message}</h2>
  	
  	<!-- static 폴더의 이미지 파일 위치를 지정 -->
  	<img width=200 height=200 src="${contextPath}/image/duke3.png" /><br/>
  	
  	<input type="button" name="테스트" value="테스트" onClick="test();">
</body>
</html>