package com.eka.mobileserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.eka.mobileserver.constants.GlobalConstants;


@Configuration
@SpringBootApplication
public class EkaMobileServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkaMobileServerApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		List<String> exposedHeaderList = new ArrayList<String>();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		exposedHeaderList.add(GlobalConstants.AUTHORIZATION);
		exposedHeaderList.add(GlobalConstants.ACCEPT);
		exposedHeaderList.add(GlobalConstants.CACHE_CONTROL);
		config.setAllowCredentials(true);
		config.addAllowedOrigin(GlobalConstants.ORIGIN);
		config.addAllowedHeader(GlobalConstants.STAR);
		config.addAllowedMethod(GlobalConstants.PUT);
		config.addAllowedMethod(GlobalConstants.GET);
		config.addAllowedMethod(GlobalConstants.POST);
		config.addAllowedMethod(GlobalConstants.OPTIONS);
		config.addAllowedMethod(GlobalConstants.DELETE);
		config.setExposedHeaders(exposedHeaderList);
		source.registerCorsConfiguration(GlobalConstants.SLASH_STAR, config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
	
}