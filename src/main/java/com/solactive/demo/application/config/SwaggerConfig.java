package com.solactive.demo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// students
	@Bean
	public Docket financeApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("finance-api").useDefaultResponseMessages(false)
				.apiInfo(financeAPIInfo()).select().paths(financePaths()).build();

	}

	private Predicate<String> financePaths() {
		return regex("/api.*");
	}

	private ApiInfo financeAPIInfo() {
		return new ApiInfoBuilder().title("Finance Index API").description("Finance Index API to calculate aggregated statistics data for given Instrument name")
				.license("Apache License Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0").build();
	}

}
