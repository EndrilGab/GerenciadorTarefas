# 📝 Gerenciador de Tarefas - Full Stack

Sistema completo de gerenciamento de tarefas desenvolvido com **Spring Boot** (Backend) e **Angular** (Frontend).

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)
![Angular](https://img.shields.io/badge/Angular-17-red)
![TypeScript](https://img.shields.io/badge/TypeScript-5.0-blue)

---

## 📋 Índice

1. [Sobre o Projeto](#-sobre-o-projeto)
2. [Tecnologias Utilizadas](#-tecnologias-utilizadas)
3. [Instalação](#-instalação)
4. [Como Executar](#-como-executar)
5. [Funcionalidades](#-funcionalidades)
6. [Estrutura do Projeto](#-estrutura-do-projeto)
7. [API Endpoints](#-api-endpoints)
8. [Arquitetura](#-arquitetura)
9. [Decisões Técnicas](#-decisões-técnicas)
10. [Melhorias Futuras](#-melhorias-futuras)

---

## 🎯 Sobre o Projeto

Sistema full-stack para gerenciamento de tarefas com interface moderna e API RESTful robusta. Permite criar, editar, listar, deletar e alternar o status de tarefas de forma intuitiva.

### **Características Principais:**

✅ **CRUD Completo** - Todas operações de gerenciamento  
✅ **Interface Responsiva** - Funciona em desktop, tablet e mobile  
✅ **Validações** - Frontend e Backend validam dados  
✅ **Tratamento de Erros** - Mensagens claras e específicas  
✅ **Documentação Interativa** - Swagger UI integrado  
✅ **Banco em Memória** - H2 Database para desenvolvimento rápido  
✅ **Design Moderno** - Material Design com animações  

---

## 🚀 Tecnologias Utilizadas

### **Backend**
- **Java 21** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework web
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de boilerplate
- **Bean Validation** - Validação de dados
- **SpringDoc OpenAPI** - Documentação Swagger
- **JUnit 5** - Testes unitários
- **Maven** - Gerenciador de dependências

### **Frontend**
- **Angular 17** - Framework SPA
- **TypeScript 5.0** - Linguagem tipada
- **Angular Material** - Biblioteca de componentes UI
- **RxJS** - Programação reativa
- **SCSS** - Pré-processador CSS
- **Standalone Components** - Arquitetura moderna do Angular

---

## 📦 Instalação

### **Pré-requisitos**

Antes de começar, certifique-se de ter instalado:

#### **1. Java 21+**
```bash
# Verificar versão
java -version

# Download: https://www.oracle.com/java/technologies/downloads/
```

#### **2. Node.js 18+ e npm**
```bash
# Verificar versões
node --version
npm --version

# Download: https://nodejs.org/
```

#### **3. Angular CLI**
```bash
# Instalar globalmente
npm install -g @angular/cli@17

# Verificar instalação
ng version
```

#### **4. Git (opcional)**
```bash
# Verificar instalação
git --version

# Download: https://git-scm.com/
```

---

### **Clone do Projeto**

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre na pasta
cd gerenciadortarefas
```

### **Instalação do Frontend**

```bash
# Entre na pasta frontend
cd frontend/gerenciador-tarefas-front

# Instale as dependências
npm install

# Instale Angular Material (se não instalado)
ng add @angular/material
```

---

## ▶️ Como Executar

### **1️⃣ Iniciar o Backend**

```bash
# Na pasta backend
cd backend

# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

**Aguarde a mensagem:** `Started GerenciadorTarefasApplication in X seconds`

✅ Backend rodando em: **http://localhost:8080**

---

### **2️⃣ Iniciar o Frontend**

```bash
# Em outro terminal, na pasta frontend
cd frontend/gerenciador-tarefas-front

# Iniciar servidor de desenvolvimento
ng serve
```

**Aguarde a mensagem:** `✔ Compiled successfully`

✅ Frontend rodando em: **http://localhost:4200**

---

### **3️⃣ Acessar a Aplicação**

Abra seu navegador e acesse:

- **Aplicação:** http://localhost:4200
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **API Docs JSON:** http://localhost:8080/api-docs
- **H2 Console:** http://localhost:8080/h2-console

#### **Credenciais H2 Console:**
- **JDBC URL:** `jdbc:h2:mem:tarefasdb`
- **Username:** `sa`
- **Password:** _(deixe vazio)_

---

## ✨ Funcionalidades

### **Interface do Usuário**

#### **1. Listar Tarefas**
- Grid responsivo com cards
- Indicador visual de status (concluída/pendente)
- Informações de criação e atualização
- Loading state durante carregamento
- Empty state quando não há tarefas

#### **2. Criar Tarefa**
- Modal popup centralizado
- Campos:
  - **Título** (obrigatório, máx 100 caracteres)
  - **Descrição** (opcional, máx 500 caracteres)
  - **Status** (checkbox concluída/pendente)
- Validação em tempo real
- Contador de caracteres
- Loading durante salvamento

#### **3. Editar Tarefa**
- Mesmo modal de criação, pré-preenchido
- Atualiza título, descrição e status
- Mantém dados de auditoria

#### **4. Deletar Tarefa**
- Confirmação antes de deletar
- Mensagem de sucesso/erro
- Atualização automática da lista

#### **5. Alternar Status**
- Um clique para marcar como concluída/pendente
- Feedback visual imediato
- Mensagem de confirmação

#### **6. Tratamento de Erros**
- Mensagens específicas por tipo de erro:
  - 🔌 Backend não está respondendo
  - 🔍 Tarefa não encontrada
  - ⚠️ Erro no servidor
  - ❌ Dados inválidos
- Notificações com cores (verde=sucesso, vermelho=erro)
- Logs detalhados no console

---

## 📁 Estrutura do Projeto

```
gerenciadortarefas/
├── backend/                          # Backend Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/exemplo/gerenciadortarefas/
│   │   │   │   ├── GerenciadorTarefasApplication.java    # Classe principal
│   │   │   │   ├── config/
│   │   │   │   │   └── CorsConfig.java                   # Configuração CORS
│   │   │   │   ├── controller/
│   │   │   │   │   ├── TarefaController.java             # Endpoints REST
│   │   │   │   │   └── HealthController.java             # Health check
│   │   │   │   ├── dto/
│   │   │   │   │   ├── TarefaRequestDTO.java             # DTO de entrada
│   │   │   │   │   └── TarefaResponseDTO.java            # DTO de saída
│   │   │   │   ├── exception/
│   │   │   │   │   ├── GlobalExceptionHandler.java       # Tratamento global
│   │   │   │   │   ├── ResourceNotFoundException.java    # Exception customizada
│   │   │   │   │   └── ErrorResponse.java                # Modelo de erro
│   │   │   │   ├── model/
│   │   │   │   │   └── Tarefa.java                       # Entidade JPA
│   │   │   │   ├── repository/
│   │   │   │   │   └── TarefaRepository.java             # Interface JPA
│   │   │   │   └── service/
│   │   │   │       └── TarefaService.java                # Lógica de negócio
│   │   │   └── resources/
│   │   │       └── application.properties                # Configurações
│   │   └── test/                                         # Testes unitários
│   ├── pom.xml                                           # Dependências Maven
│   ├── mvnw e mvnw.cmd                                   # Maven Wrapper
│   └── README.md                                         # Documentação backend
│
└── frontend/                         # Frontend Angular
    └── gerenciador-tarefas-front/
        ├── src/
        │   ├── app/
        │   │   ├── components/
        │   │   │   ├── lista-tarefas/                    # Componente de listagem
        │   │   │   │   ├── lista-tarefas.component.ts
        │   │   │   │   ├── lista-tarefas.component.html
        │   │   │   │   └── lista-tarefas.component.scss
        │   │   │   └── formulario-tarefa/                # Componente de formulário
        │   │   │       ├── formulario-tarefa.component.ts
        │   │   │       ├── formulario-tarefa.component.html
        │   │   │       └── formulario-tarefa.component.scss
        │   │   ├── models/
        │   │   │   └── tarefa.model.ts                   # Interfaces TypeScript
        │   │   ├── services/
        │   │   │   └── tarefa.service.ts                 # Service HTTP
        │   │   ├── app.component.ts                      # Componente raiz
        │   │   ├── app.config.ts                         # Configurações
        │   │   └── app.routes.ts                         # Rotas
        │   ├── styles.scss                               # Estilos globais
        │   └── index.html                                # HTML principal
        ├── angular.json                                  # Configuração Angular
        ├── package.json                                  # Dependências npm
        └── tsconfig.json                                 # Configuração TypeScript
```

---

## 🌐 API Endpoints

### **Base URL:** `/api/v1/tarefas`

| Método | Endpoint | Descrição | Body | Response |
|--------|----------|-----------|------|----------|
| **GET** | `/api/v1/tarefas` | Lista todas as tarefas | - | `200 OK` Array de TarefaResponseDTO |
| **GET** | `/api/v1/tarefas/{id}` | Busca tarefa por ID | - | `200 OK` TarefaResponseDTO |
| **POST** | `/api/v1/tarefas` | Cria nova tarefa | TarefaRequestDTO | `201 Created` TarefaResponseDTO |
| **PUT** | `/api/v1/tarefas/{id}` | Atualiza tarefa completa | TarefaRequestDTO | `200 OK` TarefaResponseDTO |
| **PATCH** | `/api/v1/tarefas/{id}/toggle` | Alterna status | - | `200 OK` TarefaResponseDTO |
| **DELETE** | `/api/v1/tarefas/{id}` | Remove tarefa | - | `204 No Content` |

### **Exemplos de Requisições**

#### **Criar Tarefa (POST)**
```bash
POST http://localhost:8080/api/v1/tarefas
Content-Type: application/json

{
  "titulo": "Estudar Spring Boot",
  "descricao": "Aprender conceitos avançados",
  "concluida": false
}
```

#### **Resposta de Sucesso**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Aprender conceitos avançados",
  "concluida": false,
  "criadoEm": "2025-10-29T10:30:00",
  "atualizadoEm": "2025-10-29T10:30:00"
}
```

#### **Resposta de Erro**
```json
{
  "timestamp": "2025-10-29T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Tarefa não encontrada com ID: 999",
  "path": "/api/v1/tarefas/999"
}
```

---

## 🏗️ Arquitetura

### **Backend - Arquitetura em Camadas**

```
┌─────────────────────────────────────────┐
│          CONTROLLER LAYER               │  ← REST Endpoints
│  - TarefaController                     │
│  - HealthController                     │
│  - Validações @Valid                    │
│  - Documentação Swagger                 │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│           SERVICE LAYER                 │  ← Lógica de Negócio
│  - TarefaService                        │
│  - @Transactional                       │
│  - Regras de negócio                    │
│  - Logs                                 │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         REPOSITORY LAYER                │  ← Acesso a Dados
│  - TarefaRepository                     │
│  - Spring Data JPA                      │
│  - Queries automáticas                  │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│          DATABASE (H2)                  │  ← Persistência
│  - Banco em memória                     │
│  - jdbc:h2:mem:tarefasdb                │
└─────────────────────────────────────────┘

         ↔ DTOs (Request/Response) ↔
         ↔ Exception Handling ↔
         ↔ CORS Configuration ↔
```

### **Frontend - Arquitetura de Componentes**

```
┌─────────────────────────────────────────┐
│         APP COMPONENT                   │  ← Raiz
│  - Toolbar                              │
│  - Router Outlet                        │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│      LISTA TAREFAS COMPONENT            │  ← Lista
│  - Grid de cards                        │
│  - Loading/Empty states                 │
│  - Ações (editar, deletar, toggle)      │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│    FORMULARIO TAREFA COMPONENT          │  ← Modal
│  - Reactive Forms                       │
│  - Validações                           │
│  - Submit/Cancel                        │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         TAREFA SERVICE                  │  ← HTTP
│  - HttpClient                           │
│  - CRUD Operations                      │
│  - Error Handling                       │
└─────────────────────────────────────────┘
                    ↓
         API REST (localhost:8080)
```

---

## 🔧 Decisões Técnicas

### **Backend**

#### **1. Spring Boot 3.2.0**
- **Por quê?** Versão estável com suporte para Java 21 e compatível com SpringDoc OpenAPI
- **Downgrade de 3.5.7** para corrigir erro 500 no Swagger

#### **2. DTOs (Data Transfer Objects)**
- **Por quê?** Separa modelo de persistência da API
- **Benefícios:**
  - Controle sobre dados expostos
  - Validações específicas por operação
  - Não expõe entidade diretamente

#### **3. Service Layer**
- **Por quê?** Separação de responsabilidades
- **Benefícios:**
  - Lógica de negócio isolada
  - Controller enxuto
  - Transações gerenciadas
  - Fácil testar

#### **4. Global Exception Handler**
- **Por quê?** Tratamento centralizado de erros
- **Benefícios:**
  - Respostas padronizadas
  - Código mais limpo
  - Fácil manutenção

#### **5. H2 Database**
- **Por quê?** Desenvolvimento rápido sem setup
- **Para produção:** Trocar por PostgreSQL/MySQL

#### **6. Bean Validation**
- **Por quê?** Validações declarativas
- **Anotações:** `@NotBlank`, `@Size`, `@Valid`

#### **7. Lombok**
- **Por quê?** Reduz código boilerplate
- **Anotações:** `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@RequiredArgsConstructor`

#### **8. CORS Configurado**
- **Por quê?** Permitir requisições do frontend
- **Origens:** `localhost:4200`, `localhost:3000`, `localhost:8080`

---

### **Frontend**

#### **1. Angular 17 com Standalone Components**
- **Por quê?** Arquitetura moderna, sem módulos
- **Benefícios:**
  - Código mais simples
  - Lazy loading automático
  - Menos boilerplate

#### **2. Angular Material**
- **Por quê?** Componentes prontos e bonitos
- **Componentes usados:**
  - Cards, Buttons, Icons
  - Form Fields, Inputs, Checkboxes
  - Snackbar, Tooltip, Spinner
  - Toolbar

#### **3. Reactive Forms**
- **Por quê?** Controle total sobre validações
- **Benefícios:**
  - Validações síncronas/assíncronas
  - Fácil testar
  - TypeScript type-safe

#### **4. RxJS e Observables**
- **Por quê?** Padrão do Angular para HTTP
- **Benefícios:**
  - Programação reativa
  - Cancelamento automático
  - Operators poderosos

#### **5. SCSS**
- **Por quê?** Mais poder que CSS puro
- **Features:**
  - Variáveis
  - Nesting
  - Mixins
  - Funções

#### **6. Modal Popup para Formulário**
- **Por quê?** Melhor UX
- **Benefícios:**
  - Não perde contexto da lista
  - Animação suave
  - Foco na tarefa
  - Pode fechar clicando fora

#### **7. Error Handling Específico**
- **Por quê?** Usuário sabe exatamente o que aconteceu
- **Status HTTP tratados:**
  - 0: Sem conexão
  - 400: Dados inválidos
  - 404: Não encontrado
  - 500: Erro no servidor

#### **8. Loading States**
- **Por quê?** Feedback visual durante requisições
- **Implementado em:**
  - Lista completa
  - Botões de ação
  - Formulário

---

## 🎨 Design Patterns Utilizados

### **Backend**

1. **MVC (Model-View-Controller)** - Separação de camadas
2. **DTO Pattern** - Transferência de dados
3. **Repository Pattern** - Abstração de acesso a dados
4. **Service Layer Pattern** - Lógica de negócio
5. **Dependency Injection** - Inversão de controle
6. **Builder Pattern** - Lombok generates builders

### **Frontend**

1. **Component Pattern** - Componentes reutilizáveis
2. **Service Pattern** - Lógica compartilhada
3. **Observer Pattern** - RxJS Observables
4. **Reactive Forms** - Formulários reativos
5. **Standalone Components** - Arquitetura moderna

---

## 🧪 Testes

### **Backend - Testes Unitários**

```bash
# Executar testes
cd backend
mvnw test

# Testes incluem:
# ✅ Criar tarefa
# ✅ Listar todas
# ✅ Buscar por ID
# ✅ Atualizar tarefa
# ✅ Deletar tarefa
# ✅ Alternar status
```

### **Frontend - Testes (Opcional)**

```bash
# Executar testes
cd frontend/gerenciador-tarefas-front
ng test
```

---

## 📊 Validações

### **Backend**
- **Título:** Obrigatório, 3-100 caracteres
- **Descrição:** Opcional, máximo 500 caracteres
- **Concluída:** Booleano, padrão false

### **Frontend**
- **Título:** Obrigatório, máximo 100 caracteres
- **Descrição:** Opcional, máximo 500 caracteres
- **Validação em tempo real** com mensagens de erro

---

## 🚀 Melhorias Futuras

### **Funcionalidades**
- [ ] Autenticação e autorização (JWT)
- [ ] Categorias e tags para tarefas
- [ ] Prioridades (baixa, média, alta, urgente)
- [ ] Prazo de conclusão
- [ ] Filtros e busca avançada
- [ ] Paginação da lista
- [ ] Ordenação customizada
- [ ] Modo escuro (Dark Mode)
- [ ] Notificações push
- [ ] Compartilhamento de tarefas

### **Técnicas**
- [ ] Banco de dados persistente (PostgreSQL)
- [ ] Cache com Redis
- [ ] Testes E2E (Cypress/Playwright)
- [ ] CI/CD (GitHub Actions)
- [ ] Docker containerization
- [ ] Kubernetes deployment
- [ ] Monitoramento (Prometheus/Grafana)
- [ ] Logs centralizados (ELK Stack)
- [ ] Rate limiting
- [ ] PWA (Progressive Web App)

---

## 📝 Scripts Úteis

### **Backend**
```bash
# Compilar
mvnw clean compile

# Rodar testes
mvnw test

# Gerar JAR
mvnw clean package

# Rodar aplicação
mvnw spring-boot:run

# Limpar target
mvnw clean
```

### **Frontend**
```bash
# Instalar dependências
npm install

# Desenvolvimento
ng serve

# Build produção
ng build --configuration production

# Testes
ng test

# Lint
ng lint

# Ver bundle size
ng build --stats-json
```

---

## 🐛 Troubleshooting

### **Erro: Backend não inicia**
```bash
# Verificar Java
java -version

# Deve ser 21+
# Se não, instale Java 21
```

### **Erro: Frontend não compila**
```bash
# Limpar node_modules
rm -rf node_modules package-lock.json
npm install
```

### **Erro: CORS**
```bash
# Verificar CorsConfig.java
# Deve incluir: http://localhost:4200
```

### **Erro: Porta em uso**
```bash
# Backend (8080)
# Windows: netstat -ano | findstr :8080
# Linux/Mac: lsof -i :8080

# Frontend (4200)
# Use: ng serve --port 4201
```

---

## 📄 Licença

Este projeto é de código aberto e está disponível sob a licença MIT.

---

## 👤 Autor

Desenvolvido como projeto de demonstração de aplicação Full Stack com Spring Boot e Angular.

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

## 📞 Suporte

Para dúvidas ou problemas:
- Abra uma [issue](https://github.com/seu-usuario/gerenciadortarefas/issues)
- Consulte a [documentação do Spring Boot](https://spring.io/projects/spring-boot)
- Consulte a [documentação do Angular](https://angular.io/docs)

---

## 🎓 Recursos de Aprendizado

### **Spring Boot**
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring REST Docs](https://spring.io/guides/gs/rest-service/)

### **Angular**
- [Angular Documentation](https://angular.io/docs)
- [Angular Material](https://material.angular.io/)
- [RxJS Documentation](https://rxjs.dev/)

---

**⭐ Se este projeto foi útil, considere dar uma estrela no GitHub!**

---

*Última atualização: 29 de outubro de 2025*
