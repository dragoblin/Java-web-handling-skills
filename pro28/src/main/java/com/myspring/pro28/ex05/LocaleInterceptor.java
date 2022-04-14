package com.myspring.pro28.ex05;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// 사용자 정의 인터셉터는 반드시 HandlerInterceptorAdapter를 상속받아야 함
public class LocaleInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	// 컨트롤러 실행 전 호출
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) {
		
		HttpSession session = request.getSession();
		
		// 브라우저에서 전달한 local 정보를 가져옴
	    String locale = request.getParameter("locale");
	    
	    // 최초 요청 시 locale을 한국어로 설정
	    if(locale==null) locale="ko";
	    
	    // LOCALE 속성 값을 세션에 저장해 SessionLocaleResolver가 사용할 수 있게 함
	    session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(locale));
	    
	    return true;
	}

   	@Override
   	// 컨트롤러 실행 후 호출
   	public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) 
    throws Exception {
   		
   	}
   	
   	@Override
   	// 뷰(JSP)를 수행한 후 호출
   	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex)    
    throws  Exception {
   		
   	}
   	
}