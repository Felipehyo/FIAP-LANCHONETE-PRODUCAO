package com.postech;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Pós Tech - Arquitetura Hexagonal - Serviço de Produção de Pedidos",
                version = "1.0",
                description = "Aplicação responsável por fornecer o processo de operacionalização da produção de um pedido, oferecendo as funcionalidade de iniciar e finalizar o preparo de um pedido."
        )
)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
