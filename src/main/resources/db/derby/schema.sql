CREATE TABLE stats
(
    id       BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    vet_id   BIGINT NOT NULL,
    owner_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
