CREATE TABLE skills
(
    id          TEXT      NOT NULL
        CONSTRAINT skills_id_pkey
            PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
    user_id     TEXT      NOT NULL
        CONSTRAINT skills_user_id_fkey
            REFERENCES users,
    name        TEXT      NOT NULL,
    description TEXT      NOT NULL
);
