package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/json2")
public class JsonServlet2 extends HttpServlet {
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
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		// 배열을 저장할 totalObject를 선언
		JSONObject totalObject = new JSONObject();
		// memberInfo JSON 객체를 저장할 membersArray 배열을 선언
		JSONArray membersArray = new JSONArray();
		// 회원 한 명의 정보가 들어갈 memberInfo JSON 객체를 선언
		JSONObject memberInfo = new JSONObject();
		
		// 회원 정보를 name/value 쌍으로 저장
		memberInfo.put("name", "박지성");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날센돌이");
		
        // 회원 정보를 다시 membersArray 배열에 저장
		membersArray.add(memberInfo);
		
		// 다른 회원 정보를 name/value 쌍으로 저장한 후 membersArray에 다시 저장
		memberInfo = new JSONObject();
		memberInfo.put("name", "김연아");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "여자");
		memberInfo.put("nickname", "칼치");
		membersArray.add(memberInfo);
		
		// totalObject에 members라는 name으로 membersArray를 value로 저장
		totalObject.put("members", membersArray);
		
		// JSONObject를 문자열로 변환
		String jsonInfo = totalObject.toJSONString();
		
		System.out.print(jsonInfo);
		
		// JSON 데이터를 브라우저로 전송
		writer.print(jsonInfo);
	}
}