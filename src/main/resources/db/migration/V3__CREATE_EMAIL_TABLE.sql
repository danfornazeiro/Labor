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