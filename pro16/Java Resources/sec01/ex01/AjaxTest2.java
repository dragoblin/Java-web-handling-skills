package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajaxTest2")
public class AjaxTest2 extends HttpServlet {
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
		String result = "";
		PrintWriter writer = response.getWriter();
		
		// ���� ������ XML�� �ۼ��� �� Ŭ���̾�Ʈ�� ����
		result="<main><book>"+
		       "<title><![CDATA[�ʺ��ڸ� ���� �ڹ� ���α׷���]]></title>" +
		       "<writer><![CDATA[�����Ͻ� �� | �̺���]]></writer>" +
		       // pro16���� ���� �� WebContent ���� image ������ image1.jpg�� ǥ��
		       "<image><![CDATA[http://localhost:8090/pro16/image/image1.jpg]]></image>"+
		       "</book>"+
		       "<book>"+
		       "<title><![CDATA[����� ���̽�]]></title>" +
		       "<writer><![CDATA[��� �� | �̽���]]></writer>" +                 
		       "<image><![CDATA[http://localhost:8090/pro16/image/image2.jpg]]></image>"+
		       "</book></main>";
		
		System.out.println(result);
		writer.print(result);
	}
}