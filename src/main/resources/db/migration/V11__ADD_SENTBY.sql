
ALTER TABLE ratings
    ADD COLUMN send_by_id UUID;

ALTER TABLE ratings
    ADD CONSTRAINT fk_rating_sent_by
        FOREIGN KEY (send_by_id)
            REFERENCES users(id)
            ON DELETE SET NULL;