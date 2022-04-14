<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
    
<%-- <fmt> 태그를 이용하기 전에 반드시 설정 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  	request.setCharacterEncoding("UTF-8");
%>    

<html>
<head>
<meta charset="UTF-8">
<title>JSTL 다국어 기능</title>
</head>
<body>

	<%-- locale을 영어로 지정 --%>   
	<fmt:setLocale value="en_US" />
	
	<%-- locale을 한글로 지정 --%>	
	<%-- <fmt:setLocale value="ko_KR" /> --%>

	<h1>
		회원정보<br><br>
		
		<%-- resource 패키지 아래 member 프로퍼티 파일을 읽어옴 --%>
 		<fmt:bundle basename="resource.member">
 			
 			<%-- <fmt:message> 태그의 key 속성에 프로퍼티 파일의 key를 지정하여 값(value)을 출력 --%>
  			이름:<fmt:message key="mem.name" /><br>
  			주소:<fmt:message key="mem.address" /><br>
  			직업:<fmt:message key="mem.job" />
  			
		</fmt:bundle>
	</h1>
</body>
</html>