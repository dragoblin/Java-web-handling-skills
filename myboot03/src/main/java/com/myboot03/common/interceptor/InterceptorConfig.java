package com.myboot03.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
// WebMvcConfigurerAdapter�� ��ӹ���
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ViewNameInterceptor())
        		
        		// 1�ܰ�, 2�ܰ� ��û�� ���� ��� ���ͼ��͸� ����
                .addPathPatterns("/*.do")
                .addPathPatterns("/*/*.do")
                
                /* /user/login ��û�� ���ؼ��� ���ͼ��� ��û�� �������� ���� */
                .excludePathPatterns("/users/login"); 
        
    }
}