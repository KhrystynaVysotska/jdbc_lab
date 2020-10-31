CREATE DATABASE IF NOT EXISTS vysotska;
USE vysotska;

DROP TABLE IF EXISTS bank_card;
DROP TABLE IF EXISTS card_type;
DROP TABLE IF EXISTS transfer;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS pin_code;
DROP TABLE IF EXISTS account_owner;
DROP TABLE IF EXISTS account_type;
DROP TABLE IF EXISTS adress;
DROP TABLE IF EXISTS building;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS street;
DROP TABLE IF EXISTS bank;
DROP TABLE IF EXISTS currency;

CREATE TABLE account (
id INT AUTO_INCREMENT,
pin_code_id INT,
current_account_number VARCHAR(20) NOT NULL UNIQUE,
amount INT NOT NULL,
account_owner_id INT NOT NULL,
bank_identification_code INT NOT NULL,
currency_id INT,
account_type_id INT,

CONSTRAINT PK_ACCOUNT_ID_PIN_CODE_ID
PRIMARY KEY (id, pin_code_id)
);

CREATE TABLE account_owner (
id INT AUTO_INCREMENT PRIMARY KEY,
personal_identification_number VARCHAR(10) UNIQUE,
name VARCHAR(45) NOT NULL,
surname VARCHAR(45) NOT NULL,
patronym VARCHAR(45) NOT NULL,
mobile_number VARCHAR(13) UNIQUE,
email VARCHAR(45) UNIQUE,
birth_date DATE NOT NULL,
adress_id INT
);

CREATE TABLE account_type (
id INT AUTO_INCREMENT PRIMARY KEY,
type VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE adress (
id INT AUTO_INCREMENT PRIMARY KEY,
city_id INT NOT NULL,
street_id INT,
building_id INT
);

CREATE TABLE bank (
identification_code INT PRIMARY KEY,
state_registration_code INT NOT NULL UNIQUE,
full_bank_name TINYTEXT NOT NULL,
short_bank_name VARCHAR(45) NOT NULL UNIQUE,
bank_license_number INT NOT NULL,
bank_license_date DATE NOT NULL
);

CREATE TABLE bank_card (
id INT PRIMARY KEY AUTO_INCREMENT,
account_id INT NOT NULL,
card_type_id INT,
cvc2 INT,
date_of_expire DATE NOT NULL
);

CREATE TABLE building (
id INT PRIMARY KEY AUTO_INCREMENT,
house_number VARCHAR(45) NOT NULL,
flat_number VARCHAR(45)
);

CREATE TABLE card_type (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE city (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL UNIQUE,
zip_code INT NOT NULL UNIQUE,
phone_code VARCHAR(45) NOT NULL
);

CREATE TABLE currency (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE pin_code (
id INT PRIMARY KEY AUTO_INCREMENT,
pin VARCHAR(45) NOT NULL
);

CREATE TABLE street (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE transfer (
id INT PRIMARY KEY AUTO_INCREMENT,
sender_account_id INT NOT NULL,
recipient_account_id INT NOT NULL,
amount INT NOT NULL,
currency_id INT,
date DATE NOT NULL,
time TIME NOT NULL,
purpose_of_payment TINYTEXT
);

ALTER TABLE account 
	ADD CONSTRAINT FK_pin_code_id
	FOREIGN KEY(pin_code_id)
	REFERENCES pin_code(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
	ADD CONSTRAINT FK_account_owner_id
	FOREIGN KEY(account_owner_id)
	REFERENCES account_owner(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT FK_bank_identification_code
    FOREIGN KEY(bank_identification_code)
    REFERENCES bank(identification_code)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT FK_currency_id
    FOREIGN KEY(currency_id)
    REFERENCES currency(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
    
    ADD CONSTRAINT FK_account_type_id
    FOREIGN KEY(account_type_id)
    REFERENCES account_type(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL;

ALTER TABLE account_owner
	ADD CONSTRAINT FK_adress_id
    FOREIGN KEY(adress_id)
    REFERENCES adress(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL;
    
ALTER TABLE adress
	ADD CONSTRAINT FK_city_id
    FOREIGN KEY(city_id)
    REFERENCES city(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT FK_street_id
    FOREIGN KEY(street_id)
    REFERENCES street(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
    
    ADD CONSTRAINT FK_building_id
    FOREIGN KEY(building_id)
    REFERENCES building(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL;
    
ALTER TABLE bank_card
    ADD CONSTRAINT FK_account_id
    FOREIGN KEY(account_id)
    REFERENCES account(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT FK_card_type_id
    FOREIGN KEY(card_type_id)
    REFERENCES card_type(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL;
    
ALTER TABLE transfer
    ADD CONSTRAINT FK_sender_account_id
    FOREIGN KEY(sender_account_id)
    REFERENCES account(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT FK_recipient_account_id
    FOREIGN KEY(recipient_account_id)
    REFERENCES account(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT FK_transfer_currency_id
    FOREIGN KEY(currency_id)
    REFERENCES currency(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL;

INSERT INTO currency(name) VALUES
('BGN'),
('CNY'),
('CZK'),
('EUR'),
('GBP'),
('PLN'),
('RUB'),
('SEK'),
('UAH'),
('USD');

INSERT INTO bank (identification_code, state_registration_code, full_bank_name, short_bank_name, bank_license_number, bank_license_date) VALUES
(300335, 14305909, 'JOINT-STOCK COMPANY RAIFFEISEN BANK AVAL', 'JSC `Raiffeisen Bank Aval`', 10, '2018-06-18'),
(300346, 23494714, 'JOINT-STOCK COMPANY ALFA-BANK', 'JSC `ALFA-BANK`', 61, '2011-10-05'),
(300465, 32129, 'JOINT-STOCK COMPANY STATE SAVINGS BANK OF UKRAINE', 'JSC `Oschadbank`', 148, '2011-10-05'),
(305299, 14360570, 'JOINT-STOCK COMPANY COMMERCIAL BANK PRIVATBANK', 'JSC CB `PrivatBank`', 22, '2011-10-05'),
(307770, 14380060, 'JOINT-STOCK COMPANY ACCENT-BANK', 'JSC `A-BANK`', 16, '2011-10-26'),
(320940, 19358784, 'JOINT-STOCK COMPANY ALTBANK', 'JSC `ALTBANK`', 106, '2018-11-19'),
(322302, 21570492, 'PRIVATE JOINT-STOCK COMPANY IBOX BANK', 'JSC `IBOX BANK`', 53, '2017-07-11'),
(325365, 9807862, 'JOINT-STOCK COMPANY KREDOBANK', 'JSC `KREDOBANK`', 43, '2011-10-11'),
(334840, 20042839, 'PRIVATE JOINT-STOCK COMPANY BANK FAMILY', 'PJSC `BANK FAMILY`', 145, '2011-11-14'),
(353489, 9809192, 'JOINT-STOCK COMPANY ASVIO BANK', 'JSC `ASVIO BANK`', 133, '2011-11-16');

INSERT INTO street(name) VALUES
('Baldwin Haven'),
('Cromer Gardens'),
('Furze Paddocks'),
('Hare Quay'),
('Moss Shaw Way'),
('Old Mill Retreat'),
('Pollard Common'),
('Romer Side'),
('Selborne Road'),
('St Peter`s Square');

INSERT INTO city(name, zip_code, phone_code) VALUES
('Lviv', 79007, 380),
('Sambir', 81400, 380),
('Chervonograd', 80100, 380),
('Kyiv', 02132, 380),
('Vinnutsia', 21000, 380),
('Ternopil', 46001, 380),
('Uzhgorod', 88000, 380),
('Chernivtsi', 58000, 380),
('Chernihiv', 14039, 380),
('Drogobych', 82100, 380);

INSERT INTO building(house_number, flat_number) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(4, 7),
(3, 4),
(3, 6),
(2, 1),
(1, 9);

INSERT INTO adress(city_id, street_id, building_id) VALUES
(1, 2, 3),
(4, 6, 9),
(2, 4, 10),
(8, 2, 1),
(2, 7, 5),
(1, 9, 2),
(5, 2, 6),
(1, 2, 4),
(4, 3, 10),
(2, 3, 1);

INSERT INTO account_type (type) VALUES
('accumulative pension account'),
('bond account'),
('correspondent account'),
('current account'),
('deposit account'),
('exchange account'),
('fixed assets account'),
('frozen account'),
('savings account'),
('transit account');


INSERT INTO account_owner (personal_identification_number, name, surname, patronym, mobile_number, email, birth_date, adress_id) VALUES
(5525018372, 'Alexandr', 'Frolov', 'Petrovych', '407-442-0779', 'AlexandrFrolov@armyspy.com', '1996-09-24', 10),
(5525018373, 'Flora', 'Pokrovskaya', 'Andriivna', '856-946-2513', 'FloraPokrovskaya@jourrapide.com', '1979-03-10', 9),
(5525018374, 'Boris', 'Uspensky', 'Bogdanovych', '724-452-4889', 'BorisUspensky@rhyto.com', '1996-06-05', 8),
(5525018375, 'Nelly', 'Zubareva', 'Volodymyrivna', '812-793-1885', 'NellyZubareva@teleworm.us', '1991-09-25', 6),
(5525018376, 'Mark', 'Yevdokimov', 'Arsenovych', '906-672-4074', 'MarkYevdokimov@teleworm.us', '1940-02-08', 7),
(5525018377, 'Herman', 'Polyakov', 'Denusovych', '206-441-8900', 'HermanPolyakov@rhyta.com', '1965-09-25', 3),
(5525018378, 'Basil', 'Yefremov', 'Sergiyovych', '240-936-2591', 'BasilYefremov@jourrapide.com', '1989-11-03', 4),
(5525018379, 'Ian', 'Blokhin', 'Pavlovych', '503-672-0178', 'IanBlokhin@jourrapide.com', '1941-06-18', 5),
(5525018382, 'Stepan', 'Yefremov', 'Maksymovych', '616-486-0327', 'StepanYefremov@jourrapide.com', '1992-11-26', 2),
(5525018392, 'Maria', 'Gorbunova', 'Oleksiivna', '646-404-4748', 'MariaGorbunova@jourrapide.com', '1977-02-09', 1);

INSERT INTO pin_code(pin) VALUES
('jie9hahTu'),
('weywe7f'),
('kwfe83j'),
('2o32u3892'),
('m2of2i90'),
('m2epwkf3'),
('ms0322k1l'),
('vnsdwlkw82'),
('mxa920f2235'),
('mkjkdkwl8m');

INSERT INTO account (pin_code_id, current_account_number, amount, account_owner_id, bank_identification_code, currency_id, account_type_id) VALUES
(2, 4929343705927162, 1300, 5, 300335, 2, 2),
(5, 5297487429928344, 250, 8, 300346, 4, 1),
(3, 4532642238973372, 8400, 3, 300465, 5, 8),
(9, 5450118231578893, 690, 7, 305299, 1, 5),
(8, 4539856502604405, 1500, 4, 307770, 6, 4),
(6, 5164204310976260, 1890, 1, 320940, 3, 3),
(7, 4949848715929163, 217, 6, 322302, 2, 5),
(1, 4929343705927164, 150, 10, 325365, 4, 9),
(4, 4929343705927165, 699, 7, 334840, 6, 2),
(10, 5929343705927166, 90, 2, 353489, 1, 1);

INSERT INTO transfer (sender_account_id, recipient_account_id, amount, currency_id, date, time) VALUES
(1, 4, 400, 1, '2020-03-09', '08:30:00'),
(2, 9, 200, 5, '2020-08-12', '08:35:00'),
(4, 7, 50, 4, '2020-05-24', '09:10:00'),
(3, 6, 150, 2, '2020-12-09', '09:15:00'),
(5, 9, 20, 2, '2020-01-03', '10:20:00'),
(7, 4, 33, 3, '2020-06-11', '11:00:00'),
(1, 3, 890, 7, '2020-06-11', '12:30:20'),
(6, 10, 450, 9, '2020-06-12', '13:40:10'),
(2, 1, 36, 3, '2020-06-13', '13:41:00'),
(8, 2, 40, 5, '2020-06-14', '13:42:00');

INSERT INTO card_type (name) VALUES
('american express'),
('gold'),
('mastercard'),
('mastercard maestro'),
('mastercard standart'),
('mastercard world platinum'),
('visa'),
('visa classic'),
('visa electron'),
('visa platinum');

INSERT INTO bank_card (account_id, card_type_id, cvc2, date_of_expire) VALUES
(1, 2, null, '2023-03-01'),
(4, 1, 760, '2023-07-01'),
(8, 3, 560, '2022-05-01'),
(5, 2, 670, '2021-07-01'),
(7, 5, 819, '2023-09-01'),
(9, 1, 678, '2022-04-01'),
(4, 3, 560, '2023-07-02'),
(10, 8, 234, '2022-10-04'),
(2, 4, 123, '2021-11-07'),
(3, 7, 324, '2025-08-01');

CREATE INDEX date ON transfer(date);
CREATE INDEX time ON transfer(time);
