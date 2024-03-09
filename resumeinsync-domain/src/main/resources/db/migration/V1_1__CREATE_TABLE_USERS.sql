CREATE TABLE users
(
    id                      TEXT      NOT NULL
        CONSTRAINT users_id_pkey
            PRIMARY KEY,
    created_at              TIMESTAMP NOT NULL,
    updated_at              TIMESTAMP NOT NULL,
    username                TEXT      NOT NULL
        CONSTRAINT users_username_key
            UNIQUE,
    email                   TEXT      NOT NULL
        CONSTRAINT users_email_key
            UNIQUE,
    password                TEXT      NOT NULL,
    enabled                 BOOLEAN   NOT NULL,
    account_non_locked      BOOLEAN   NOT NULL,
    account_non_expired     BOOLEAN   NOT NULL,
    credentials_non_expired BOOLEAN   NOT NULL
);
