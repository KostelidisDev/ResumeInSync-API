CREATE TABLE roles
(
    id         TEXT      NOT NULL
        CONSTRAINT roles_id_pkey
            PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name       TEXT      NOT NULL
);
