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
   	<title>글보기</title>
   	<script type="text/javascript">
      	function backToList(obj){
		    obj.action="${contextPath}/board/listArticles.do";
	    	obj.submit();
	  	}
	</script>
</head>
<body>
  	<form name="frmArticle" method="post" enctype="multipart/form-data">
	  	<table border="0" align="center">
	  		<tr>
	   			<td width="150" align="center" bgcolor="#FF9933">
	      			글번호
	   			</td>
	   			<td>
	    			<input type="text" value="${article.articleNO}" disabled />
	    			<input type="hidden" name="articleNO" value="${article.articleNO}" />
	   			</td>
	  		</tr>
	  		<tr>
	   			<td width="150" align="center" bgcolor="#FF9933">
	      			작성자 아이디
	   			</td>
	   			<td>
	    			<input type="text" value="${article.id}" name="id" disabled />
	   			</td>
	  		</tr>
	  		<tr>
	   			<td width="150" align="center" bgcolor="#FF9933">
	      			제목 
	   			</td>
	   			<td>
	   				<!-- 글 제목을 표시 -->
	    			<input type="text" value="${article.title}" name="title" id="i_title" disabled />
	   			</td>   
	  		</tr>
	  		<tr>
	   			<td width="150" align="center" bgcolor="#FF9933">
	      			내용
	   			</td>
	   			<td>
	   				<!-- 글 내용을 표시 -->
	    			<textarea rows="20" cols="60" name="content" id="i_content" disabled />${article.content}</textarea>
	   			</td>  
	  		</tr>
	 		
	 		<%-- imageFileName 값이 있으면 이미지를 표시 --%>
			<c:if test="${not empty article.imageFileName && article.imageFileName!='null'}">  
				<tr>
	   				<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">
	      				이미지
	   				</td>
	   				<td>
	   					<!-- <hidden> 태그에 원래 이미지 파일 이름을 저장 -->
	     				<input type="hidden" name="originalFileName" value="${article.imageFileName}" />
	     				
	     				<!-- FileDownloadController 서블릿에 이미지 파일 이름과 글 번호를 전송해
	     					 이미지를 <img> 태그에 표시 -->
	    				<img src="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO}" id="preview" /><br>
	    				
	   				</td>   
	  			</tr>  
	  			<tr>
	    			<td>
	       				<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);" />
	    			</td>
	  			</tr>
	 		</c:if>
	  		<tr>
		   		<td width="20%" align="center" bgcolor="#FF9933">
		      		등록일자
		   		</td>
		   		<td>
		    		<input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
		   		</td>   
	  		</tr>
	  		<tr id="tr_btn_modify">
		   		<td colspan="2" align="center">
		       		<input type=button value="수정하기" onClick="fn_modify_article(frmArticle)">
	           		<input type=button value="취소" onClick="backToList(frmArticle)">
		   		</td>   
	  		</tr>
	    
	  		<tr id="tr_btn">
	   			<td colspan=2 align="center">
	     		<%-- 
	     			<c:if test="${member.id == article.id}">
		    			<input type=button value="수정하기" onClick="fn_enable(this.form)">
		    			<input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
		  			</c:if> 
		  		--%>
		  			<input type=button value="수정하기" onClick="fn_enable(this.form)">
		  			<input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
		  			<input type=button value="리스트로 돌아가기" onClick="backToList(this.form)">
		  			<input type=button value="답글쓰기" onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
	   			</td>
	  		</tr>
	 	</table>
 	</form>
</body>
</html>