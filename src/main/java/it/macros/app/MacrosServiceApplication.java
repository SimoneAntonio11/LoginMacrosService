package it.macros.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication(scanBasePackages = "it.macros")
public class MacrosServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(MacrosServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(MacrosServiceApplication.class);
	}

	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:application-resource");
	    messageSource.setCacheSeconds(10);

	    return messageSource;
	}
}