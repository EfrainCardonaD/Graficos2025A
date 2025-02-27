CREATE TABLE medicos (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         email VARCHAR(100),
                         documento VARCHAR(16) not null unique ,
                         especialidad VARCHAR(100) not null ,
                         calle VARCHAR(100) not null ,
                         numero VARCHAR(100),
                         cruces VARCHAR(100) not null ,
                         ciudad VARCHAR(100) not null ,
                         colonia VARCHAR(100) not null ,
                         UNIQUE KEY unique_email (email)
);
