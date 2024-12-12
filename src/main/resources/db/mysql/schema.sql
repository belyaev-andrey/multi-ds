DROP TABLE IF EXISTS vets;
CREATE TABLE vets
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    speciality VARCHAR(255),
    PRIMARY KEY (id)
);