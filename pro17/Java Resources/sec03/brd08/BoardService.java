package sec03.brd08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public Map listArticles(Map pagingMap) {
		Map articlesMap = new HashMap();
		
		// ���޵� pagingMap �� ����� �� ����� ��ȸ
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		
		// ���̺� �����ϴ� ��ü �� ���� ��ȸ
		int totArticles = boardDAO.selectTotArticles();
		
		// ��ȸ�� �� ����� ArrayList�� ������ �� �ٽ� articlesMap�� ����
		articlesMap.put("articlesList", articlesList);
		
		// ��ü �� ���� articlesMap�� ����
		articlesMap.put("totArticles", totArticles);
//		articlesMap.put("totArticles", 170);
		
		return articlesMap;
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	// �� �� ��ȣ�� ��Ʈ�ѷ��� ��ȯ
	public int addArticle(ArticleVO article){
		return boardDAO.insertNewArticle(article);		
	}
	
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO);
		return article;
	}
	
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	
	public List<Integer> removeArticle(int articleNO) {
		
		// ���� �����ϱ� �� �� ��ȣ���� ArrayList ��ü�� ����
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		
		boardDAO.deleteArticle(articleNO);
		
		// ������ �� ��ȣ ����� ��Ʈ�ѷ��� ��ȯ
		return articleNOList;
		
	}
	
	public int addReply(ArticleVO article) {
		
		// �� �� �߰� �� ����� insertNewArticle() �޼��带 �̿��� ����� �߰�
		return boardDAO.insertNewArticle(article);
		
	}
	
}