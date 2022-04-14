package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/*")
public class TestController {
	static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/* /hello�� ��û �� �������� ���ڿ��� ����*/
	@RequestMapping("/hello")
	public String hello() {
		return "Hello REST!!";
	}
	
	
	@RequestMapping("/member")
	// MemberVO ��ü�� �Ӽ� ���� ������ �� JSON���� ����
	public MemberVO member() {
		MemberVO vo = new MemberVO();
	    vo.setId("hong");
	    vo.setPwd("1234");
	    vo.setName("ȫ�浿");
	    vo.setEmail("hong@test.com");
	    return vo;
	} 
	
	
	@RequestMapping("/membersList")
	public List<MemberVO> listMembers () {
		
		// MemberVO ��ü�� ������ ArrayList ��ü�� ����
	    List<MemberVO> list = new ArrayList<MemberVO>();
	    
	    // MemberVO ��ü�� 10�� ������ ArrayList�� ����
		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("123"+i);
			vo.setName("ȫ�浿"+i);
			vo.setEmail("hong"+i+"@test.com");
			list.add(vo);
		}
		
		// ArrayList�� JSON���� �������� ����
	    return list; 
	    
	}
	
	
	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap() {
		
		// MemberVO ��ü�� ������ HashMap ��ü�� ����
	    Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
	    
	    // MemberVO ��ü�� HashMap�� ����
	    for (int i = 0; i < 10; i++) {
	    	MemberVO vo = new MemberVO();
	    	vo.setId("hong" + i);
	    	vo.setPwd("123"+i);
	    	vo.setName("ȫ�浿" + i);
	    	vo.setEmail("hong"+i+"@test.com");
	    	map.put(i, vo); 
	    }
	    
	    // HashMap ��ü�� �������� ����
	    return map;
	    
	} 
	
	
	// ���������� ��û �� {num} �κ��� ���� @PathVariable�� ����
	@RequestMapping(value="/notice/{num}", method=RequestMethod.GET)	
	// ��û URL���� ������ ���� num�� �ڵ����� �Ҵ�
	public int notice(@PathVariable("num") int num) throws Exception {
		return num;
	}
	
	
	@RequestMapping(value="/info", method=RequestMethod.POST)
	// JSON���� ���۵� �����͸� MemberVO ��ü�� �Ӽ��� �ڵ����� ����
	public void modify(@RequestBody MemberVO vo) {
	    logger.info(vo.toString());
	}
	
	
	@RequestMapping("/membersList2")
	// ResponseEntity�� ����
	public ResponseEntity<List<MemberVO>> listMembers2() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("lee" + i);
			vo.setPwd("123" + i);
			vo.setName("�̼���" + i);
			vo.setEmail("lee" + i + "@test.com");
			list.add(vo);
		}
		
		// ���� �ڵ� 500���� ����
	    return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	}
	
	
	@RequestMapping(value = "/res3")
	public ResponseEntity res3() {
		HttpHeaders responseHeaders = new HttpHeaders();
		
		// ������ �������� ������ ���ڵ��� ����
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    
	    // ������ �ڹٽ�ũ��Ʈ �ڵ带 ���ڿ��� �ۼ�
	    String message = "<script>";
		message += " alert('�� ȸ���� ����մϴ�.');";
		message += " location.href='/pro29/test/membersList2'; ";
		message += " </script>";
		
		// ResponseEntity�� �̿��� HTML �������� ����
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		
	}

}