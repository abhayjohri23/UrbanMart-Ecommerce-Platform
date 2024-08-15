CREATE TABLE price_entity
(
    uuid  BINARY(16) NOT NULL,
    price BIGINT NULL,
    CONSTRAINT pk_priceentity PRIMARY KEY (uuid)
);