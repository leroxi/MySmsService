CREATE TABLE IF NOT EXISTS Clients
(
    id SERIAL PRIMARY KEY ,
    FullName VARCHAR(255) NOT NULL ,
    phone VARCHAR(255) NOT NULL ,
    Birthday DATE ,
    messageSend BOOLEAN

);