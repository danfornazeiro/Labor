CREATE TABLE job_vacancies (
                               id UUID PRIMARY KEY,
                               title VARCHAR(255) NOT NULL,
                               ability VARCHAR(255),
                               pay_value NUMERIC(38, 2),
                               init_and_end_time TIMESTAMP WITHOUT TIME ZONE,
                               date_job DATE,
                               description TEXT
);