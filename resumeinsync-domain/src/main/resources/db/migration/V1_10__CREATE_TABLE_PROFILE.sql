CREATE TABLE profiles
(
    id           TEXT      NOT NULL
        CONSTRAINT profiles_id_pkey
            PRIMARY KEY,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NOT NULL,
    user_id      TEXT      NOT NULL
        CONSTRAINT profiles_user_id_fkey
            REFERENCES users,
    first_name   TEXT      NOT NULL,
    last_name    TEXT      NOT NULL,
    mobile_phone TEXT      NOT NULL,
    land_phone   TEXT      NOT NULL,
    fax          TEXT      NOT NULL,
    published    BOOLEAN   NOT NULL
);