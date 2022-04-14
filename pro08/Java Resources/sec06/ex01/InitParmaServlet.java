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
		// urlPatterns를 이용해 매핑 이름을 여러개 설정할 수 있음
		urlPatterns = { 
				"/sInit", 
				"/sInit2"
		}, 
		// @WebInitParam을 이용해 여러 개의 매개변수를 설정할 수 있음
		initParams = { 
				@WebInitParam(name = "email", value = "admin@jweb.com"), 
				@WebInitParam(name = "tel", value = "010-1111-2222")
		})
public class InitParmaServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 설정한 매개변수의 name으로 값을 가져옴
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		out.print("<html><body>");
		out.print("<table><tr>");
		out.print("<td>email: </td><td>"+email+"</td></tr>");
		out.print("<tr><td>휴대전화: </td><td>"+tel+"</td>");
		out.print("</tr></table></body></html>");
	}
}