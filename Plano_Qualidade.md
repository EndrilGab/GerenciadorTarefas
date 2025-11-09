# Plano de Gerenciamento da Qualidade

**Projeto:** Gerenciador de Tarefas (Backend Spring Boot + Frontend Angular)

**Equipe:** Desenvolvedores, QA, DevOps, Product Owner

**Versão do documento:** 1.0 — 08 de novembro de 2025

---

## 1. Objetivo do Plano de Qualidade

Definir critérios, processos, métricas e responsabilidades necessários para assegurar que o sistema "Gerenciador de Tarefas" atenda aos requisitos funcionais e não-funcionais acordados. O plano descreve como a qualidade será gerenciada durante o desenvolvimento, testes, entrega e manutenção.

Objetivos mensuráveis:
- Disponibilidade em ambiente de desenvolvimento: 99% (durante horários ativos de trabalho)
- Cobertura mínima de testes unitários (backend): 80% de linhas e 70% de instruções em serviços críticos
- Cobertura mínima de testes unitários (frontend): 70% com foco em services e componentes críticos
- Tempo médio de resolução de defeitos de severidade alta: < 48 horas
- Builds no CI com lint, testes e análise estática: 100% antes de merge na main

---

## 2. Requisitos de Qualidade

Funcionalidade:
- API REST completa para CRUD de tarefas, endpoints documentados via OpenAPI/Swagger.
- Frontend capaz de criar, editar, listar, alternar status e deletar tarefas com feedback claro ao usuário.

Desempenho:
- Tempo de resposta médio das APIs (pico de dev): < 200 ms para operações simples em H2 (dev); objetivos de produção a definir com DB real.
- Página principal do frontend deve carregar (bundle inicial) em < 2s em rede decente durante desenvolvimento local.

Segurança:
- Validação server-side de todos os inputs (Bean Validation).
- Proteção contra vulnerabilidades comuns (injeção, XSS, CSRF) documentadas e tratadas conforme práticas OWASP.

Usabilidade:
- Interface responsiva e acessível (botões com labels claros, mensagens de erro amigáveis).
- Feedback para ações (toasts/snackbars) com estados sucesso/erro/loading.

Confiabilidade e Manutenibilidade:
- Código modular e testável (camadas controller/service/repository no backend; componentes/serviços no frontend).
- Documentação de API atualizada e exemplos de requisição.

---

## 3. Papéis e Responsabilidades

- Product Owner: define prioridades, valida aceitação de requisitos.
- Gerente de Projeto / Tech Lead: coordena entregas, aprova checkpoints de qualidade.
- Desenvolvedores (Backend/Frontend): implementar features, escrever testes unitários, seguir padrões de codificação e PRs.
- Engenheiro de QA / Testes: escrever/rodar cenários de integração e E2E, validar regressões, reportar bugs.
- DevOps: manter pipeline de CI/CD, configurar ambientes, monitoramento e rollback.
- Revisor de Código: revisar PRs garantindo qualidade, segurança e padrões.

---

## 4. Processos de Qualidade

1. Code Review
	- Todo pull request deve ter pelo menos 1 revisor (preferencialmente 2 para mudanças críticas).
	- Checklists para revisão: arquitetura, testes, segurança, performance aparente, compatibilidade de API.

2. Integração Contínua (CI)
	- Pipeline acionado em PRs e commits na main.
	- Passos mínimos do pipeline: build backend (maven), lint + build frontend, testes unitários, análise estática (Sonar/JaCoCo/ESLint), gerar reports.
	- Falha em qualquer etapa bloqueia o merge.

3. Entrega Contínua (CD)
	- Deploy em ambiente de homologação após merge na main (ou via tag/convenção de release).
	- Validações pós-deploy (smoke tests) automatizadas.

4. Gestão de Defeitos
	- Todos bugs registrados no sistema de issues com severidade, steps para reproduzir e logs.
	- Priorização: Blocker > Crítico > Alto > Médio > Baixo.

5. Análise Estática e Lint
	- Backend: Checkstyle / SpotBugs e JaCoCo para cobertura.
	- Frontend: ESLint / Prettier configurados e executados no CI.

6. Auditoria e Revisão Periódica
	- Revisões de qualidade ao final de cada sprint/iteração (retrospectiva técnica).

---

## 5. Métricas e Indicadores

- Cobertura de testes (backend/frontend) — metas definidas na seção 1.
- Número de bugs abertos por sprint e por severidade.
- Tempo médio de resolução de bugs por severidade.
- Percentual de builds verdes no CI por semana.
- Tempo médio de execução da pipeline CI.
- Métricas de performance (tempo médio de resposta) e erros 5xx por hora.

Meta de aceitação:
- Antes de liberar para produção (ou homolog), todas as pipelines devem estar verdes, não deve haver bugs de severidade crítica não tratados, e os testes de integração/E2E devem passar.

---

## 6. Estratégia de Testes

1. Testes Unitários
	- Backend: JUnit 5 + Mockito para serviços e repositórios; foco em lógica de negócio (cobertura mínima 80% nas camadas críticas).
	- Frontend: Jasmine/Karma ou Jest para services e components críticos (cobertura mínima 70%).

2. Testes de Integração
	- Backend: testes que inicializam contexto Spring e banco H2 para validar contratos entre camadas.
	- Fluxos de API (endpoints) com dados reais simulados.

3. Testes E2E (End-to-End)
	- Ferramenta recomendada: Cypress (ou Playwright).
	- Cenários principais: criação, edição, deleção, toggle de status, tratamento de erros e fluxo de autenticação (se aplicado).

4. Testes de Regressão Automáticos
	- Rodar suites E2E no pipeline em cada release.

5. Testes de Performance (Smoke)
	- Execuções rápidas com JMeter ou k6 para validar latências após deploy em homolog.

6. Testes de Segurança
	- Verificação de dependências vulneráveis (OWASP Dependency-Check / npm audit).
	- Scans de segurança automáticos no CI.

Critérios de aceitação de um requisito:
- Todos os testes unitários referentes ao módulo passam.
- Não existem defeitos de severidade crítica abertos.
- Manual verification (UX) aprovada pelo PO para mudanças visuais.

---

## 7. Ferramentas, Ambiente e Integração

- Backend: Java 21, Maven (mvnw), Spring Boot 3.2.x, H2 (dev), JaCoCo, SpotBugs/Checkstyle.
- Frontend: Node.js >=18, Angular 17, Angular CLI, ESLint, Prettier, Jest/Karma.
- Testes E2E: Cypress ou Playwright.
- CI/CD: GitHub Actions (ou GitLab CI), jobs para build/test/analyze/deploy.
- Quality Gate: SonarQube (opcional) com políticas de cobertura e bugs críticos.
- Containers: Docker para homolog/produção.

Ambientes:
- Local (dev): H2 in-memory, localhost:8080 backend, localhost:4200 frontend.
- Homolog: PostgreSQL (ou DB similar), deploy automático via CD, smoke tests após deploy.
- Produção: configuração e políticas a definir (DB persistente, monitoramento, backup).

---

## 8. Cronograma e Checkpoints

- Checkpoint inicial (Kick-off qualidade): definição do plano — imediato.
- Integração do CI com lint e testes: até a próxima sprint.
- Cobertura alvo (unit/integration) monitorada semanalmente.
- Revisões formais de qualidade a cada entrega (end of sprint) com relatório de métricas.

Exemplo de gate para liberar para homolog:
1. PR aprovado por revisores.
2. Pipeline CI verde (build, lint, testes, análise estática).
3. No critical bugs open.
4. Smoke tests automatizados passaram em homolog.

---

## 9. Gestão de Riscos (Qualidade)

- Risco: Dependências incompatíveis (ex.: versão do SpringDoc vs Spring Boot)
  - Mitigação: Definir matriz de versões suportadas; executar testes de integração quando atualizar dependências.

- Risco: Falha no pipeline CI bloqueando entregas
  - Mitigação: Jobs paralelos, alertas, rollback automático e runbook para restauração.

- Risco: Cobertura de testes abaixo da meta
  - Mitigação: Tarefa de dívida técnica com prioridade e monitoramento de cobertura no Sonar/CI.

- Risco: Regressões visuais/UX
  - Mitigação: Testes E2E automatizados e revisão manual antes de releases.

---

## 10. Auditorias, Revisões e Aceitação

- Revisões de código obrigatórias antes de merge.
- Auditoria de qualidade (relatório de métricas) ao final de cada sprint.
- Critério de aceitação final para release: build verde, testes E2E passando, PO aprova interface/fluxos principais e não existem bugs críticos abertos.

---

## 11. Treinamento e Capacitação

- Sessão de onboarding para equipe com overview do projeto, padrões de código e pipeline CI.
- Documentos de referência e checklist de PRs disponíveis no repositório (README e MELHORIAS.md).

---

## 12. Documentação e Controle de Versões

- API documentada com OpenAPI/Swagger (`/swagger-ui.html`).
- Documentos de arquitetura, README, e guias (frontend/backend) mantidos no repo.
- Versionamento semântico para releases e changelog simples em cada tag.

---

## 13. Feedback e Melhoria Contínua

- Reuniões de retrospectiva técnica por sprint com levantamento de ações para melhoria de qualidade.
- Registro de lições aprendidas e atualização deste plano quando necessário.

---
