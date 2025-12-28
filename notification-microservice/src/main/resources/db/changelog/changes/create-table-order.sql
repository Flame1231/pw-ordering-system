CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        order_id BIGINT,
                        product_id BIGINT,
                        quantity INT,
                        price NUMERIC(19, 2) NOT NULL,
                        sale NUMERIC(19, 2) NOT NULL,
                        total_price NUMERIC(19, 2) NOT NULL,
                        user_id BIGINT
);