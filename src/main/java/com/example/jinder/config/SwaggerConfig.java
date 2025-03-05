package com.example.jinder.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("gilmanovamir19@gmail.com");
        contact.setName("Amir Gilmanov");
        contact.setUrl("https://t.me/krutojparen");

        Info info = new Info()
                .title("Jinder")
                .version("1.0")
                .contact(contact)
                .description("Api сервис для знакомств");
        return new OpenAPI().info(info);
    }
}
