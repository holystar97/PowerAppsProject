package com.net.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("ImageUploadService Api List")
                .description("My API")
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("imageController")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.net.main"))
                //RequestMapping의 value를 입력 시 해당 Request 정의문을 import함
                .paths(PathSelectors.ant("/upload/**"))
                .build();
    }
    @Bean
    public Docket commonApi2() {
    	return new Docket(DocumentationType.SWAGGER_2)
    			.groupName("textController")
    			.apiInfo(this.apiInfo())
    			.select()
    			.apis(RequestHandlerSelectors
    					.basePackage("com.net.main"))
    			.paths(PathSelectors.ant("/textupload/**"))
    			.build();
    }
}