<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.Date"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
    
<%-- 포매팅 태그 라이브러리를 사용하기 위해 반드시 선언 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  	request.setCharacterEncoding("UTF-8");
%>    
<html>
<head>
<meta charset="UTF-8">
<title>포매팅 태그 라이브러리 예제</title>
</head>

<body>
	<h2>fmt의 number 태그를 이용한 숫자 포맷팅 예제.</h2>
   	<c:set var="price" value="100000000" />
   	<fmt:formatNumber value="${price}" type="number" var="priceNumber" />
    통화로 표현 시 :
    
    <%-- currencySymbol="￦" : 숫자를 원화로 표시 --%>
    <%-- groupingUsed="true" : 세 자리 숫자마다 콤마(,)로 표시. 설정하지 않으면 기본값이 true --%>
    <fmt:formatNumber type="currency" currencySymbol="￦" value="${price}" groupingUsed="true" /><br>
    
    퍼센트로 표현 시 : 
    
    <%-- groupingUsed="false" : groupingUsed가 false이므로 세 자리 마다 콤마(,)가 표시되지 않음 --%>
    <fmt:formatNumber value="${price}" type="percent" groupingUsed="false" /><br>
    
    <%-- <fmt:formatNumber> 태그에서 var 속성에 정한 변수 이름으로 표현 언어에서 출력 --%>
    일반 숫자로 표현 시 : ${priceNumber}<br>
    
	<h2>formatDate 예제</h2> 
    <c:set var="now" value="<%=new Date() %>" />
    <fmt:formatDate value="${now}" type="date" dateStyle="full" /><br>
    <fmt:formatDate value="${now}" type="date" dateStyle="short" /><br>
    <fmt:formatDate value="${now}" type="time" /><br>
    <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full" /><br>
    
    <%-- <fmt:formatDate> 태그의 pattern 속성에 출력한 날짜 포맷을 지정 --%>
    <fmt:formatDate value="${now}" pattern="YYYY-MM-dd :hh:mm:ss" /><br>
     
    <br><br>
    한국 현재 시간:
    <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full" /><br><br>
	
	<%-- 뉴욕 시간대로 변경 --%>
    <fmt:timeZone value="America/New York">
    	뉴욕 현재 시간:<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
    </fmt:timeZone>
</body>
</html>