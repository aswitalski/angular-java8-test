package com.sky.assignment.web.init;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.sky.assignment")
public class SkyWebAppConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("index.html").addResourceLocations("index.html");
		registry.addResourceHandler("images/**").addResourceLocations("images/**");
		registry.addResourceHandler("scripts/**").addResourceLocations("scripts/**");
		registry.addResourceHandler("styles/**").addResourceLocations("styles/**");
		registry.addResourceHandler("views/**").addResourceLocations("views/**");
	}
}
