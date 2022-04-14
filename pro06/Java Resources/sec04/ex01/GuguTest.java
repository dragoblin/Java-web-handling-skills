package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guguTest")
public class GuguTest extends HttpServlet {
	public void init() {
		System.out.println("init 메서드 호출");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 전송된 dan의 값을 받아옴
		int dan = Integer.parseInt(request.getParameter("dan"));
		out.print("<table border=1 width=800 align=center>");
		out.print("<tr align=center bgcolor='#FFFF66'>");
		out.print("<td colspan=2>" + dan + " 단 출력 </td>");
		out.print("</tr>");
		// for문을 이용해 연속해서 결과를 테이블 행으로 출력
		for (int i=1; i<10; i++) {
			out.print("<tr align=center>");
			out.print("<td width=400>");
			out.print(dan + " * " + i);
			out.print("</td>");
			out.print("<td width=400>");
			out.print(i * dan);
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
	
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}
}
