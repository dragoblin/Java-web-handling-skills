package com.myspring.pro28.ex05;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// ����� ���� ���ͼ��ʹ� �ݵ�� HandlerInterceptorAdapter�� ��ӹ޾ƾ� ��
public class LocaleInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	// ��Ʈ�ѷ� ���� �� ȣ��
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) {
		
		HttpSession session = request.getSession();
		
		// ���������� ������ local ������ ������
	    String locale = request.getParameter("locale");
	    
	    // ���� ��û �� locale�� �ѱ���� ����
	    if(locale==null) locale="ko";
	    
	    // LOCALE �Ӽ� ���� ���ǿ� ������ SessionLocaleResolver�� ����� �� �ְ� ��
	    session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(locale));
	    
	    return true;
	}

   	@Override
   	// ��Ʈ�ѷ� ���� �� ȣ��
   	public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) 
    throws Exception {
   		
   	}
   	
   	@Override
   	// ��(JSP)�� ������ �� ȣ��
   	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex)    
    throws  Exception {
   		
   	}
   	
}