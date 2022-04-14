package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParmaServlet
 */
@WebServlet(name = "InitParamServlet",
		// urlPatterns�� �̿��� ���� �̸��� ������ ������ �� ����
		urlPatterns = { 
				"/sInit", 
				"/sInit2"
		}, 
		// @WebInitParam�� �̿��� ���� ���� �Ű������� ������ �� ����
		initParams = { 
				@WebInitParam(name = "email", value = "admin@jweb.com"), 
				@WebInitParam(name = "tel", value = "010-1111-2222")
		})
public class InitParmaServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// ������ �Ű������� name���� ���� ������
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		out.print("<html><body>");
		out.print("<table><tr>");
		out.print("<td>email: </td><td>"+email+"</td></tr>");
		out.print("<tr><td>�޴���ȭ: </td><td>"+tel+"</td>");
		out.print("</tr></table></body></html>");
	}
}