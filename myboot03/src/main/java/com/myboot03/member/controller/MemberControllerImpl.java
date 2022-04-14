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

/* @Controller를 이용해 MemberControllerImpl 클래스에 대해 
 * id가 memberController인 빈을 자동 생성 */
@Controller("memberController")
@EnableAspectJAutoProxy
public class MemberControllerImpl implements MemberController {
	
	// @Autowired를 이용해 id가 memberService인 빈을 자동 주입
	@Autowired
	private MemberService memberService;
	
	// @Autowired를 이용해 id가 memberService인 빈을 자동 주입
	@Autowired
	private MemberVO memberVO;
	
	/* /pro30/main.do로 요청 시 메인 페이지를 보여 줌 */
	@RequestMapping(value={ "/","/main.do"}, method=RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	// 두 단계로 요청 시 바로 해당 메서드를 호출하도록 매핑
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		// 인터셉터에서 바인딩된(전달된) 뷰이름을 가져옴
		String viewName = (String) request.getAttribute("viewName");
		
		List membersList = memberService.listMembers();
		
		// viewName이 <definition> 태그에 설정한 뷰이름과 일치
		ModelAndView mav = new ModelAndView(viewName);
		
		// 뷰이름을 직접 지정
//		ModelAndView mav = new ModelAndView("/member/listMembers");
		
		mav.addObject("membersList", membersList);
		
		// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환
		return mav;
		
	}

	
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	
	// 회원 가입창에서 전송된 회원 정보를 바로 MemberVO 객체에 설정
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member,
			                  	  HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		
		// 설정된 memberVO 객체를 SQL문으로 전달해 회원 등록
		result = memberService.addMember(member);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	
	@Override
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.GET)
	
	// 전송된 ID를 변수 id에 설정
	public ModelAndView removeMember(@RequestParam("id") String id, 
			           HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	
	// 정규식을 이용해 요청명이 Form.do로 끝나면 form() 메서드를 호출
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
	
	/* 로그인창에서 전송된 ID와 비밀번호를 MemberVO 객체인 member에 저장 */
	/* RedirectAttributes 클래스를 이용해 로그인 실패 시 다시 로그인창으로 리다이렉트하여 
	 * 실패 메시지를 전달 */
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
				              RedirectAttributes rAttr,
		                      HttpServletRequest request, 
		                      HttpServletResponse response) 
    throws Exception {
		ModelAndView mav = new ModelAndView();
		
		// login() 메서드를 호출하면서 로그인 정보를 전달
		memberVO = memberService.login(member);
		
		// memberVO로 반환된 값이 있으면 세션을 이용해 로그인 상태를 true로 함
		if(memberVO != null) {
		    HttpSession session = request.getSession();
		    
		    // 세션에 회원 정보를 저장
		    session.setAttribute("member", memberVO);
		    
		    // 세션에 로그인 상태를 true로 설정
		    session.setAttribute("isLogOn", true);
		    
//		    mav.setViewName("redirect:/member/listMembers.do");
		    
		    // 로그인 성공 시 세션에 저장된 action 값을 가져옴
		    String action = (String)session.getAttribute("action");
		    
		    session.removeAttribute("action");
		    
		    // action 값이 null이 아니면 action 값을 뷰이름으로 지정해 글쓰기창으로 이동
		    if(action!=null) {
		       mav.setViewName("redirect:"+action);
		    } else {
		       mav.setViewName("redirect:/member/listMembers.do");	
		    }
			   
		} else {	
			
			// 로그인 실패 시 다시 로그인창으로 리다이렉트
			rAttr.addAttribute("result", "loginFailed");
			
			// 로그인 실패 시 다시 로그인창으로 리다이렉트
			mav.setViewName("redirect:/member/loginForm.do");
			
		}
		return mav;
	}

	
	@Override
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		HttpSession session = request.getSession();
		
		// 로그아웃 요청 시 세션에 저장된 로그인 정보와 회원 정보를 삭제
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}	

	
	@RequestMapping(value="/member/*Form.do", method=RequestMethod.GET)
	/* @RequestParam(value="result", required=false) :  
	 * 		로그인창 요청 시 매개변수 result가 전송되면 변수 result에 값을 저장
	 * 		최초로 로그인창을 요청할 때는 매개변수 result가 전송되지 않으므로 무시 */
	/* @RequestParam(value= "action", required=false) :
	 * 		로그인 후 수행할 글쓰기 요청명을 action에 저장
	 * 		로그인 성공 후 바로 글쓰기 창으로 이동 */
	private ModelAndView form(@RequestParam(value="result", required=false) String result,
							  @RequestParam(value= "action", required=false) String action,
						       HttpServletRequest request, 
						       HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);

		// 인터셉터에서 바인딩된 뷰이름을 가져옴
//		String viewName = (String) request.getAttribute("viewName");
		
		HttpSession session = request.getSession();
		
		// 글쓰기창 요청명을 action 속성으로 세션에 저장
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
			
			/* /member/listMembers.do로 요청할 경우 member/listMember를 파일 이름으로 가져옴 */
			viewName = viewName.substring(viewName.lastIndexOf("/",1), viewName.length());
			
		}
		return viewName;
	}	

}