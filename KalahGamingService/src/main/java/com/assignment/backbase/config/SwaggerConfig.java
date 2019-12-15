package com.assignment.backbase.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2)

				.select().apis(RequestHandlerSelectors.basePackage("com.assignment.backbase.controller")).build()
				.apiInfo(metaData());

	}

	@Bean
	public ApiInfo metaData() {
		return new ApiInfoBuilder().title("KalaGamingService").description("Spring Boot REST API for KalaGamingService")
				.build();

	}
}
