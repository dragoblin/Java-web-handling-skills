package com.myboot03.common.tiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration	// 설정 클래스로 지정
public class TilesConfig {
	
	@Bean	// id가 tilesConfigurer인 빈을 생성
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		
		// 한 개의 타일즈 설정 파일을 읽어 들임
		configurer.setDefinitions(new String[] {"WEB-INF/tiles/tiles_member.xml"});
		
		// 여러 개의 타일즈 설정 파일을 읽어 들임
		//configurer.setDefinitions(new String[] {"WEB-INF/tiles/tiles_member.xml","WEB-INF/tiles/tiles_board.xml"});
		
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean	// id가 tilesViewResolver인 빈을 생성
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
}