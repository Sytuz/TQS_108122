CREATE TABLE tqs_car (
    carId BIGSERIAL PRIMARY KEY,
    maker VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL
);

INSERT INTO tqs_car (maker, model) VALUES ('Ford', 'Focus');
INSERT INTO tqs_car (maker, model) VALUES ('Ford', 'Fiesta');
INSERT INTO tqs_car (maker, model) VALUES ('Ford', 'Mustang');
INSERT INTO tqs_car (maker, model) VALUES ('Toyota', 'Corolla');
INSERT INTO tqs_car (maker, model) VALUES ('Toyota', 'Yaris');
INSERT INTO tqs_car (maker, model) VALUES ('Toyota', 'Supra');
