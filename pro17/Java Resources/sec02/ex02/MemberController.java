package sec02.ex02;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 브라우저에서 요청 시 두 단계로 요청이 이루어짐
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	
	throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		if (action == null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/member/listMembers.do";
		} else if (action.equals("/memberForm.do")) {
			nextPage = "/test03/memberForm.jsp";
			
		  // 회원 수정창 요청 시 ID로 회원 정보를 조회한 후 수정창으로 포워딩
		} else if (action.equals("/modMemberForm.do")) {
		     String id = request.getParameter("id");
		     
		     // 회원 정보 수정창을 요청하면서 전송된 ID를 이용해 수정 전 회원 정보를 조회
		     MemberVO memInfo = memberDAO.findMember(id);
		     
		     // request에 바인딩하여 회원 정보 수정창에 수정하기 전 회원 정보를 전달
		     request.setAttribute("memInfo", memInfo);
		     
		     nextPage="/test03/modMemberForm.jsp";
		     
		  // 테이블의 회원 정보를 수정
		} else if (action.equals("/modMember.do")) {
			
			 // 회원 정보 수정창에서 전송된 수정 회원 정보를 가져온 후 MemberVO 객체 속성에 설정
		     String id = request.getParameter("id");
		     String pwd = request.getParameter("pwd");
		     String name = request.getParameter("name");
	         String email = request.getParameter("email");
	         
		     MemberVO memberVO = new MemberVO(id, pwd, name, email);
		     memberDAO.modMember(memberVO);
		     
		     // 회원 목록창으로 수정 작업 완료 메시지를 전달
		     request.setAttribute("msg", "modified");
		     
		     nextPage="/member/listMembers.do";
		     
		  // 회원 ID를 SQL문으로 전달해 테이블의 회원 정보를 삭제
		} else if (action.equals("/delMember.do")) {
			
			 // 삭제할 회원 ID를 받아옴
		     String id = request.getParameter("id");
		     
		     memberDAO.delMember(id);
		     
		     // 회원 목록창으로 삭제 작업 완료 메시지를 전달
		     request.setAttribute("msg", "deleted");
		     
		     nextPage="/member/listMembers.do";
		}else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}