package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = "", pwd = "" ;
        Boolean isLogon = false;
        // �̹� ������ �����ϸ� ������ ��ȯ�ϰ�, ������ null�� ��ȯ
        HttpSession session =  request.getSession(false);			
        if (session != null) {	// ���� ������ �����Ǿ� �ִ��� Ȯ��
        	// isLogOn �Ӽ��� ������ �α��� ���¸� Ȯ��
        	isLogon = (Boolean)session.getAttribute("isLogon");
        	// isLogOn�� true�� �α��� �����̹Ƿ� ȸ�� ������ �������� ǥ��
        	if (isLogon == true) { 
        		id = (String)session.getAttribute("login.id");
        		pwd = (String)session.getAttribute("login.pwd");
        		out.print("<html><body>");
        		out.print("���̵�: " + id + "<br>");
        		out.print("��й�ȣ: " + pwd + "<br>");
        		out.print("</body></html>");
        	} else {	// �α��� ���°� �ƴϸ� �α���â���� �̵�
        		response.sendRedirect("login3.html");
        	}
        } else {	// ������ �������� �ʾ����� �α���â���� �̵�
            response.sendRedirect("login3.html");
        }
	}
}