package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")	//서블릿의 매핑 이름이 login
public class LoginServlet extends HttpServlet{
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}
	
	/* HttpServletRequest request : 웹 브라우저에서 전송한 정보를 톰캣 컨테이너가
	                  HttpServletRequest 객체를 생성한 후 doGet()으로 넘겨줌 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	// 전송된 데이터를 UTF-8로 인코딩
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		// getParameter()를 이용해 <input> 태그의 name 속성 값으로 전송된 value를 받아옴
		System.out.println("아이디:" + user_id);
		System.out.println("비밀번호:" + user_pw);
	}
	
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}
}