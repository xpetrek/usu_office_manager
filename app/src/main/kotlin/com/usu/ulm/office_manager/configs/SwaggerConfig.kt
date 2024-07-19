package com.usu.ulm.office_manager.configs

import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("springshop-public")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun apiInfo(): Info {
        return Info()
            .title("My API")
            .description("API for managing employees, desks, and offices.")
            .version("1.0.0")
    }
}
