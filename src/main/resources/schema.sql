
DROP TABLE IF EXISTS medication;
CREATE TABLE medication (
        id bigint AUTO_INCREMENT  PRIMARY KEY,
        name VARCHAR (255) NOT NULL,
        code VARCHAR (50) NOT NULL UNIQUE,
        weight decimal(5,2) not null,
        image VARCHAR (255) null,
        status VARCHAR (20) NOT NULL
);


DROP TABLE IF EXISTS drone_model;
CREATE TABLE drone_model (
        id bigint AUTO_INCREMENT  PRIMARY KEY,
        serial_number VARCHAR (100) NOT NULL UNIQUE,
        model VARCHAR (50) NOT NULL,
        weight decimal(5,2) not null,
        available_weight decimal(5,2) not null,
        battery_capacity decimal(5,2) not null,
        state VARCHAR (50) null
);

DROP TABLE IF EXISTS drone_load;
CREATE TABLE drone_load (
        id bigint AUTO_INCREMENT  PRIMARY KEY,
        drone_id VARCHAR (100) NOT NULL,
        medication_id VARCHAR (50) NOT NULL,
        medication_weight decimal(5,2) not null,
        status VARCHAR (50) null
);



