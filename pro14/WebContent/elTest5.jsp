<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>

<%-- 유즈빈(useBean)을 생성 --%>
<jsp:useBean id="m1" class="sec01.ex01.MemberBean" scope="page"/>

<%-- 빈의 name 속성에 값을 설정 --%>
<jsp:setProperty name="m1" property="name" value="이순신"/>

<%-- ArrayList 객체를 빈으로 생성 --%>
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page"/>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어의 여러 가지 연산자들</title>
</head>
<body>
	empty 연산자
	
 	<h2>
 		<%-- m1의 name 속성에 값이 설정되어 있으므로 false를 반환 --%>
	   	\${empty m1} : ${empty m1} <br>
	   	<%-- true를 반환 --%>
	   	\${not empty m1} : ${not empty m1} <br><br>
		
		<%-- ArrayList 객체인 m2는 비어 있으므로 true를 반환 --%>
	   	\${empty m2} : ${empty m2} <br>
	   	<%-- false를 반환 --%>
	   	\${not empty m2} : ${not empty m2} <br><br>
	
		<%-- 문자열에 대해 false를 반환 --%>
	   	\${empty "hello"} : ${empty "hello"}<br>
	   	<%-- null은 true를 반환 --%>
	   	\${empty null} : ${empty null} <br>
	   	<%-- 빈 문자열은 true를 반환 --%>
	   	\${empty ""} : ${empty ""} <br>
	</h2>
</body>
</html>