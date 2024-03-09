CREATE TABLE educations
(
    id          TEXT      NOT NULL
        CONSTRAINT educations_id_pkey
            PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
    user_id     TEXT      NOT NULL
        CONSTRAINT educations_user_id_fkey
            REFERENCES users,
    school      TEXT      NOT NULL,
    degree      TEXT      NOT NULL,
    field       TEXT      NOT NULL,
    start_date  TIMESTAMP NOT NULL,
    end_date    TIMESTAMP NULL,
    grade       TEXT      NOT NULL,
    description TEXT      NOT NULL
);
