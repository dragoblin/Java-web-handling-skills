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
		
		// boardDAO�� selectAllArticlesList() �޼��带 ȣ��
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList();
		
        return articlesList;
	}

	
	/*
	// ���� �̹��� �߰��ϱ�
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		return boardDAO.insertNewArticle(articleMap);
	}
	*/
	
	
	//���� �̹��� �߰��ϱ�
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		
		// �� ������ ������ �� �� ��ȣ�� ������
		int articleNO = boardDAO.insertNewArticle(articleMap);
		
		// �� ��ȣ�� articleMap�� ������ �� �̹��� ������ ����
		articleMap.put("articleNO", articleNO);
		boardDAO.insertNewImage(articleMap);
		
		return articleNO;
	}
	
	
	/*
	// ���� ���� ���̱�
	@Override
	public ArticleVO viewArticle(int articleNO) throws Exception {
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		return articleVO;
	}
	*/
	
	
	//���� ���� ���̱�
	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		
		// �� ������ ��ȸ
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		
		// �̹��� ���� ������ ��ȸ
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		
		// �� ������ �̹��� ���� ������ Map�� ����
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