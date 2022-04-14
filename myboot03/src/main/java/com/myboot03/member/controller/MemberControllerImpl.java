package com.myboot03.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myboot03.member.service.MemberService;
import com.myboot03.member.vo.MemberVO;

/* @Controller�� �̿��� MemberControllerImpl Ŭ������ ���� 
 * id�� memberController�� ���� �ڵ� ���� */
@Controller("memberController")
@EnableAspectJAutoProxy
public class MemberControllerImpl implements MemberController {
	
	// @Autowired�� �̿��� id�� memberService�� ���� �ڵ� ����
	@Autowired
	private MemberService memberService;
	
	// @Autowired�� �̿��� id�� memberService�� ���� �ڵ� ����
	@Autowired
	private MemberVO memberVO;
	
	/* /pro30/main.do�� ��û �� ���� �������� ���� �� */
	@RequestMapping(value={ "/","/main.do"}, method=RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	// �� �ܰ�� ��û �� �ٷ� �ش� �޼��带 ȣ���ϵ��� ����
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		// ���ͼ��Ϳ��� ���ε���(���޵�) ���̸��� ������
		String viewName = (String) request.getAttribute("viewName");
		
		List membersList = memberService.listMembers();
		
		// viewName�� <definition> �±׿� ������ ���̸��� ��ġ
		ModelAndView mav = new ModelAndView(viewName);
		
		// ���̸��� ���� ����
//		ModelAndView mav = new ModelAndView("/member/listMembers");
		
		mav.addObject("membersList", membersList);
		
		// ModelAndView ��ü�� ������ ���̸��� Ÿ���� �丮������ ��ȯ
		return mav;
		
	}

	
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	
	// ȸ�� ����â���� ���۵� ȸ�� ������ �ٷ� MemberVO ��ü�� ����
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member,
			                  	  HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		
		// ������ memberVO ��ü�� SQL������ ������ ȸ�� ���
		result = memberService.addMember(member);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	
	@Override
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.GET)
	
	// ���۵� ID�� ���� id�� ����
	public ModelAndView removeMember(@RequestParam("id") String id, 
			           HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	
	// ���Խ��� �̿��� ��û���� Form.do�� ������ form() �޼��带 ȣ��
//	@RequestMapping(value={"/member/loginForm.do", "/member/memberForm.do"}, method= RequestMethod.GET)
//	@RequestMapping(value = "/member/*Form.do", method =  RequestMethod.GET)
	/*
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	*/
	
	
	@Override
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	
	/* �α���â���� ���۵� ID�� ��й�ȣ�� MemberVO ��ü�� member�� ���� */
	/* RedirectAttributes Ŭ������ �̿��� �α��� ���� �� �ٽ� �α���â���� �����̷�Ʈ�Ͽ� 
	 * ���� �޽����� ���� */
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
				              RedirectAttributes rAttr,
		                      HttpServletRequest request, 
		                      HttpServletResponse response) 
    throws Exception {
		ModelAndView mav = new ModelAndView();
		
		// login() �޼��带 ȣ���ϸ鼭 �α��� ������ ����
		memberVO = memberService.login(member);
		
		// memberVO�� ��ȯ�� ���� ������ ������ �̿��� �α��� ���¸� true�� ��
		if(memberVO != null) {
		    HttpSession session = request.getSession();
		    
		    // ���ǿ� ȸ�� ������ ����
		    session.setAttribute("member", memberVO);
		    
		    // ���ǿ� �α��� ���¸� true�� ����
		    session.setAttribute("isLogOn", true);
		    
//		    mav.setViewName("redirect:/member/listMembers.do");
		    
		    // �α��� ���� �� ���ǿ� ����� action ���� ������
		    String action = (String)session.getAttribute("action");
		    
		    session.removeAttribute("action");
		    
		    // action ���� null�� �ƴϸ� action ���� ���̸����� ������ �۾���â���� �̵�
		    if(action!=null) {
		       mav.setViewName("redirect:"+action);
		    } else {
		       mav.setViewName("redirect:/member/listMembers.do");	
		    }
			   
		} else {	
			
			// �α��� ���� �� �ٽ� �α���â���� �����̷�Ʈ
			rAttr.addAttribute("result", "loginFailed");
			
			// �α��� ���� �� �ٽ� �α���â���� �����̷�Ʈ
			mav.setViewName("redirect:/member/loginForm.do");
			
		}
		return mav;
	}

	
	@Override
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		HttpSession session = request.getSession();
		
		// �α׾ƿ� ��û �� ���ǿ� ����� �α��� ������ ȸ�� ������ ����
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}	

	
	@RequestMapping(value="/member/*Form.do", method=RequestMethod.GET)
	/* @RequestParam(value="result", required=false) :  
	 * 		�α���â ��û �� �Ű����� result�� ���۵Ǹ� ���� result�� ���� ����
	 * 		���ʷ� �α���â�� ��û�� ���� �Ű����� result�� ���۵��� �����Ƿ� ���� */
	/* @RequestParam(value= "action", required=false) :
	 * 		�α��� �� ������ �۾��� ��û���� action�� ����
	 * 		�α��� ���� �� �ٷ� �۾��� â���� �̵� */
	private ModelAndView form(@RequestParam(value="result", required=false) String result,
							  @RequestParam(value= "action", required=false) String action,
						       HttpServletRequest request, 
						       HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);

		// ���ͼ��Ϳ��� ���ε��� ���̸��� ������
//		String viewName = (String) request.getAttribute("viewName");
		
		HttpSession session = request.getSession();
		
		// �۾���â ��û���� action �Ӽ����� ���ǿ� ����
		session.setAttribute("action", action);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}

	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			
			/* /member/listMembers.do�� ��û�� ��� member/listMember�� ���� �̸����� ������ */
			viewName = viewName.substring(viewName.lastIndexOf("/",1), viewName.length());
			
		}
		return viewName;
	}	

}