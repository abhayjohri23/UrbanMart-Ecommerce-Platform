CREATE TABLE category_entity
(
    entity_id     BIGINT NOT NULL,
    category_name VARCHAR(255) NULL,
    CONSTRAINT pk_categoryentity PRIMARY KEY (entity_id)
);

CREATE TABLE products
(
    entity_id     BIGINT NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NULL,
    imageurl      VARCHAR(255) NULL,
    category_ref  BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (entity_id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY_REF FOREIGN KEY (category_ref) REFERENCES category_entity (entity_id);