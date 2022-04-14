package com.myboot03.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
// WebMvcConfigurerAdapter를 상속받음
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ViewNameInterceptor())
        		
        		// 1단계, 2단계 요청에 대해 모두 인터셉터를 적용
                .addPathPatterns("/*.do")
                .addPathPatterns("/*/*.do")
                
                /* /user/login 요청에 대해서는 인터셉터 요청을 적용하지 않음 */
                .excludePathPatterns("/users/login"); 
        
    }
}