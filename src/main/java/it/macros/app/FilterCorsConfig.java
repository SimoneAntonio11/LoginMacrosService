package it.macros.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterCorsConfig implements WebMvcConfigurer {

	@Bean
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry
					.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
					.allowedHeaders("*")
					.exposedHeaders("Authorization", "lang")
					.allowCredentials(true)
					.maxAge(3600);
			}
		};
	}
}