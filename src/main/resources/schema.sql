
DROP TABLE IF EXISTS medication;
CREATE TABLE medication (
        id INT AUTO_INCREMENT  PRIMARY KEY,
        name VARCHAR (255) NOT NULL,
        code VARCHAR (50) NOT NULL UNIQUE,
        weight int(8) not null,
        image VARCHAR (255) null
);


DROP TABLE IF EXISTS drone_model;
CREATE TABLE drone_model (
        id INT AUTO_INCREMENT  PRIMARY KEY,
        serial_number VARCHAR (100) NOT NULL UNIQUE,
        model VARCHAR (50) NOT NULL,
        weight int(8) not null,
        battery_capacity decimal(5,2) not null,
        state VARCHAR (50) null
);



