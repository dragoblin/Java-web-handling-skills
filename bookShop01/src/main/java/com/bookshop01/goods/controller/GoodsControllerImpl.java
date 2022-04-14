package com.bookshop01.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

import net.sf.json.JSONObject;

@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value="/goodsDetail.do", method=RequestMethod.GET)
	// @RequestParam("goods_id") : ��ȸ�� ��ǰ ��ȣ�� ���޹���
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id,
			                         HttpServletRequest request, 
			                         HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		
		// ��ǰ ������ ��ȸ�� �� Map���� ��ȯ
		Map goodsMap = goodsService.goodsDetail(goods_id);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsMap", goodsMap);
		
		// ��ȸ�� ��ǰ ������ ���� �޴��� ǥ���ϱ� ���� ����
		GoodsVO goodsVO = (GoodsVO)goodsMap.get("goodsVO");
		addGoodsInQuick(goods_id, goodsVO, session);
		
		return mav;
	}
	
	// produces="application/text; charset=utf8" : �������� �����ϴ� JSON �������� �ѱ� ���ڵ��� ����
	@RequestMapping(value="/keywordSearch.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	// @ResponseBody : JSON �����͸� �������� ���
	// @RequestParam("keyword") : �˻��� Ű���带 ������
	public @ResponseBody String keywordSearch(@RequestParam("keyword") String keyword,
			                                   HttpServletRequest request, 
			                                   HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//System.out.println(keyword);
		if(keyword==null || keyword.equals(""))
		   return null ;
	
		keyword = keyword.toUpperCase();
		
		// ������ Ű���尡 ���Ե� ��ǰ ������ ��ȸ
	    List<String> keywordList =goodsService.keywordSearch(keyword);
	    
	 
		JSONObject jsonObject = new JSONObject();	// ���� �ϼ��� JSONObject ����(��ü)
		jsonObject.put("keyword", keywordList);		// ��ȸ�� �����͸� JSON�� ����
		 
		// JSON�� ���ڿ��� ��ȯ�� �� �������� ���
	    String jsonInfo = jsonObject.toString();
	    //System.out.println(jsonInfo);
	    return jsonInfo ;
	    
	}
	
	@RequestMapping(value="/searchGoods.do", method=RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,
			                       	 HttpServletRequest request, 
			                       	 HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		
		// �˻�â���� ������ �ܾ ���Ե� ��ǰ ������ ��ȸ
		List<GoodsVO> goodsList = goodsService.searchGoods(searchWord);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsList", goodsList);
		return mav;
	}
	
	private void addGoodsInQuick(String goods_id, GoodsVO goodsVO, 
								 HttpSession session) {
		boolean already_existed = false;
		List<GoodsVO> quickGoodsList; 	//�ֱ� �� ��ǰ ���� ArrayList
		
		// ���ǿ� ����� �ֱ� �� ��ǰ ����� ������
		quickGoodsList = (ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");
		
		// �ֱ� �� ��ǰ�� �ִ� ���
		if(quickGoodsList!=null) {
			if(quickGoodsList.size() < 4) { // �̸��� ��ǰ ����Ʈ�� ��ǰ������ �� �� ������ ���
				
				/* ��ǰ ����� ������ �̹� �����ϴ� ��ǰ���� ��
				 * �̹� ������ ��� already_existed�� true�� ���� */
				for(int i=0; i<quickGoodsList.size(); i++) {
					GoodsVO _goodsBean = (GoodsVO)quickGoodsList.get(i);
					if(goods_id.equals(_goodsBean.getGoods_id())) {
						already_existed = true;
						break;
					}
				}
				
				// already_existed�� false�̸� ��ǰ ������ ��Ͽ� ����
				if(already_existed==false) {
					quickGoodsList.add(goodsVO);
				}
				
			}
			
		} else {
			
			// �ֱ� �� ��ǰ ����� ������ �����Ͽ� ��ǰ ������ ����
			quickGoodsList = new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
			
		}
		
		// �ֱ� �� ��ǰ ����� ���ǿ� ����
		session.setAttribute("quickGoodsList",quickGoodsList);
		
		// �ֱ� �� ��ǰ ��Ͽ� ����� ��ǰ ������ ���ǿ� ����
		session.setAttribute("quickGoodsListNum", quickGoodsList.size());
		
	}
}