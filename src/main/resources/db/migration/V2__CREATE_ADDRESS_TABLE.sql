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