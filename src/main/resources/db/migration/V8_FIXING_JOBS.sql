ALTER TABLE job_vacancies
DROP COLUMN init_and_end_time;

ALTER TABLE job_vacancies
    ADD COLUMN init_time TIME;

ALTER TABLE job_vacancies
    ADD COLUMN end_time TIME;