# RESUMO DAS CORREÇÕES - MIGRATIONS E ENTIDADES

## Problemas Encontrados e Corrigidos

### 1. **Nome da Tabela de Rating Inconsistente**
- **Problema**: Entidade declara `@Table(name = "rating")` mas as migrations criavam `ratings`
- **Correção**: V9 corrigida para criar `rating` (singular)
- **Impacto**: Migration consolidada

### 2. **Falta da Tabela `candidate_applications`**
- **Problema**: A entidade `CandidateApplication` estava completa mas não havia migration para criar a tabela
- **Correção**: V7 agora cria a tabela `candidate_applications` com todos os campos necessários
  - `candidate_id` (FK para `candidate`)
  - `job_id` (FK para `job_vacancies`)
  - `status` (ENUM: APPLIED, SELECTED, REJECTED)
  - `applied_at` (TIMESTAMP)
  - Constraint UNIQUE (candidate_id, job_id) para evitar duplicatas
- **Impacto**: Feature crítica de candidatura agora funciona corretamente

### 3. **Coluna Desnecessária em `candidate`**
- **Problema**: V7 (antigo) adicionava coluna `job_vacancy_id` em `candidate`, mas a entidade não tem esse campo
- **Correção**: Removida dessa lógica. Relacionamento correto está em `candidate_applications`
- **Impacto**: Evita inconsistências no banco

### 4. **Campo `sent_by` Inconsistente em `rating`**
- **Problema Anterior**:
  - V9 (antigo): Não tinha o campo
  - V11: Adicionava como FK UUID
  - V13: Removia FK e criava como VARCHAR
- **Correção**: V9 agora cria `sent_by VARCHAR(255)` de uma vez
- **Impacto**: Simplifica o histórico de migrações

### 5. **Tipos de Dados Incorretos em `job_vacancies`**
- **Problema**: 
  - `init_and_end_time TIMESTAMP` (deveria ser dois campos TIME)
  - `pay_value NUMERIC(38, 2)` (excessivo, deveria ser NUMERIC(10, 2))
- **Correção**: V6 agora cria corretamente:
  - `init_time TIME`
  - `end_time TIME`
  - `pay_value NUMERIC(10, 2)`
  - `company_id UUID` (com FK)
- **Impacto**: Alinha com as entidades JPA

### 6. **Imports Desnecessários**
- **Problema**: `Rating.java` importava `Candidate` mas não usava
- **Correção**: Removido import
- **Impacto**: Código mais limpo

### 7. **Migrations Vazias / Deprecadas**
- **Problema**: V8, V10, V11, V12, V13 continham lógica de correção de erros anteriores (ALTERs desnecessários)
- **Correção**: Marcadas como DEPRECATED com comentários explicativos
- **Impacto**: Histórico preservado, estrutura simplificada

---

## Estrutura Final do Banco de Dados

```sql
-- Tabelas principais
users (id, email, phone_number, password, role)
address (id, user_id, ...)
email (id, user_id, ...)
candidate (id, user_id, username, cpf, birth_date, ...)
company (id, user_id, company_name, cnpj, ...)
job_vacancies (id, company_id, title, ability, pay_value, init_time, end_time, date_job, description)
candidate_applications (id, candidate_id, job_id, status, applied_at)
rating (id, user_id, rating, rating_description, sent_by)

-- Constraints principais
- FK candidate.user_id → users.id (CASCADE)
- FK company.user_id → users.id (CASCADE)
- FK job_vacancies.company_id → company.id (SET NULL)
- FK candidate_applications.candidate_id → candidate.id (CASCADE)
- FK candidate_applications.job_id → job_vacancies.id (CASCADE)
- FK rating.user_id → users.id (CASCADE)
- UNIQUE (candidate_id, job_id) em candidate_applications
```

---

## Índices Adicionados

Para melhorar performance:

```sql
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_candidate_user_id ON candidate(user_id);
CREATE INDEX idx_company_user_id ON company(user_id);
CREATE INDEX idx_job_vacancies_company_id ON job_vacancies(company_id);
CREATE INDEX idx_candidate_applications_candidate_id ON candidate_applications(candidate_id);
CREATE INDEX idx_candidate_applications_job_id ON candidate_applications(job_id);
CREATE INDEX idx_rating_user_id ON rating(user_id);
```

---

## Como Usar as Correções

### Opção 1: Aplicar Incrementalmente (Recomendado)
Cada arquivo V1-V13 foi corrigido independentemente. Flyway aplicará na ordem.

### Opção 2: Reconstruir do Zero
Se quiser limpar e reconstruir, use `MIGRATIONS_CORRECTED_COMPLETE.sql` que consolida tudo.

### Passo a Passo para Reconstruir:

```sql
-- 1. Deletar banco antigo (cuidado!)
DROP DATABASE "Labor";

-- 2. Recriar banco vazio
CREATE DATABASE "Labor";

-- 3. Executar MIGRATIONS_CORRECTED_COMPLETE.sql
-- (Flyway hará isso automaticamente na próxima run)
```

---

## Validação

Após aplicar as migrações, verificar:

```sql
-- Verificar tabelas criadas
\dt

-- Verificar constraints
SELECT constraint_name FROM information_schema.table_constraints 
WHERE table_name IN (...);

-- Verificar índices
\di

-- Testar inserção de candidatura
INSERT INTO candidate_applications (id, candidate_id, job_id, status) 
VALUES (...);
```

---

## Próximos Passos

1. ✓ Migrations corrigidas
2. Testar aplicação com as novas migrations
3. Validar que `CandidateApplication` funciona corretamente
4. Atualizar controllers/services se necessário
5. Adicionar testes de integração

---

## Arquivo de Referência

Veja `MIGRATIONS_CORRECTED_COMPLETE.sql` na raiz do projeto para uma visão consolidada de toda a estrutura corrigida.

