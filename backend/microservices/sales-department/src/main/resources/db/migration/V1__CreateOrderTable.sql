CREATE TABLE IF NOT EXISTS orders (
    order_id UUID PRIMARY KEY NOT NULL,
    customer_id UUID NOT NULL,
    bike_id UUID NOT NULL,
    total_price FLOAT NOT NULL,
    order_date DATE NOT NULL
);