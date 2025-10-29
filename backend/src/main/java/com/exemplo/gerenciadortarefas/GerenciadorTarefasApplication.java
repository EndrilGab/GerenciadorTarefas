package com.exemplo.gerenciadortarefas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API Gerenciador de Tarefas",
        version = "1.0.0",
        description = "API REST para gerenciamento de tarefas com operações CRUD completas",
        contact = @Contact(name = "Suporte", email = "suporte@exemplo.com")
    )
)
public class GerenciadorTarefasApplication {
    public static void main(String[] args) {
        SpringApplication.run(GerenciadorTarefasApplication.class, args);
    }
}
