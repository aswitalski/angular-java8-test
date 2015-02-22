package com.sky.assignment.web.init;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.sky.assignment")
public class SkyWebAppConfig {
	
	// @Bean
	// public FeedService feedService()
	// return new DefaultFeedService();
	// }
}
