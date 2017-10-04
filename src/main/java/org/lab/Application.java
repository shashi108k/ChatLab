package org.lab;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableJpaRepositories("org.lab.dao")
//@EnableSolrRepositories(value = {"org.lab.dao.solr"}, multicoreSupport = true)
//@EnableJpaRepositories(value = {"org.lab.dao"})
public class Application {

	@Value("${endpoints.cors.allowed-origins}")
	private String origin;
	
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//registry.addMapping("/**").allowedOrigins(origin.split(","));
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS");
			}
		};
	}
	
	// In Spring Boot, you can use appContext.getBeanDefinitionNames() to get all the beans loaded by the Spring container.
	@Bean
    public CommandLineRunner run(ApplicationContext appContext) {
        return args -> {

            String[] beans = appContext.getBeanDefinitionNames();
            Arrays.stream(beans).sorted().forEach(System.out::println);

        };
    }

}
