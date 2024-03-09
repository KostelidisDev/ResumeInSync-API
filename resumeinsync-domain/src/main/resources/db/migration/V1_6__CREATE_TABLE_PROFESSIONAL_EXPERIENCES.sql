CREATE TABLE professional_experiences
(
    id          TEXT      NOT NULL
        CONSTRAINT work_experiences_id_pkey
            PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
    user_id     TEXT      NOT NULL
        CONSTRAINT work_experiences_user_id_fkey
            REFERENCES users,
    location    TEXT      NOT NULL,
    currently   BOOLEAN   NOT NULL,
    start_date  TIMESTAMP NOT NULL,
    end_date    TIMESTAMP NULL,
    description TEXT      NOT NULL,
    title       TEXT      NOT NULL,
    company     TEXT      NOT NULL
);
