package com.myspring.pro30.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.board.dao.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	public List<ArticleVO> listArticles() throws Exception {
		
		// boardDAO의 selectAllArticlesList() 메서드를 호출
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList();
		
        return articlesList;
	}

	
	/*
	// 단일 이미지 추가하기
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		return boardDAO.insertNewArticle(articleMap);
	}
	*/
	
	
	//다중 이미지 추가하기
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		
		// 글 정보를 저장한 후 글 번호를 가져옴
		int articleNO = boardDAO.insertNewArticle(articleMap);
		
		// 글 번호를 articleMap에 저장한 후 이미지 정보를 저장
		articleMap.put("articleNO", articleNO);
		boardDAO.insertNewImage(articleMap);
		
		return articleNO;
	}
	
	
	/*
	// 단일 파일 보이기
	@Override
	public ArticleVO viewArticle(int articleNO) throws Exception {
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		return articleVO;
	}
	*/
	
	
	//다중 파일 보이기
	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		
		// 글 정보를 조회
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		
		// 이미지 파일 정보를 조회
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		
		// 글 정보와 이미지 파일 정보를 Map에 담음
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		
		return articleMap;
	}
   
	
	
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}
	
	
	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}
		
}