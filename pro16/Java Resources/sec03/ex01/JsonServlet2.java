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
		
		// �迭�� ������ totalObject�� ����
		JSONObject totalObject = new JSONObject();
		// memberInfo JSON ��ü�� ������ membersArray �迭�� ����
		JSONArray membersArray = new JSONArray();
		// ȸ�� �� ���� ������ �� memberInfo JSON ��ü�� ����
		JSONObject memberInfo = new JSONObject();
		
		// ȸ�� ������ name/value ������ ����
		memberInfo.put("name", "������");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "����");
		memberInfo.put("nickname", "��������");
		
        // ȸ�� ������ �ٽ� membersArray �迭�� ����
		membersArray.add(memberInfo);
		
		// �ٸ� ȸ�� ������ name/value ������ ������ �� membersArray�� �ٽ� ����
		memberInfo = new JSONObject();
		memberInfo.put("name", "�迬��");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "����");
		memberInfo.put("nickname", "Įġ");
		membersArray.add(memberInfo);
		
		// totalObject�� members��� name���� membersArray�� value�� ����
		totalObject.put("members", membersArray);
		
		// JSONObject�� ���ڿ��� ��ȯ
		String jsonInfo = totalObject.toJSONString();
		
		System.out.print(jsonInfo);
		
		// JSON �����͸� �������� ����
		writer.print(jsonInfo);
	}
}