package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")	//������ ���� �̸��� login
public class LoginServlet extends HttpServlet{
	public void init() throws ServletException {
		System.out.println("init �޼��� ȣ��");
	}
	
	/* HttpServletRequest request : �� ���������� ������ ������ ��Ĺ �����̳ʰ�
	                  HttpServletRequest ��ü�� ������ �� doGet()���� �Ѱ��� */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	// ���۵� �����͸� UTF-8�� ���ڵ�
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		// getParameter()�� �̿��� <input> �±��� name �Ӽ� ������ ���۵� value�� �޾ƿ�
		System.out.println("���̵�:" + user_id);
		System.out.println("��й�ȣ:" + user_pw);
	}
	
	public void destroy() {
		System.out.println("destroy �޼��� ȣ��");
	}
}