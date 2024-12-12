DROP TABLE IF EXISTS owners;

CREATE SEQUENCE IF NOT EXISTS owners_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE owners
(
  id           BIGINT       NOT NULL,
  name         VARCHAR(100) NOT NULL,
  phone_number VARCHAR(15)  NOT NULL,
  address      VARCHAR(255),
  CONSTRAINT pk_owners PRIMARY KEY (id)
);
