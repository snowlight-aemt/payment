package com.snowlightpay.money;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("Money");
        return new OpenAPI().components(new Components()).info(info);
    }
}

//@Configuration
//public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
//        Info info = new Info()
//                .title("타이틀 입력")
//                .version(springdocVersion)
//                .description("API에 대한 설명 부분");
//
//        return new OpenAPI()
//                .components(new Components())
//                .info(info);
//    }
//}