# 📝 API Gerenciador de Tarefas

API REST completa para gerenciamento de tarefas desenvolvida com Spring Boot 3.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **Lombok** (redução de código boilerplate)
- **Bean Validation**
- **Swagger/OpenAPI** (documentação interativa)
- **JUnit 5** (testes)

## 📋 Funcionalidades

- ✅ CRUD completo de tarefas
- ✅ Validação de dados
- ✅ Tratamento global de exceções
- ✅ Auditoria (campos de criação e atualização)
- ✅ Toggle de status de tarefa
- ✅ Documentação interativa com Swagger
- ✅ Logs estruturados
- ✅ Testes unitários

## 🔧 Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6+

### Executar o Projeto

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre na pasta do projeto
cd gerenciadortarefas

# Execute com Maven
mvnw spring-boot:run

# Ou no Windows
mvnw.cmd spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

Após iniciar a aplicação, acesse:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs
- **H2 Console**: http://localhost:8080/h2-console

### Credenciais H2 Console
- **JDBC URL**: jdbc:h2:mem:tarefasdb
- **Username**: sa
- **Password**: (vazio)

## 🛣️ Endpoints da API

### Base URL: `/api/v1/tarefas`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/v1/tarefas` | Lista todas as tarefas |
| GET | `/api/v1/tarefas/{id}` | Busca tarefa por ID |
| POST | `/api/v1/tarefas` | Cria nova tarefa |
| PUT | `/api/v1/tarefas/{id}` | Atualiza tarefa completa |
| PATCH | `/api/v1/tarefas/{id}/toggle` | Alterna status (concluída/pendente) |
| DELETE | `/api/v1/tarefas/{id}` | Remove tarefa |

## 📦 Exemplos de Requisições

### Criar Tarefa (POST)

```json
POST /api/v1/tarefas
Content-Type: application/json

{
  "titulo": "Estudar Spring Boot",
  "descricao": "Estudar os conceitos avançados do Spring Boot",
  "concluida": false
}
```

### Atualizar Tarefa (PUT)

```json
PUT /api/v1/tarefas/1
Content-Type: application/json

{
  "titulo": "Estudar Spring Boot - Atualizado",
  "descricao": "Foco em Spring Data JPA e Spring Security",
  "concluida": true
}
```

### Resposta de Sucesso

```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Estudar os conceitos avançados do Spring Boot",
  "concluida": false,
  "criadoEm": "2025-10-29T10:30:00",
  "atualizadoEm": "2025-10-29T10:30:00"
}
```

### Resposta de Erro

```json
{
  "timestamp": "2025-10-29T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Tarefa não encontrada com ID: 999",
  "path": "/api/v1/tarefas/999"
}
```

## 🧪 Executar Testes

```bash
# Executar todos os testes
mvnw test

# Ou no Windows
mvnw.cmd test
```

## 🏗️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/exemplo/gerenciadortarefas/
│   │       ├── config/          # Configurações (CORS, etc)
│   │       ├── controller/      # Controladores REST
│   │       ├── dto/             # Data Transfer Objects
│   │       ├── exception/       # Tratamento de exceções
│   │       ├── model/           # Entidades JPA
│   │       ├── repository/      # Repositórios Spring Data
│   │       └── service/         # Lógica de negócio
│   └── resources/
│       └── application.properties
└── test/
    └── java/                    # Testes unitários
```

## 📝 Validações

- **Título**: obrigatório, entre 3 e 100 caracteres
- **Descrição**: opcional, máximo 500 caracteres
- **Concluída**: booleano, padrão false

## 🔒 CORS

Configurado para aceitar requisições de:
- http://localhost:3000 (React/Frontend)
- http://localhost:8080 (mesma origem)

Para produção, ajuste as origens permitidas em `CorsConfig.java`.

## 📊 Monitoramento

Endpoints do Spring Actuator disponíveis:
- `/actuator/health` - Status da aplicação
- `/actuator/info` - Informações da aplicação
- `/actuator/metrics` - Métricas

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.

## 👤 Autor

Desenvolvido como projeto de demonstração de API REST com Spring Boot.

---

**Nota**: Este é um projeto de exemplo para fins educacionais. Para uso em produção, considere adicionar:
- Autenticação e Autorização (Spring Security)
- Banco de dados persistente (PostgreSQL, MySQL)
- Paginação e ordenação
- Cache (Redis)
- Rate limiting
- Docker containerization
