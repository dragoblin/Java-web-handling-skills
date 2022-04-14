<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  	request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%-- Map으로 넘어온 속성들의 이름을 다시 간단하게 설정 --%>
<c:set var="article" value="${articleMap.article}" />
<c:set var="imageFileList" value="${articleMap.imageFileList}" />

<head>
   	<meta charset="UTF-8">
   	<title>글보기</title>
   	<style>
   		#tr_file_upload {
       		display:none;
     	}
     	#tr_btn_modify {
       		display:none;
     	}
   	</style>
   	<script src="http://code.jquery.com/jquery-latest.min.js"></script> 
   	<script type="text/javascript">
      	function backToList(obj){
		    obj.action="${contextPath}/board/listArticles.do";
	    	obj.submit();
	  	}
      	
      	// 수정하기 클릭 시 텍스트 박스를 활성화시킴
      	function fn_enable(obj) {
      		
      		// 텍스트 박스의 id로 접근해 disabled 속성을 false로 설정
	   		document.getElementById("i_title").disabled=false;
	   		document.getElementById("i_content").disabled=false;
	   		document.getElementById("i_imageFileName").disabled=false;
	   		document.getElementById("tr_btn_modify").style.display="block";
	   		document.getElementById("tr_file_upload").style.display="block";
	   		document.getElementById("tr_btn").style.display="none";
		}	
   	 	
      	// 수정반영하기 클릭 시 컨트롤러에 수정 데이터를 전송
	   	function fn_modify_article(obj) {
	   		obj.action="${contextPath}/board/modArticle.do";
	   		obj.submit();
	   	}
      	
	   	function fn_remove_article(url,articleNO) {
	   		
	   		// 자바스크립트를 이용해 동적으로 <form> 태그를 생성
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", url);
			
			// 자바스크립트를 이용해 동적으로 <input> 태그를 생성한 후 name과 value를 articleNO와 글 번호로 설정
		    var articleNOInput = document.createElement("input");
		    articleNOInput.setAttribute("type", "hidden");
		    articleNOInput.setAttribute("name", "articleNO");
		    articleNOInput.setAttribute("value", articleNO);
			
		    // 동적으로 생성된 <input> 태그를 동적으로 생성한 <form> 태그에 append
		    form.appendChild(articleNOInput);
		    
		    // <form> 태그를 <body> 태그에 추가(append)한 후 서버에 요청
		    document.body.appendChild(form);
		    form.submit();
		 
		}
	   	
	   	function fn_reply_form(url, parentNO) {
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			
			// 전달된 요청명을 <form> 태그의 action 속성 값에 설정
			form.setAttribute("action", url);
			 
		    var parentNOInput = document.createElement("input");
		     
		    // 함수 호출 시 전달된 articleNO 값을 <input> 태그를 이용해 컨트롤러에 전달
		    parentNOInput.setAttribute("type","hidden");
		    parentNOInput.setAttribute("name","parentNO");
		    parentNOInput.setAttribute("value", parentNO);
			 
		    form.appendChild(parentNOInput);
		    document.body.appendChild(form);
			form.submit();
		}
      	
	   	function readURL(input) {
			if (input.files && input.files[0]) {
		    	var reader = new FileReader();
		        reader.onload = function (e) {
		            $('#preview').attr('src', e.target.result);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		}  
      	
	</script>
</head>
<body>
  	<form name="frmArticle" method="post" action="${contextPath}" enctype="multipart/form-data">
	  	<table border="0" align="center">
	  		<tr>
	   			<td width="150" align="center" bgcolor="#FF9933">
	      			글번호
	   			</td>
	   			<td>
	    			<input type="text" value="${article.articleNO}" disabled />
	    			
	    			<!-- 글 수정 시 글 번호를 컨트롤러로 전송하기 위해 미리 <hidden> 태그를 이용해 글 번호를 저장 -->
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
	  		
	  		<%-- imageFileList가 비어 있지 않으면 이미지를 표시 --%>
	  		<c:if test="${not empty imageFileList && imageFileList!='null'}">	  		
	  			<%-- <forEach> 태그를 이용해 이미지 개수만큼 반복해서 다운로드 --%>
	  			<c:forEach var="item" items="${imageFileList}" varStatus="status">
		    		<tr>
			    		<td width="150" align="center" bgcolor="#FF9933" rowspan="2">
			      			이미지${status.count}
			   			</td>
			   			<td>
			     			<input type= "hidden" name="originalFileName" value="${item.imageFileName}" />
			    			<img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="preview" /><br>
			   			</td>   
			  		</tr>  
			  		<tr>
			    		<td>
			       			<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);" />
			    		</td>
			 		</tr>
				</c:forEach>
 			</c:if>
	 		
	 		<c:choose>
		 		<%-- imageFileName 값이 있으면 이미지를 표시 --%>
				<c:when test="${not empty article.imageFileName && article.imageFileName!='null'}">  
					<tr>
		   				<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">
		      				이미지
		   				</td>
		   				<td>
		   					<!-- 이미지 수정에 대비해 미리 원래 이미지 파일 이름을 <hidden> 태그에 저장 -->
		     				<input type="hidden" name="originalFileName" value="${article.imageFileName}" />
		     				
		     				<!-- FileDownloadController 서블릿에 이미지 파일 이름과 글 번호를 전송해
		     					 이미지를 <img> 태그에 표시 -->
		    				<img src="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO}" id="preview" /><br>
		    				
		   				</td>   
		  			</tr>  
		  			<tr>
		    			<td>
		    				<!-- 수정된 이미지 파일 이름을 전송 -->
		       				<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);" />
		    			</td>
		  			</tr>
		 		</c:when>
		 		
		 		<%-- 첨부 파일이 없는 글을 수정할 때는 파일 업로드가 표시되게 함 --%>
		 		<c:otherwise>
		    		<tr>
				    	<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">
				      		이미지
				    	</td>
				    	<td>
				      		<input type= "hidden" name="originalFileName" value="${article.imageFileName}" />
				    	</td>
			    	</tr>
			    	<tr>
				    	<td>
				       		<img id="preview" /><br>
				       		<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);" />
				    	</td>
			  		</tr>
		 		</c:otherwise>
		 		
		 	</c:choose>
		 	
	  		<tr>
		   		<td width="20%" align="center" bgcolor="#FF9933">
		      		등록일자
		   		</td>
		   		<td>
		    		<input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
		   		</td>   
	  		</tr>
	  		<tr id="tr_btn_modify" align="center">
		   		<td colspan="2">
		       		<input type=button value="수정반영하기" onClick="fn_modify_article(frmArticle)">
	           		<input type=button value="취소" onClick="backToList(frmArticle)">
		   		</td>   
	  		</tr>
	    
	  		<tr id="tr_btn">
	   			<td colspan=2 align="center">
	   			
	   				<!-- 로그인 ID가 작성자 ID와 같은 경우에만 수정하기, 삭제하기 버튼이 표시됨 -->
	   				<c:if test="${member.id == article.id}">
		  				<input type=button value="수정하기" onClick="fn_enable(this.form)">
		  			
		  				<!-- 삭제하기 클릭 시 fn_remove_article() 자바스크립트 함수를 호출하면서 articleNO 을 전달 -->
		  				<input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
					</c:if>
							  			
		  			<input type=button value="리스트로 돌아가기" onClick="backToList(this.form)">
		  			
		  			<!-- 답글쓰기 클릭 시 fn_reply_form() 함수를 호출하면서 요청명과 글 번호를 전달 -->
		  			<input type=button value="답글쓰기" onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
		  			
	   			</td>
	  		</tr>
	 	</table>
 	</form>
</body>
</html>