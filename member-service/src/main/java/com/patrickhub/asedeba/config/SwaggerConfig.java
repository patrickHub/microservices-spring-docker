package com.patrickhub.asedeba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{
	
	 @Bean
	    public Docket MemberApi() {
	        return new Docket(DocumentationType.SWAGGER_2) .groupName("Member").select()
	        		.apis(RequestHandlerSelectors.basePackage("com.patrickhub.asedeba.web.rest"))
	                .paths(regex("/api/members.*"))
	                .build().apiInfo(new ApiInfoBuilder()
	    	                .title("member-service.Member REST API")
	    	                .description("\"Member REST API for our member-service\"")
	    	                .version("1.0.0")
	    	                .license("Apache License Version 2.0")
	    	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	    	                .contact(new Contact("PatrickHub", "", "patrickd.djomo@gmail.com"))
	    	                .build());
	             
	    }
	 
	 @Bean
	    public Docket AdressApi() {
	        return new Docket(DocumentationType.SWAGGER_2).groupName("Adress").select()
	        		.apis(RequestHandlerSelectors.basePackage("com.patrickhub.asedeba.web.rest"))
	                .paths(regex("/api/adress.*"))
	                .build().apiInfo(new ApiInfoBuilder()
	                		.title("member-service.Adress REST API")
	                		.description("\"Adress REST API for our member-service\"")
	                		.version("1.0.0")
	                		.license("Apache Licence Version 2.0")
	                		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	                		.contact(new Contact("PatrickHub", "", "patrickd.djomo@gmail.com"))
	                		.build());
	             
	    }
	 
	  @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
}
