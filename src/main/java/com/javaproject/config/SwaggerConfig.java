package com.javaproject.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(TypeResolver typeResolver) {
        String description = """
                <p> 토큰 등록 해야 API 호출 됩니다. </p>
                <p> Swagger는 Production 환경에서는 접근할 수 없습니다. </p>
                """;
        Contact contact = new Contact("자바프로젝트", "", "");

        ApiInfo apiInfo = new ApiInfo("자바 프로젝트",
                description,
                "API 1.0",
                "",
                contact,
                "",
                "", Arrays.asList());

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                ;
    }
}
