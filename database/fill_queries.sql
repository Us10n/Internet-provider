USE internetprovider;

# Users ########
INSERT INTO internetprovider.users (roles_id, login, password, email, first_name, last_name, status)
VALUES ('1','admin','admin', 'cron96@list.ru', 'Stas', 'Rabinin', 'Verified');
INSERT INTO internetprovider.users (roles_id, login, password, email, first_name, last_name, status)
VALUES ('2','user','user', 'cron96@list.ru', 'Петр', 'Петров', 'Verified');
INSERT INTO internetprovider.users (roles_id, login, password, email, first_name, last_name, status)
VALUES ('3','user','user', 'cron96@list.ru', 'Петр', 'Петров', 'Verified');

# Special Offers #######
INSERT INTO internetprovider.specialoffers (title, description, start_date, expiration_date, discount)
VALUES ('New Year & Christmas', 'Holidays are coming. Have time to get good internet tariff with a 20% discount on all tariffs.', '2021-12-01','2022-01-31', '40','offers/winter.png');
INSERT INTO internetprovider.specialoffers (title, description, start_date, expiration_date, discount)
VALUES ('Winter Ends', 'Winter is ending thus it`s time to get spring discounts on "Spring" tariffs.', '2022-02-20','2022-03-31', '15','offers/spring.png');

# Tariff #######
INSERT INTO internetprovider.tariffs (special_offers_id, name, description, status, internet_speed, rating, image_url)
VALUES ('1','Start','Provides stable internet connection with speed up to 10 Mbps', 'Active', '10', '0', 'tariff/start.png');
INSERT INTO internetprovider.tariffs (name, description, status, internet_speed, rating, image_url)
VALUES ('Medium','Provides stable internet connection with speed up to 50 Mbps', 'Active', '50', '0', 'tariff/medium.png');
INSERT INTO internetprovider.tariffs (name, description, status, internet_speed, rating, image_url)
VALUES ('Super','Provides stable internet connection with speed up to 100 Mbps', 'Active', '100', '0', 'tariff/super.png');
INSERT INTO internetprovider.tariffs (name, description, status, internet_speed, rating, image_url)
VALUES ('Home Light','Provides stable internet connection with speed up to 30 Mbps', 'Archive', '30', '0', 'tariff/home_light.png');
INSERT INTO internetprovider.tariffs (name, description, status, internet_speed, rating, image_url)
VALUES ('Light','Provides stable internet connection with speed up to 30 Mbps', 'Deactivated', '40', '0', 'tariff/light.png');

# Bank Account ######
INSERT INTO internetprovider.bankaccounts (users_id, balance)
VALUES ('1', '1','100');
INSERT INTO internetprovider.bankaccounts (users_id, balance)
VALUES ('2', '10');

# Feedback ######
INSERT INTO internetprovider.feedbacks (users_id, tariffs_id, rating, body)
VALUES ('2', '1', '9','Nice tariff with. Got with discount');




