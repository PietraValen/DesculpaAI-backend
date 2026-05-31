package com.hackathon.DesculpaAI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração de CORS (Cross-Origin Resource Sharing) para a aplicação.
 * Permite que o frontend acesse os endpoints da API sem erros de CORS.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                // Domínios permitidos (ajuste conforme necessário)
                .allowedOrigins(
                        "http://localhost:3000", // Frontend local (React/Next.js)
                        "http://localhost:5173", // Frontend local (Vite)
                        "http://localhost:8000", // Frontend local (Python)
                        "http://127.0.0.1:3000",
                        "http://127.0.0.1:5173",
                        "http://127.0.0.1:8000"
                // Adicione aqui os domínios de produção quando necessário
                // "https://desculpaai.com",
                // "https://www.desculpaai.com"
                )
                // Métodos HTTP permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                // Headers permitidos
                .allowedHeaders("*")
                // Headers expostos para o cliente
                .exposedHeaders("Authorization", "X-Total-Count", "X-Page-Count")
                // Permitir credenciais (cookies, autenticação)
                .allowCredentials(true)
                // Tempo máximo em segundos que o resultado do preflight pode ser cacheado
                .maxAge(3600);

        // Configuração adicional para Swagger/OpenAPI
        registry.addMapping("/swagger-ui/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "OPTIONS")
                .maxAge(3600);

        registry.addMapping("/v3/api-docs/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "OPTIONS")
                .maxAge(3600);
    }
}
