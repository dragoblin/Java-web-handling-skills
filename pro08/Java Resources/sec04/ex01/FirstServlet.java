package sec04.ex01;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		// 웹 브라우저에서 요청한 request 객체에 address의 값으로 "서울시 성북구"를 바인딩
		request.setAttribute("address", "서울시 성북구");
		// 두 번째 서블릿으로 전달하기 위해 sendRedirect()를 호출
		response.sendRedirect("second");
	}
}
