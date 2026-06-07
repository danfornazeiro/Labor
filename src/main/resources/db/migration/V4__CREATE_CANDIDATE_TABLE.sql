CREATE TABLE candidate (
                              id UUID PRIMARY KEY,

                              username VARCHAR(100),
                              cpf VARCHAR(14),
                              birth_date DATE,
                              user_photo TEXT,
                              status VARCHAR(50),
                              real_name VARCHAR(255),

                              user_id UUID UNIQUE,

                              CONSTRAINT fk_candidate_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(id)
                                      ON DELETE CASCADE
);