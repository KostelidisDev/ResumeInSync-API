CREATE TABLE certifications
(
    id           TEXT      NOT NULL
        CONSTRAINT certifications_id_pkey
            PRIMARY KEY,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NOT NULL,
    user_id      TEXT      NOT NULL
        CONSTRAINT certifications_user_id_fkey
            REFERENCES users,
    name         TEXT      NOT NULL,
    organization TEXT      NOT NULL,
    can_expire   BOOLEAN   NOT NULL,
    start_date   TIMESTAMP NOT NULL,
    end_date     TIMESTAMP NULL,
    original_id  TEXT      NOT NULL,
    original_url TEXT      NOT NULL
);
