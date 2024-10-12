package com.werwiz.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                // accessToken이라는 스키마 만들어주기
                                .addSecuritySchemes("accessToken", new SecurityScheme()
                                        .name("accessToken")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .bearerFormat("JWT")
                                )
                                // refreshToken이라는 스키마 만들어주기
                                .addSecuritySchemes("userId", new SecurityScheme()
                                        .name("useID")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .bearerFormat("String")
                                )
                );
    }
}
