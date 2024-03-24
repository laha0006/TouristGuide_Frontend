CREATE TABLE city (
    city_id INT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (city_id)
);

CREATE TABLE attraction (
    attraction_id INT AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(500) NOT NULL,
    city_id INT NOT NULL,
    PRIMARY KEY (attraction_id),
    FOREIGN KEY (city_id) REFERENCES city(city_id)
);

CREATE TABLE tag (
    tag_id INT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (tag_id)
);

CREATE TABLE attraction_tags (
    attraction_id INT NOT NULL,
    tag_id INT NOT NULL,
    FOREIGN KEY (attraction_id) REFERENCES attraction(attraction_id),
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
);

INSERT INTO tag (name) VALUES('Museum');
INSERT INTO tag (name) VALUES('Free');
INSERT INTO tag (name) VALUES('Paid');
INSERT INTO tag (name) VALUES('Family Friendly');
INSERT INTO tag (name) VALUES('Historical');
INSERT INTO tag (name) VALUES('Outdoor');
INSERT INTO tag (name) VALUES('Religion');
INSERT INTO tag (name) VALUES('Amusement Park');
INSERT INTO tag (name) VALUES('Fine Dining');
INSERT INTO tag (name) VALUES('Nature');
INSERT INTO tag (name) VALUES('Active');
INSERT INTO tag (name) VALUES('Food');


INSERT INTO city (name) VALUES('København');
INSERT INTO city (name) VALUES('Århus');
INSERT INTO city (name) VALUES('Odense');
INSERT INTO city (name) VALUES('Vejle');
INSERT INTO city (name) VALUES('Henne');
INSERT INTO city (name) VALUES('Lønstrup');
INSERT INTO city (name) VALUES('Hørve');
INSERT INTO city (name) VALUES('Nykøbing Sjælland');
INSERT INTO city (name) VALUES('Åkirkeby');
INSERT INTO city (name) VALUES('Sønderborg');
INSERT INTO city (name) VALUES('Præstø');
INSERT INTO city (name) VALUES('Herning');
INSERT INTO city (name) VALUES('Agger');
INSERT INTO city (name) VALUES('Fredericia');

INSERT INTO attraction (name,description,city_id) VALUES('Odense Zoo','Zooooo',3);
INSERT INTO attraction (name,description,city_id) VALUES('Den Lille Fede','Zooooo',3);
INSERT INTO attraction (name,description,city_id) VALUES('Osteria NANA','Zooooo',3);
INSERT INTO attraction (name,description,city_id) VALUES('FIAT','Zooooo',3);
INSERT INTO attraction (name,description,city_id) VALUES('No.2','Foooood',2);
INSERT INTO attraction (name,description,city_id) VALUES('No.3','Throooood',5);