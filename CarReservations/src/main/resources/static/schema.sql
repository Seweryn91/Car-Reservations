CREATE TABLE car (car_id SERIAL PRIMARY KEY, brand VARCHAR(64) NOT NULL, model VARCHAR(64) NOT NULL, year INTEGER, price FLOAT8 NOT NULL, category VARCHAR(64), seats INTEGER, doors INTEGER, automaticGearbox BOOLEAN, automaticAC BOOLEAN);

CREATE TABLE reservation (reservation_id SERIAL PRIMARY KEY, car_id INTEGER NOT NULL, customer_id INTEGER NOT NULL, reservation_start DATE, reservation_end DATE);

CREATE TABLE customer (customer_id SERIAL PRIMARY KEY, first_name VARCHAR(64) NOT NULL, last_name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, phone VARCHAR(64), country VARCHAR(64) NOT NULL, city VARCHAR(64) NOT NULL, zipcode varchar(10) NOT NULL, address VARCHAR(99) NOT NULL);

ALTER TABLE reservation ADD CONSTRAINT car_id FOREIGN KEY (car_id) REFERENCES car(car_id);

ALTER TABLE reservation ADD CONSTRAINT customer_id FOREIGN KEY (customer_id) references customer(customer_id);