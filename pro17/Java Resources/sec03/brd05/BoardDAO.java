package sec03.brd05;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			
			// 오라클 계층형 SQL문을 실행
			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" 
			             + " from t_board"
					     + " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
					     + " ORDER SIBLINGS BY articleNO DESC";
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				// 각 글의 깊이(계층)를 level 속성에 저장
				int level = rs.getInt("level");
				
				// 글 번호는 숫자형이므로 getInt()로 값을 가져옴
				int articleNO = rs.getInt("articleNO");
				
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				// 글 정보를 ArticleVO 객체의 속성에 설정
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	private int getNewArticleNO() {
		try {
			conn = dataFactory.getConnection();
			
			// 기본 글 번호 중 가장 큰 번호를 조회
			String query = "SELECT max(articleNO) from t_board ";
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			
			// 가장 큰 번호에 1을 더한 번호를 반환
			if (rs.next())
				return (rs.getInt(1) + 1);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		try {
			conn = dataFactory.getConnection();	
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			
			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES (?, ? ,?, ?, ?, ?)";	// insert문을 이용해 글 정보를 추가
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// SQL문으로 새 글을 추가하고 새 글 번호를 반환
		return articleNO;
		
	}
	
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			
			// 전달받은 글 번호를 이용해 글 정보를 조회
			String query = "select articleNO, parentNO, title, content, NVL(imageFileName, 'null') as imageFileName, id, writeDate"
				                     +" from t_board" 
				                     +" where articleNO=?";
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs =pstmt.executeQuery();
			rs.next();
			int _articleNO =rs.getInt("articleNO");
			int parentNO=rs.getInt("parentNO");
			String title = rs.getString("title");
			String content =rs.getString("content");
			
			//파일이름에 특수문자가 있을 경우 인코딩합니다.
			String imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "UTF-8"); 
			
			if(imageFileName.equals("null")) {
				imageFileName = null;
			}
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
	
			article.setArticleNO(_articleNO);
			article.setParentNO (parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();	
		}
		return article;
	}
	
	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_board set title=?,content=?";
			
			// 수정된 이미지 파일이 있을 때만 imageFileName을 SQL문에 추가
			if (imageFileName != null && imageFileName.length() != 0) {
				query += ",imageFileName=?";
			}
			
			query += " where articleNO=?";
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			
			// 이미지 파일을 수정하는 경우와 그렇지 않은 경우를 구분해서 설정
			if (imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}