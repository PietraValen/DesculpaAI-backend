package com.hackathon.DesculpaAI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configuração do OpenAPI/Swagger para a aplicação.
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor de Desenvolvimento"))
                .addServersItem(new Server()
                        .url("https://api.desculpaai.com")
                        .description("Servidor de Produção"))
                .info(new Info()
                        .title("DesculpaAI API")
                        .version("1.0.0")
                        .description("API para geração de alibis usando Inteligência Artificial. "
                                + "Uma aplicação desenvolvida para hackathon que gera desculpas criativas "
                                + "e absurdas para as mais variadas situações.")
                        .contact(new Contact()
                                .name("DesculpaAI Team")
                                .email("contato@desculpaai.com")
                                .url("https://github.com/PietraValen/DesculpaAI-backend"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
