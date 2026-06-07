CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(255),
                       phone_number VARCHAR(50),
                       password VARCHAR(255),
                       role VARCHAR(50)
);