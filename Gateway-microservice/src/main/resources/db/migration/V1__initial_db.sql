CREATE table user_model (
    id BIGSERIAL primary key,
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255),
    active BOOLEAN
)