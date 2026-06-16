# Labor

> **Projeto em desenvolvimento**
>
> Este sistema está **em construção** e pode passar por alterações frequentes na estrutura, nos endpoints, nas regras de negócio e no modelo de dados.
>
> Algumas partes podem mudar sem aviso prévio enquanto o projeto evolui.

## Visão geral

O **Labor** é uma aplicação backend em **Java 21** com **Spring Boot**, desenvolvida para apoiar um fluxo de vagas de emprego com autenticação via JWT, perfis de usuário e operações relacionadas a vagas, curtidas e avaliação.

A aplicação segue uma abordagem REST e utiliza banco de dados **PostgreSQL** com versionamento de schema via **Flyway**.

## Stack utilizada

- **Java 21**
- **Spring Boot 4**
- **Spring Web MVC**
- **Spring Security**
- **Spring Data JPA**
- **Flyway**
- **PostgreSQL**
- **JWT** com `java-jwt`
- **SpringDoc OpenAPI / Swagger UI**

## Estrutura principal do projeto

### `br.net.labor.LaborApplication`

Classe principal da aplicação Spring Boot.

### `config`

Contém configurações de segurança e autenticação:

- `SecurityConfig`: define as regras de acesso, sessão stateless e integração com o filtro de JWT.
- `SecurityFilter`: intercepta requisições, valida o token e monta o contexto de autenticação.
- `TokenService`: responsável pela geração e validação de JWT.
- `JWTUserData`: objeto usado para transportar dados do usuário autenticado.

### `controller`

Camada responsável por expor os endpoints REST:

- `AuthController`: login e cadastro de usuários.
- `JobsVacanciesController`: criação, listagem e exclusão de vagas.
- `LikeInJobsController`: ações de interesse/curtida em vagas.
- `RatingController`: avaliação de empresas.

### `service`

Contém a regra de negócio principal:

- `AuthService`: autenticação, registro de empresa, candidato e admin.
- `JobVacanciesService`: criação e consulta de vagas.
- `CandidateInJobService`: lógica de curtidas/interesse em vagas.
- `RatingService`: lógica de avaliação.

### `repository`

Interfaces de acesso ao banco de dados com Spring Data JPA.

### `model`

Contém entidades e DTOs do domínio:

- `user`: usuário base do sistema.
- `typeUser`: perfis como empresa e candidato.
- `jobs`: vagas de emprego.
- `rate`: entidades relacionadas a avaliação.
- `dto`: objetos de entrada e saída da API.

## Funcionalidades principais

### Autenticação

A aplicação possui fluxo de autenticação com JWT.

Endpoints principais:

- `POST /api/auth/login`
- `POST /api/auth/enterprise/register`
- `POST /api/auth/candidate/register`
- `POST /api/auth/admin/register`

### Vagas

A empresa autenticada pode criar vagas, e a API também possui listagem e remoção.

Endpoints:

- `POST /api/jobsVacancies`
- `GET /api/jobsVacancies`
- `DELETE /api/jobsVacancies/{id}`

### Curtir vaga

O candidato autenticado pode demonstrar interesse em uma vaga.

Endpoint:

- `POST /api/likeInJobs/{id}`

### Avaliação

Permite registrar avaliações relacionadas à empresa.

Endpoints:

- `POST /api/rating/{id}`
- `GET /api/rating`

## Segurança

O projeto usa autenticação via JWT com política de sessão **stateless**.

Regras gerais observadas no `SecurityConfig`:

- ` /api/auth/**` liberado publicamente
- documentação Swagger liberada
- rotas protegidas por perfil de acesso

Perfis previstos no sistema:

- `ADMIN`
- `COMPANY`
- `CANDIDATE`

## Banco de dados e migrações

O projeto usa **Flyway** para controlar a evolução do schema.

As migrações ficam em:

- `src/main/resources/db/migration`

## Configuração

As configurações principais estão em:

- `src/main/resources/application.properties`

Lá ficam parâmetros como:

- URL do banco
- usuário e senha do PostgreSQL
- configuração do Hibernate
- chave secreta do JWT

> Observação: mantenha esses dados sensíveis protegidos em ambientes reais.

## Como executar

### Pré-requisitos

- Java 21
- Maven Wrapper disponível no projeto
- PostgreSQL em execução

### Subindo a aplicação

```powershell
cd D:\felipeCoisas\JAVA\labor
.\mvnw.cmd spring-boot:run
```

### Gerando build

```powershell
cd D:\felipeCoisas\JAVA\labor
.\mvnw.cmd -DskipTests package
```

## Observações importantes

- Este projeto está em desenvolvimento.
- Estruturas de DTO, endpoints e regras de acesso podem mudar.
- Alguns nomes de pacotes e entidades ainda refletem evolução interna do projeto.
- É recomendável revisar a documentação conforme novas features forem sendo adicionadas.

## Próximos passos sugeridos

- Padronizar nomes de pacotes e DTOs
- Documentar exemplos de request/response de cada endpoint
- Adicionar testes automatizados para controllers e serviços
- Melhorar tratamento de erros com respostas HTTP padronizadas

## Autor / manutenção

Projeto mantido em evolução contínua.

