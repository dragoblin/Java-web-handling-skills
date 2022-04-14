package sec03.brd05;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

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
				nextPage = "/board04/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);			
				nextPage = "/board04/listArticles.jsp";
				
			  // action �� /articleForm.do�� ��û �� �۾���â�� ��Ÿ��
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/board04/articleForm.jsp";
				
			  // /addArticle.do�� ��û �� �� �� �߰� �۾��� ����
			} else if (action.equals("/addArticle.do")) {
				
				int articleNO=0;
				
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
				
				// ���̺� �� ���� �߰��� �� �� �ۿ� ���� �� ��ȣ�� ������
				articleNO = boardService.addArticle(articleVO);
				
				// ������ ÷���� ��쿡�� ����
				if(imageFileName!=null && imageFileName.length()!=0) {
					
					// temp ������ �ӽ÷� ���ε� �� ���� ��ü�� ����
				    File srcFile = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
				    
				    // CURR_IMAGE_REPO_PATH�� ��� ������ �� ��ȣ�� ������ ����
					File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO);
					System.out.println("���Ϲ�ȣ : "+articleNO);
					destDir.mkdirs();
					
					// temp ������ ������ �� ��ȣ�� �̸����� �ϴ� ������ �̵���Ŵ
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
				}
				
				/* �� �� ��� �޽����� ��Ÿ�� �� �ڹٽ�ũ��Ʈ location ��ü�� href �Ӽ���
				   �̿��� �� ����� ��û */
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
				         +"alert('������ �߰��߽��ϴ�.');" 
				         +"location.href='"+request.getContextPath()+"/board/listArticles.do';"
				         +"</script>");
				return;
				
			} else if(action.equals("/viewArticle.do")) {
				
				// �� ��â�� ��û�� ��� articleNO ���� ������
				String articleNO = request.getParameter("articleNO");
				
				// articleNO �� ���� �� ������ ��ȸ�ϰ� article �Ӽ����� ���ε�
				articleVO=boardService.viewArticle(Integer.parseInt(articleNO));
				request.setAttribute("article",articleVO);
				
				nextPage = "/board04/viewArticle.jsp";
			
			} else if (action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				articleVO.setArticleNO(articleNO);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				// ���۵� �� ������ �̿��� ���� ����
				boardService.modArticle(articleVO);
				
				if (imageFileName != null && imageFileName.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					
					// ������ �̹��� ������ ������ �̵�
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
					// ���۵� originalImageFileName�� �̿��� ������ ������ ����
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete();
					
				}
				PrintWriter pw = response.getWriter();
				
				// �� ���� �� location ��ü�� href �Ӽ��� �̿��� �� �� ȭ���� ��Ÿ��
				pw.print("<script>" + "alert('���� �����߽��ϴ�.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				
				return;
			
			} else {
				nextPage = "/board04/listArticles.jsp";
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
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
						
						// ÷���� ������ ���� temp ������ ���ε�
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						
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