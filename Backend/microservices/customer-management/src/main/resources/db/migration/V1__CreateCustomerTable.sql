CREATE TABLE IF NOT EXISTS customer (
    customer_id UUID PRIMARY KEY NOT NULL,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    gender VARCHAR(6) NOT NULL,
    email VARCHAR(250) NOT NULL UNIQUE,
    street VARCHAR(250) NOT NULL,
    house_number VARCHAR(12) NOT NULL,
    zip_code INTEGER NOT NULL,
    city VARCHAR(250) NOT NULL
);
