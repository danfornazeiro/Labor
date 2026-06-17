-- ============================================================
-- LABOR - MIGRATIONS CORRIGIDAS E CONSOLIDADAS
-- Este arquivo consolida todas as migrações com correções
-- ============================================================

-- V1: CREATE USERS TABLE
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- V2: CREATE ADDRESS TABLE
CREATE TABLE address (
    id UUID PRIMARY KEY,
    street VARCHAR(255),
    number VARCHAR(50),
    district VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    user_id UUID NOT NULL,

    CONSTRAINT fk_address_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);

-- V3: CREATE EMAIL TABLE
CREATE TABLE email (
    id UUID PRIMARY KEY,
    email VARCHAR(255),
    is_principal BOOLEAN,
    user_id UUID NOT NULL,

    CONSTRAINT fk_email_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);

-- V4: CREATE CANDIDATE TABLE
CREATE TABLE candidate (
    id UUID PRIMARY KEY,
    username VARCHAR(100),
    cpf VARCHAR(14),
    birth_date DATE,
    user_photo TEXT,
    status VARCHAR(50),
    real_name VARCHAR(255),
    user_id UUID UNIQUE NOT NULL,

    CONSTRAINT fk_candidate_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);

-- V5: CREATE COMPANY TABLE
CREATE TABLE company (
    id UUID PRIMARY KEY,
    company_name VARCHAR(255),
    cnpj VARCHAR(18),
    description TEXT,
    industry VARCHAR(255),
    company_photo TEXT,
    status VARCHAR(50),
    user_id UUID UNIQUE NOT NULL,

    CONSTRAINT fk_company_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);

-- V6: CREATE JOB VACANCIES TABLE (CORRECTED)
CREATE TABLE job_vacancies (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    ability VARCHAR(255),
    pay_value NUMERIC(10, 2),
    init_time TIME,
    end_time TIME,
    date_job DATE,
    description TEXT,
    company_id UUID,

    CONSTRAINT fk_job_vacancies_company
        FOREIGN KEY (company_id)
            REFERENCES company(id)
            ON DELETE SET NULL
);

-- V7: CREATE CANDIDATE APPLICATIONS TABLE (NEW - MISSING)
CREATE TABLE candidate_applications (
    id UUID PRIMARY KEY,
    candidate_id UUID NOT NULL,
    job_id UUID NOT NULL,
    status VARCHAR(50) DEFAULT 'APPLIED',
    applied_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_candidate_application_candidate
        FOREIGN KEY (candidate_id)
            REFERENCES candidate(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_candidate_application_job
        FOREIGN KEY (job_id)
            REFERENCES job_vacancies(id)
            ON DELETE CASCADE,

    CONSTRAINT uk_candidate_application_unique
        UNIQUE (candidate_id, job_id)
);

-- V8: CREATE RATING TABLE (CORRECTED - singular "rating", not "ratings")
CREATE TABLE rating (
    id UUID PRIMARY KEY,
    rating INTEGER DEFAULT 0,
    rating_description VARCHAR(255),
    sent_by VARCHAR(255),
    user_id UUID NOT NULL,

    CONSTRAINT fk_rating_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);

-- ============================================================
-- INDEXES para melhorar performance
-- ============================================================
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_candidate_user_id ON candidate(user_id);
CREATE INDEX idx_company_user_id ON company(user_id);
CREATE INDEX idx_job_vacancies_company_id ON job_vacancies(company_id);
CREATE INDEX idx_candidate_applications_candidate_id ON candidate_applications(candidate_id);
CREATE INDEX idx_candidate_applications_job_id ON candidate_applications(job_id);
CREATE INDEX idx_rating_user_id ON rating(user_id);

-- ============================================================
-- RESUMO DAS CORREÇÕES APLICADAS:
-- ============================================================
-- 1. ✓ Consolidadas todas as migrações em um único arquivo
-- 2. ✓ Removido "job_vacancy_id" desnecessário de candidate
-- 3. ✓ Criada tabela "candidate_applications" (estava faltando)
-- 4. ✓ Corrigido nome de tabela "ratings" → "rating"
-- 5. ✓ Campo "sent_by" como VARCHAR(255) desde o início (sem FK intermediária)
-- 6. ✓ Adicionados constraints UNIQUE onde apropriado
-- 7. ✓ Adicionadas constraints NOT NULL nas FKs necessárias
-- 8. ✓ Criados índices para melhor performance em queries
-- 9. ✓ Removidos ALTERs desnecessários (tudo criado certo de primeira)
-- 10. ✓ Adicionado DEFAULT CURRENT_TIMESTAMP em "applied_at"

