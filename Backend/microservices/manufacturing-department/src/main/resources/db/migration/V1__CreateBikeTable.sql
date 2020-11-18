CREATE TABLE IF NOT EXISTS bike (
    bike_id UUID PRIMARY KEY NOT NULL,
    bike_name VARCHAR(250) NOT NULL,
    description TEXT NOT NULL,
    short_description TEXT NOT NULL,
    bike_type VARCHAR(12) NOT NULL,
    price FLOAT NOT NULL
);