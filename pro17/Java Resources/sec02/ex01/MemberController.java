package sec02.ex01;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ���������� ��û �� �� �ܰ�� ��û�� �̷����
//@WebServlet("/member/*")
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
		
		// URL���� ��û���� ������
		String action = request.getPathInfo();
		
		System.out.println("action:" + action);
		
		// ���� ��û�̰ų� action ���� /memberList.do �̸� ȸ�� ����� ���
		if (action == null || action.equals("/listMembers.do")) {
			
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			
			// test02 ������ listMember.jsp�� ������
			nextPage = "/test02/listMembers.jsp";
			
		  // action ���� /addMember.do �̸� ���۵� ȸ�� ������ �����ͼ� ���̺� �߰�
		} else if (action.equals("/addMember.do")) {
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			
			// ȸ�� ��� �� �ٽ� ȸ�� ����� ���
			nextPage = "/member/listMembers.do";

		  // action ���� /memberForm.do �̸� ȸ�� ����â�� ȭ�鿡 ���
		} else if (action.equals("/memberForm.do")) {
			
			// test02 ������ memberForm.jsp�� ������
			nextPage = "/test02/memberForm.jsp";
			
		  // �� �� �ٸ� action ���� ȸ�� ����� ���
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}
		
		// nextPage�� ������ ��û������ �ٽ� ������ ��û
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		
		dispatch.forward(request, response);
	}

}