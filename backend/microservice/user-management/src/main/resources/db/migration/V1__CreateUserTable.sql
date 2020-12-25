CREATE TABLE IF NOT EXISTS user_table (
    user_uid UUID PRIMARY KEY NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    user_role VARCHAR(10) NOT NULL,
    first_name VARCHAR(250),
    last_name VARCHAR(250),
    email VARCHAR(250)
    );