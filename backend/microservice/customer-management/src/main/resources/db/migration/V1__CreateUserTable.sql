CREATE TABLE IF NOT EXISTS user_table (
    user_uid UUID PRIMARY KEY NOT NULL,
    username VARCHAR(250) NOT NULL,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL
    );