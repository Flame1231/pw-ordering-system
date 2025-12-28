CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          quantity INTEGER NOT NULL,
                          price DECIMAL(19, 2) NOT NULL,
                          sale DECIMAL(19, 2) NOT NULL
);