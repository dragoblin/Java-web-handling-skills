package com.spring.ex04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		String action = request.getParameter("action");
		String nextPage = "";

		if (action== null || action.equals("listMembers")) {
			List<MemberVO> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
			
		  /* 검색 조건이 selectMemberByld이면 전송된 값을 getParameter()로 가져온 후 
		   * SQL문의 조건식에서 id의 조건 값으로 전달 */
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test03/memberInfo.jsp";
			
		  /* 검색 조건이 selectMemberByPwd이면 전송된 값을 getParameter()로 가져온 후
		   * SQL문의 조건식 pwd의 조건 값으로 전달 */
		} else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
		} else if(action.equals("insertMember")) {
			String id=request.getParameter("id");
            String pwd=request.getParameter("pwd");
            String name=request.getParameter("name");
            String email = request.getParameter("email");
            memberVO.setId(id);
            memberVO.setPwd(pwd);
            memberVO.setName(name);
            memberVO.setEmail(email);
            
            // 회원 가입창에서 전송된 회원 정보를 MemberVO에 설정한 후 insertMember() 메서드로 전달
            dao.insertMember(memberVO);
            
            nextPage="/mem4.do?action=listMembers";
		} else if(action.equals("insertMember2")) {
	           String id=request.getParameter("id");
	           String pwd=request.getParameter("pwd");
	           String name=request.getParameter("name");
	           String email = request.getParameter("email");
	           
	           /* 회원 가입창에서 전송된 회원 정보를 HashMap에 key/value로 저장한 후
	            * MemberDAO의 insertMember2() 인자로 전달 */
	           Map<String, String> memberMap = new HashMap<String, String>();
	           memberMap.put("id", id);
	           memberMap.put("pwd", pwd);
	           memberMap.put("name", name);
	           memberMap.put("email", email);
	           dao.insertMember2(memberMap);
	           
	           nextPage="/mem4.do?action=listMembers";
		} else if(action.equals("updateMember")) {
	          String id=request.getParameter("id");
	          String pwd=request.getParameter("pwd");
	          String name=request.getParameter("name");
	          String email = request.getParameter("email");
	          memberVO.setId(id);
	          memberVO.setPwd(pwd);
	          memberVO.setName(name);
	          memberVO.setEmail(email);
	          
	          /* 회원 수정창에서 전송된 회원 정보를 MemberVO의 속성에 설정한 후
	           * updateMember()를 호출하면서 MemberVO 객체를 전달 */
	          dao.updateMember(memberVO);
	          
	          nextPage="/mem4.do?action=listMembers";     
	    } else if(action.equals("deleteMember")) {
	  	      String id=request.getParameter("id"); // 회원 아이디를 가져옴
	  	      
	  	      // 회원 목록창에서 전달된 ID를 deleteMember() 메서드를 호출하면서 SQL문으로 전달
		      dao.deleteMember(id);
		      
		      nextPage="/mem4.do?action=listMembers";
	    } else if(action.equals("searchMember")) {
	    	
	    	  // 검색창에 입력한 검색 조건을 가져옴
	          String name = request.getParameter("name");
	          String email = request.getParameter("email");
	          
	          memberVO.setName(name);
	          memberVO.setEmail(email);
	          List<MemberVO> membersList = dao.searchMember(memberVO);
	          request.setAttribute("membersList",membersList);
	          nextPage="test03/listMembers.jsp";
	    } else if(action.equals("foreachSelect")) {
	    	
	    	  // ArrayList에 검색할 이름을 저장한 후 SQL문으로 ArrayList를 전달
			  List<String> nameList = new ArrayList<String>();
			  nameList.add("홍길동");
			  nameList.add("차범근");
			  nameList.add("이순신");
			  List<MemberVO> membersList = dao.foreachSelect(nameList);
			  
			  request.setAttribute("membersList",membersList);
			  nextPage="test03/listMembers.jsp";
		} else if(action.equals("foreachInsert")) {
			
			  // 테이블에 추가할 회원 정보를 memList에 저장
	          List<MemberVO> memList = new ArrayList<MemberVO>();
	          memList.add(new MemberVO("m1", "1234", "박길동", "m1@test.com"));
	          memList.add(new MemberVO("m2", "1234", "이길동", "m2@test.com"));
	          memList.add(new MemberVO("m3", "1234", "김길동", "m3@test.com"));
	          
	          // SQL문으로 memList를 전달
	          int result = dao.foreachInsert(memList);
	          
	          nextPage="/mem4.do?action=listMembers";
		} else if(action.equals("selectLike")) {
		      String name = "길동";
			  List<MemberVO> membersList = dao.selectLike(name);
			  request.setAttribute("membersList", membersList);
			  nextPage="test03/listMembers.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);  
		dispatch.forward(request, response);
	}
}