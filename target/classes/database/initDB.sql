CREATE TABLE IF NOT EXISTS CLIENTS
(
    id SERIAL PRIMARY KEY ,
    full_name VARCHAR(255) NOT NULL ,
    phone VARCHAR(255) NOT NULL ,
    birthday DATE ,
    message_send BOOLEAN

);
