CREATE TABLE IF NOT EXISTS user_table (
    user_id UUID PRIMARY KEY NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(500) NOT NULL,
    user_role VARCHAR(10) NOT NULL
    );