<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  	request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
 
<head>
	<meta charset="UTF-8">
	<title>글쓰기창</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	
		// 제이쿼리를 이용해 이미지 파일 첨부 시 미리 보기 기능을 구현 
   		function readURL(input) {
      		if (input.files && input.files[0]) {
	      		var reader = new FileReader();
	      		reader.onload = function (e) {
	        	$('#preview').attr('src', e.target.result);
    	      	}
        	 	reader.readAsDataURL(input.files[0]);
      		}
  		}
		
  		function backToList(obj){
    		obj.action = "${contextPath}/board/listArticles.do";
    		obj.submit();
  		}
	</script>
 	<title>새 글 쓰기창</title>
</head>
<body>
	<h1 style="text-align:center">새글 쓰기</h1>
	
	<!-- action 값을 /addArticle.do로 해서 '새 글 등록'을 요청 -->
	<!-- enctype="multipart/form-data" : 파일 업로드 기능을 위한 것 -->
  	<form name="articleForm" method="post" action="${contextPath}/board/addArticle.do" 
  	 enctype="multipart/form-data">
  	 
    	<table border=0 align="center">
     		<tr>
	   			<td align="right">글제목: </td>
	   			<td colspan="2"><input type="text" size="67" maxlength="500" name="title" /></td>
	 		</tr>
	 		<tr>
				<td align="right" valign="top"><br>글내용: </td>
				<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
     		</tr>
     		<tr>
        		<td align="right">이미지파일 첨부: </td>
	     		<td><input type="file" name="imageFileName" onchange="readURL(this);" /></td>
	     		
	     		<!-- 첨부한 이미지를 미리 보기로 표시 -->
         		<td><img id="preview" src="#" width=200 height=200/></td>
         		
	 		</tr>
	 		<tr>
	    		<td align="right"> </td>
	    		<td colspan="2">
	       			<input type="submit" value="글쓰기" />
	       			<input type=button value="목록보기"onClick="backToList(this.form)" />
	    		</td>
     		</tr>
    	</table>
  	</form>
</body>
</html>