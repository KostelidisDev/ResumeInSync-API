CREATE TABLE publications
(
    id               TEXT      NOT NULL
        CONSTRAINT publications_id_pkey
            PRIMARY KEY,
    created_at       TIMESTAMP NOT NULL,
    updated_at       TIMESTAMP NOT NULL,
    user_id          TEXT      NOT NULL
        CONSTRAINT publications_user_id_fkey
            REFERENCES users,
    title            TEXT      NOT NULL,
    publisher        TEXT      NOT NULL,
    authors          TEXT      NOT NULL,
    publication_date TIMESTAMP NOT NULL,
    url              TEXT      NOT NULL,
    description      TEXT      NOT NULL
);
