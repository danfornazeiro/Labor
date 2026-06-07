CREATE TABLE company (
                            id UUID PRIMARY KEY,

                            company_name VARCHAR(255),
                            cnpj VARCHAR(18),
                            description TEXT,
                            industry VARCHAR(255),
                            company_photo TEXT,
                            status VARCHAR(50),

                            user_id UUID UNIQUE,

                            CONSTRAINT fk_company_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(id)
                                    ON DELETE CASCADE
);