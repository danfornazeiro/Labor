ALTER TABLE job_vacancies
    ADD COLUMN company_id UUID;

ALTER TABLE job_vacancies
    ADD CONSTRAINT fk_job_vacancies_company
        FOREIGN KEY (company_id) REFERENCES company(id)
            ON DELETE SET NULL;

ALTER TABLE candidate
    ADD COLUMN job_vacancy_id UUID;

ALTER TABLE candidate
    ADD CONSTRAINT fk_candidate_job_vacancies
        FOREIGN KEY (job_vacancy_id) REFERENCES job_vacancies(id)
            ON DELETE SET NULL;