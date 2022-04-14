package sec03.brd03;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	// 새 글 번호를 컨트롤러로 반환
	public int addArticle(ArticleVO article){
		return boardDAO.insertNewArticle(article);		
	}
	
}