INSERT INTO car(car_id, brand, model, year, price, category, seats, doors, automaticGearbox, automaticAC, imagesource) VALUES
(DEFAULT, 'Opel','Adam', 2019, 15.00, 'Economic', 5,3, FALSE, FALSE, NULL),
(DEFAULT, 'Volkswagen', 'UP!', 2018, 15.00, 'Economic', 5, 5, FALSE, FALSE, NULL),
(DEFAULT, 'Fiat', '500', 2018, 17.00, 'Economic', 4,3, FALSE, TRUE, NULL),
(DEFAULT, 'Citroën','DS3', 2016, 17.00, 'Economic', 5, 3, TRUE, FALSE, NULL),
(DEFAULT, 'Ford', 'Fiesta', 2018, 17.00, 'Economic', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Renault', 'Clio', 2018, 17.00, 'Economic', 5,3, FALSE, TRUE, NULL),
(DEFAULT, 'Volkswagen', 'Polo', 2016, 13.00, 'Economic', 5, 5, FALSE, FALSE, NULL),
(DEFAULT, 'Volkswagen', 'Golf', 2016, 17.00, 'Compact', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Alfa Romeo', 'Gulietta', 2016, 18.00, 'Compact', 5, 5, FALSE, FALSE, NULL),
(DEFAULT, 'Audi', 'A3 Sportback', 2018, 17.00, 'Compact', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Seat', 'Toledo', 2016, 17.00, 'Compact', 5, 5, FALSE, FALSE, NULL),
(DEFAULT, 'Fiat', 'Doblo', 2017, 19.00, 'Minivan', 5, 5, FALSE, FALSE, NULL),
(DEFAULT, 'Citroën', 'Berlingo', 2014, 20.00, 'Minivan', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Mercedes Benz', 'Vito', 2015, 23.00, 'Minivan', 9, 5, FALSE, FALSE, NULL),
(DEFAULT, 'Seat', 'Leon', 2013, 21.00, 'Family', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Ford', 'Focus', 2015, 20.00, 'Family', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Ford', 'C-Max', 2015, 20.00, 'Family', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Renault', 'Grand Scenic', 2015, 20.00, 'Family', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Nissan', 'Juke', 2017, 19.00, 'SUV', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Land Rover', 'Range Rover Evoque', 2013, 18.00, 'SUV', 5, 5, FALSE, TRUE, NULL),
(DEFAULT, 'Volkswagen', 'Touareg', 2018, 25.00, 'SUV', 5, 5, TRUE, TRUE, NULL);


INSERT INTO customer (customer_id, first_name, last_name, email, phone, country, city, zipcode, address) VALUES
(DEFAULT, 'Aegon II', 'Targaryen', 'jonsnuuh@iknownothi.ng', '33443344', 'Seven Kingdoms', 'Kings Landing', '00-001', 'Red Keep 1/1'),
(DEFAULT, 'John', 'Doe', 'john@fakemail.com', '1234567890', 'Nevercountry', 'Nevercity', '99-9999', 'Nowhere 0/0'),
(DEFAULT, 'Tommy', 'Collins', 'youhavenopants@hoax.com', '00112233', 'United States of America', 'New York', 'NY 10021', 'East 64th Street'),
(DEFAULT, 'Tommy', 'Vercetti', 'gogetsomesleep@vice.com', '01399323', 'United States of America', 'Vice City', 'GVC0323', '32 Kaufman Street'),
(DEFAULT, 'Vespa', 'Vespula', 'yellowjacket@buzz.com', '9999999', 'Nestovia', 'Nestville', 'NV3118', ' 14 Bees Blvd.');

INSERT INTO reservation (car_id, customer_id, reservation_start, reservation_end) VALUES
(9, 2, to_date('2019-05-20', 'YYYY-MM-DD'), to_date('2019-05-27', 'YYYY-MM-DD')),
(1, 3, to_date('2019-05-09', 'YYYY-MM-DD'), to_date('2019-06-23', 'YYYY-MM-DD')),
(7, 1, to_date('2019-06-01', 'YYYY-MM-DD'), to_date('2019-06-30', 'YYYY-MM-DD'));

UPDATE car SET imagesource ='https://imagemanager.carusseldwt.com/gmdrive/2019A/0UU08__GA11/69f595a2-1758-4ce8-a232-e55275b04f91_large.png' WHERE car_id = 1;
UPDATE car SET imagesource ='https://www.vw.co.za/content/dam/vw-ngw/vw_pkw/importers/za/Showrooms/up!/MODELS-SIZE-9NOV_up-beats-1.png/_jcr_content/renditions/original.transform/med/img.png' WHERE car_id = 2;
UPDATE car SET imagesource ='https://c4d709dd302a2586107d-f8305d22c3db1fdd6f8607b49e47a10c.ssl.cf1.rackcdn.com/thumbnails/stock-images/f85f4ec49a782a18267b7a530afa5697.png' WHERE car_id = 3;
UPDATE car SET imagesource ='https://media.hatla2eestatic.com/uploads/ncarteraz/204/big-up_33f0b69fcff5e6128df6c9d83af48883.jpg' WHERE car_id = 4;
UPDATE car SET imagesource ='https://www.fordeumedia-c.ford.com/nas/gforcenaslive/pol/00m/yym/images/pol00myymjd3jex(a)(a)jke_21_0.png' WHERE car_id = 5;
UPDATE car SET imagesource ='https://amcdn.co.za/cars/renault-clio-66kw-turbo-authentique-2019-id-56528135-type-main.jpg' WHERE car_id = 6;
UPDATE car SET imagesource ='https://car-pictures.cars.com/images/?IMG=CAC50VWC022C121001.jpg' WHERE car_id = 7;
UPDATE car SET imagesource ='https://image3.mouthshut.com/images/imagesp/925775472s.jpg' WHERE car_id = 8;
UPDATE car SET imagesource = 'https://autozagrosze.pl/wp-content/uploads/2018/10/alfa-romeo-giulietta-1229x718.jpg' WHERE car_id = 9;
UPDATE car SET imagesource = 'https://m.media-amazon.com/images/I/81iJ6FRcAML.jpg' WHERE car_id = 10;
UPDATE car SET imagesource = 'https://autorohoze.eu/resize/main/seat-toledo-13483.jpg' WHERE car_id = 11;
UPDATE car SET imagesource = 'https://immagini.alvolante.it/sites/default/files/styles/anteprima_lunghezza_640_jpg/public/serie_auto_galleria/2010/02/fiat_doblo_ant_0.png'WHERE car_id = 12;
UPDATE car SET imagesource = 'https://i.pinimg.com/736x/19/c5/ae/19c5ae741ad43fe13f2cd43400484cfa.jpg' WHERE car_id = 13;
UPDATE car SET imagesource = 'https://www.mercedes-benz.pl/vans/pl/vito/tourer-commercial/model-carousel/model/vito-line-sport/_jcr_content/parsysmeta/modelcarouselmeta/image.mq6.png/1552551949000.png' WHERE car_id = 14;
UPDATE car SET imagesource = 'https://www.seat-auto.pl/content/countries/pl/website/pl/samochody/nowy-leon-st/specyfikacja/style/_jcr_content/image.resizeViewPort.1200.jpg'WHERE car_id = 15;
UPDATE car SET imagesource = 'https://cloudflarestockimages.dealereprocess.com/resrc/images/stockphoto_asset-c_limit,f_auto,fl_lossy,w_700/v1/svp/Colors_PNG1280/2015/15ford/15fordfocussesa4a/ford_15focussesa4a_angularfront_oxfordwhite' WHERE car_id = 16;
UPDATE car SET imagesource = 'https://www.fordeumedia-c.ford.com/nas/gforcenaslive/pol/ceu04/yyp/images/resize767xpolceu04yypbs-uavs-diacmaa(a)(a)pn3jv_21_0.png' WHERE car_id = 17;
UPDATE car SET imagesource = 'https://s1.cdn.autoevolution.com/images/gallery/RENAULT-Scenic-5464_16.jpg' WHERE car_id = 18;
UPDATE car SET imagesource = 'https://leasecar.uk/images/main/cars/1/3/22231/xnissan_juke_hatchback_1_6__112__visia_5dr_2018_front_three_quarter.jpeg.pagespeed.ic.IED1xgd41e.jpg' WHERE car_id = 19;
UPDATE car SET imagesource = 'https://m.media-amazon.com/images/I/71D30ezQOsL._UY560_.jpg' WHERE car_id = 20;
UPDATE car SET imagesource = 'https://d1ek71enupal89.cloudfront.net/images/blocks_png/VOLKSWAGEN/TOUAREG/5DR/18VwTouSel5drRedFL1_800.jpg' WHERE car_id = 21;