package com.karthik.virtualnode.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI virtualNodeApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Virtual Node API")
                        .version("1.0.0")
                        .description("Creating Connection Group with Virtual Nodes")
                        .contact(new Contact()
                                .name("Karthikeyan A")
                                .email("karthi250501@gmail.com")));
    }

}
