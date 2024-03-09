CREATE TABLE languages
(
    id          TEXT      NOT NULL
        CONSTRAINT languages_id_pkey
            PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
    user_id     TEXT      NOT NULL
        CONSTRAINT languages_user_id_fkey
            REFERENCES users,
    name        TEXT      NOT NULL,
    proficiency INTEGER   NOT NULL
);
