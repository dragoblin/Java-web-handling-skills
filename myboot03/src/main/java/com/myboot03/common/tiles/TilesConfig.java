package com.myboot03.common.tiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration	// ���� Ŭ������ ����
public class TilesConfig {
	
	@Bean	// id�� tilesConfigurer�� ���� ����
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		
		// �� ���� Ÿ���� ���� ������ �о� ����
		configurer.setDefinitions(new String[] {"WEB-INF/tiles/tiles_member.xml"});
		
		// ���� ���� Ÿ���� ���� ������ �о� ����
		//configurer.setDefinitions(new String[] {"WEB-INF/tiles/tiles_member.xml","WEB-INF/tiles/tiles_board.xml"});
		
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean	// id�� tilesViewResolver�� ���� ����
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
}