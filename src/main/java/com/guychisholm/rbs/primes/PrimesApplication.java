package com.guychisholm.rbs.primes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class PrimesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimesApplication.class, args);
	}

	// Get the rest docs index.html to serve at the root
	@Bean
	public WebMvcConfigurer forwardToIndex() {
		return new WebMvcConfigurer() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				// forward / requests to their index.html
				registry.addViewController("/").setViewName("forward:/index.html");
			}
		};
	}

}
