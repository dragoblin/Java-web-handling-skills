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
		
		// 전달된 pagingMap 을 사용해 글 목록을 조회
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		
		// 테이블에 존재하는 전체 글 수를 조회
		int totArticles = boardDAO.selectTotArticles();
		
		// 조회된 글 목록을 ArrayList에 저장한 후 다시 articlesMap에 저장
		articlesMap.put("articlesList", articlesList);
		
		// 전체 글 수를 articlesMap에 저장
		articlesMap.put("totArticles", totArticles);
//		articlesMap.put("totArticles", 170);
		
		return articlesMap;
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	// 새 글 번호를 컨트롤러로 반환
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
		
		// 글을 삭제하기 전 글 번호들을 ArrayList 객체에 저장
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		
		boardDAO.deleteArticle(articleNO);
		
		// 삭제한 글 번호 목록을 컨트롤러로 반환
		return articleNOList;
		
	}
	
	public int addReply(ArticleVO article) {
		
		// 새 글 추가 시 사용한 insertNewArticle() 메서드를 이용해 답글을 추가
		return boardDAO.insertNewArticle(article);
		
	}
	
}