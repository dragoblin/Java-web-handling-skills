package sec03.brd02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	// �ۿ� ÷���� �̹��� ���� ��ġ�� ����� ����
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	
	BoardService boardService;
	ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

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
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board02/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);			
				nextPage = "/board02/listArticles.jsp";
				
			  // action �� /articleForm.do�� ��û �� �۾���â�� ��Ÿ��
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/board02/articleForm.jsp";
				
			  // /addArticle.do�� ��û �� �� �� �߰� �۾��� ����
			} else if (action.equals("/addArticle.do")) {
				
				// ���� ���ε� ����� ����ϱ� ���� upload()�� ��û�� ����
				Map<String, String> articleMap = upload(request, response);
				
				// articleMap�� ����� �� ������ �ٽ� ������
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				// �۾���â���� �Էµ� ������ ArticleVO ��ü�� ������ �� addArticle()�� ����
				articleVO.setParentNO(0);	// �� ���� �θ� �� ��ȣ�� 0���� ����
				articleVO.setId("hong");	// �� �� �ۼ��� ID�� hong���� ����
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.addArticle(articleVO);
				
				nextPage = "/board/listArticles.do";
			} else {
				nextPage = "/board02/listArticles.jsp";
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		
		// �� �̹��� ���� ������ ���� ���� ��ü�� ����
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					
					/* ���� ���ε�� ���� ���۵� �� �� ���� �Ű������� Map�� (key, value)�� ������ �� ��ȯ�ϰ�,
					   �� �۰� ���õ� title, content�� Map�� ���� */
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				} else {
					System.out.println("�Ķ�����̸�:" + fileItem.getFieldName());
					System.out.println("�����̸�:" + fileItem.getName());
					System.out.println("����ũ��:" + fileItem.getSize() + "bytes");
					
					// ���ε��� ������ �����ϴ� ��� ���ε��� ������ ���� �̸����� ����ҿ� ���ε�
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("���ϸ�:" + fileName);
						
						// ���ε�� ������ ���� �̸��� Map�� ("imageFileName", "���ε� ���� �̸�")�� ����
						articleMap.put(fileItem.getFieldName(), fileName);  
						// �ͽ��÷η����� ���ε� ������ ��� ���� �� map�� ���ϸ� ����
						
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
	
}