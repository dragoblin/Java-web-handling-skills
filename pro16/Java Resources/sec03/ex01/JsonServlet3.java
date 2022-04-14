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

@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {
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
		
		
		// �迭�� ���������� ������ JSONObject ��ü�� ����
		JSONObject totalObject = new JSONObject();
		
		JSONArray membersArray = new JSONArray();
		
		JSONObject memberInfo = new JSONObject();
		memberInfo.put("name", "������");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "����");
		memberInfo.put("nickname", "��������");
		membersArray.add(memberInfo);

		memberInfo = new JSONObject();
		memberInfo.put("name", "�迬��");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "����");
		memberInfo.put("nickname", "Įġ");
		membersArray.add(memberInfo);
		
		// ȸ�� ������ ������ �迭�� �迭 �̸� members�� totalObject�� ����
		totalObject.put("members", membersArray);
		
		// JSONArray ��ü�� ����
		JSONArray bookArray = new JSONArray();
		
		JSONObject bookInfo = new JSONObject();
		
		// JSONObject ��ü�� ������ �� å ������ ����
		bookInfo.put("title", "�ʺ��ڸ� ���� �ڹ� ���α׷���");
		bookInfo.put("writer", "�̺���");
		bookInfo.put("price", "30000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/image1.jpg");
		
		// bookArray�� ��ü�� ����
		bookArray.add(bookInfo);
		
		// JSONObject ��ü�� ������ �� å ������ ����
		bookInfo = new JSONObject();
		bookInfo.put("title", "����� ���̽�");
		bookInfo.put("writer", "�̽���");
		bookInfo.put("price", "12000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/image2.jpg");
		
		// bookArray�� ��ü�� ����
		bookArray.add(bookInfo);

		// ���� ������ ������ �迭�� �迭 �̸� books�� totalObject�� ����
		totalObject.put("books", bookArray);

		String jsonInfo = totalObject.toJSONString();
		System.out.print(jsonInfo);
		writer.print(jsonInfo);
	}
}