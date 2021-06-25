package com.pruebaTUL.TUL.utils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import com.google.common.base.Predicates;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


/**
 *
 *  Clase para tratar la configuración de Swagger
 *
 */
@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    open fun api() =
            Docket(DocumentationType.SWAGGER_2).select()
                    .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                    .build();


    @Override
    fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}