INSERT INTO car(car_id, brand, model, year, price, category, seats, doors, automaticGearbox, automaticAC) VALUES
(DEFAULT, 'Opel','Adam', 2019, 15.00, 'Economic', 5,5, FALSE, FALSE),
(DEFAULT, 'Volkswagen', 'UP!', 2018, 15.00, 'Economic', 5, 5, FALSE, FALSE),
(DEFAULT, 'Fiat', '500', 2018, 17.00, 'Economic', 4,3, FALSE, TRUE),
(DEFAULT, 'Citroën','DS3', 2016, 17.00, 'Economic', 5, 3, TRUE, FALSE),
(DEFAULT, 'Ford', 'Fiesta', 2018, 17.00, 'Economic', 5, 5, FALSE, TRUE),
(DEFAULT, 'Renault', 'Clio', 2018, 17.00, 'Economic', 5,5, FALSE, TRUE),
(DEFAULT, 'Volkswagen', 'Polo', 2016, 13.00, 'Economic', 5, 5, FALSE, FALSE),
(DEFAULT, 'Volkswagen', 'Golf', 2016, 17.00, 'Compact', 5, 5, FALSE, TRUE),
(DEFAULT, 'Alfa Romeo', 'Gulietta', 2016, 18.00, 'Compact', 5, 5, FALSE, FALSE),
(DEFAULT, 'Audi', 'A3 Sportback', 2018, 17.00, 'Compact', 5, 5, FALSE, TRUE),
(DEFAULT, 'Seat', 'Toledo', 2016, 17.00, 'Compact', 5, 5, FALSE, FALSE),
(DEFAULT, 'Fiat', 'Doblo', 2017, 19.00, 'Minivan', 5, 5, FALSE, FALSE),
(DEFAULT, 'Citroën', 'Berlingo', 2014, 20.00, 'Minivan', 5, 5, FALSE, TRUE),
(DEFAULT, 'Mercedes Benz', 'Vito', 2015, 23.00, 'Minivan', 9, 5, FALSE, FALSE),
(DEFAULT, 'Seat', 'Leon', 2013, 21.00, 'Family', 5, 5, FALSE, TRUE),
(DEFAULT, 'Ford', 'Focus', 2015, 20.00, 'Family', 5, 5, FALSE, TRUE),
(DEFAULT, 'Ford', 'C-Max', 2015, 20.00, 'Family', 5, 5, FALSE, TRUE),
(DEFAULT, 'Renault', 'Scenic', 2015, 20.00, 'Family', 5, 5, FALSE, TRUE),
(DEFAULT, 'Nissan', 'Juke', 2017, 19.00, 'SUV', 5, 5, FALSE, TRUE),
(DEFAULT, 'Land Rover', 'Range Rover Evoque', 2013, 18.00, 'SUV', 5, 5, FALSE, TRUE),
(DEFAULT, 'Volkswagen', 'Touareg', 2018, 25.00, 'SUV', 5, 5, TRUE, TRUE);


INSERT INTO customer (customer_id, first_name, last_name, email, phone, country, city, zipcode, address) VALUES
(DEFAULT, 'Aegon II', 'Targaryen', 'jonsnuuh@iknownothi.ng', '33443344', 'Seven Kingdoms', 'Kings Landing', '00-001', 'Red Keep 1/1'),
(DEFAULT, 'John', 'Doe', 'john@fakemail.com', '1234567890', 'Nevercountry', 'Nevercity', '99-9999', 'Nowhere 0/0'),
(DEFAULT, 'Tommy', 'Collins', 'youhavenopants@hoax.com', '00112233', 'United States of America', 'New York', 'NY 10021', 'East 64th Street');