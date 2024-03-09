CREATE TABLE users_roles
(
    user_id  TEXT NOT NULL
        CONSTRAINT users_roles_user_id_fkey
            REFERENCES users,
    roles_id TEXT NOT NULL
        CONSTRAINT users_roles_roles_id_fkey
            REFERENCES roles,
    CONSTRAINT users_roles_pkey
        PRIMARY KEY (user_id, roles_id)
);
