package com.hackathon.DesculpaAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe principal de inicialização da aplicação DesculpaAI.
 *
 * A aplicação é um hackathon que utiliza IA para gerar alibis criativose
 * absurdos para as mais variadas situações.
 *
 * Acesse a documentação em: http://localhost:8080/swagger-ui.html
 */
@SpringBootApplication
@Slf4j
public class DesculpaAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesculpaAiApplication.class, args);

        log.info("╔════════════════════════════════════════════╗");
        log.info("║     🎭 DesculpaAI Backend iniciado! 🎭     ║");
        log.info("╠════════════════════════════════════════════╣");
        log.info("║ Documentação: http://localhost:8080/swagger-ui.html");
        log.info("║ API Base URL: http://localhost:8080/api");
        log.info("║ API Docs: http://localhost:8080/v3/api-docs");
        log.info("╚════════════════════════════════════════════╝");
    }
}
