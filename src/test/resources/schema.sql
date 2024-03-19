CREATE SCHEMA IF NOT EXISTS tourist_guide;
USE tourist_guide;

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
)