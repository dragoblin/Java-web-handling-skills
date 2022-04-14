<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어의 여러 가지 연산자들</title>
</head>
<body>
	<h2>여러가지 논리연산자</h2>
    <h2>
    	<%-- 연산자 양쪽 값이 true일 때만 true를 반환 --%>
    	\${(10==10) && (20==20)}  : ${(10==10) && (20==20)} <br>
      	\${(10==10) and (20!=20)}  : ${(10==10) and (20!=20)} <br><br>
 		
 		<%-- 연산자 양쪽 값 중 하나라도 true면 true를 반환 --%>
      	\${(10==10) || (20!=30)}  : ${(10==10) || (20==30)} <br>
      	\${(10!=10) or (20!=20)}  : ${(10!=10) or (20!=20)} <br><br>
      	
      	<%-- 비교 연산자의 결과값이 false이므로 true를 출력 --%>
      	\${!(20==10)}  : ${!(20==10)}<br>
      	\${not (20==10)}  : ${not (20==10)}<br><br>
      	
      	<%-- 비교 연산자의 결과값이 true이므로 false를 출력 --%>
      	\${!(20!=10)}  : ${!(20!=10)}<br>
      	\${not (20!=10)}  : ${not (20!=10)}<br><br>       
	</h2>
</body>
</html>