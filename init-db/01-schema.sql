-- ------------------------------------------------------------
-- Tabelas independentes
-- ------------------------------------------------------------

CREATE TABLE contact_type (
    id   SERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE payment_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- ------------------------------------------------------------
-- users
-- ------------------------------------------------------------

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULl,
    email VARCHAR(320) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role INT DEFAULT 1,
    creation_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deactivated_at TIMESTAMP
);

-- ------------------------------------------------------------
-- contact
-- ------------------------------------------------------------

CREATE TABLE contact (
    id              SERIAL PRIMARY KEY,
    value           VARCHAR(255) NOT NULL,
    id_user     INT NOT NULL REFERENCES users(id),
    id_contact_type INT NOT NULL REFERENCES contact_type(id)
);

-- ------------------------------------------------------------
-- adress
-- ------------------------------------------------------------

CREATE TABLE address (
    id           SERIAL PRIMARY KEY,
    complement   VARCHAR(255),
    cep          VARCHAR(10),
    country      VARCHAR(100),
    state        VARCHAR(100),
    city         VARCHAR(100),
    neighborhood VARCHAR(150),
    street       VARCHAR(200),
    number       VARCHAR(20),
    id_user  INT NOT NULL REFERENCES users(id)
);

-- ------------------------------------------------------------
-- character
-- ------------------------------------------------------------

CREATE TABLE character (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

-- ------------------------------------------------------------
-- figure
-- ------------------------------------------------------------

CREATE TABLE figure (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description TEXT,
    price       NUMERIC(10,2) NOT NULL,
    quantity    INT DEFAULT 0,
    is_launch   BOOLEAN DEFAULT TRUE,
    active      BOOLEAN DEFAULT TRUE,
    id_character INT NOT NULL REFERENCES character(id)
);

-- ------------------------------------------------------------
-- image
-- ------------------------------------------------------------

CREATE TABLE image(
    id            SERIAL PRIMARY KEY,
    description   TEXT NOT NULL,
    url           VARCHAR(500) NOT NULL,
    image_type    INT NOT NULL,
    id_character  INT REFERENCES character(id),
    id_figure     INT REFERENCES figure(id)
);

-- ------------------------------------------------------------
-- accessory
-- ------------------------------------------------------------

CREATE TABLE accessory (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description TEXT,
    id_image    INT NOT NULL UNIQUE REFERENCES image(id)
);

CREATE TABLE figure_accessory (
    id_figure   INT NOT NULL REFERENCES figure(id),
    id_accessory INT NOT NULL REFERENCES accessory(id),
    PRIMARY KEY (id_figure, id_accessory)
);

-- ------------------------------------------------------------
-- category
-- ------------------------------------------------------------

CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE figure_category(
    id_figure   INT NOT NULL REFERENCES figure(id),
    id_category INT NOT NULL REFERENCES category(id),
    PRIMARY KEY (id_figure, id_category)
);

-- ------------------------------------------------------------
-- feedback
-- ------------------------------------------------------------

CREATE TABLE feedback (
    id SERIAL PRIMARY KEY,
    rating INTEGER NOT NULL CHECK (rating >= 0 AND rating <= 5),
    description VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    id_figure INTEGER REFERENCES figure(id),
    id_user INTEGER REFERENCES users(id)
);

-- ------------------------------------------------------------
-- coupons
-- ------------------------------------------------------------

CREATE TABLE coupon (
    id           SERIAL PRIMARY KEY,
    end_date     DATE,
    code         VARCHAR(50) NOT NULL UNIQUE,
    description  TEXT,
    discount_pct NUMERIC(5,2),
    usage_limit  INT,
    usage_count  INT DEFAULT 0,
    active       BOOLEAN NOT NULL DEFAULT TRUE,
    start_date   DATE
);

-- ------------------------------------------------------------
-- user_order
-- ------------------------------------------------------------

CREATE TABLE user_order (
    id                 SERIAL PRIMARY KEY,
    price              NUMERIC(10,2),
    final_price        NUMERIC(10,2) NOT NULL,
    discount           NUMERIC(10,2),
    status             INT,
    installments_count INT,
    created_at         TIMESTAMP NOT NULL DEFAULT NOW(),
    id_user            INT NOT NULL REFERENCES users(id),
    id_address         INT REFERENCES address(id)
);

CREATE TABLE user_order_coupons (
    id_user_order     INT NOT NULL REFERENCES user_order(id),
    id_coupon         INT NOT NULL REFERENCES coupon(id),
    PRIMARY KEY (id_user_order, id_coupon)
);

CREATE TABLE user_order_figure (
    id_user_order     INT NOT NULL REFERENCES user_order(id),
    id_figure         INT NOT NULL REFERENCES figure(id),
    quantity          INT NOT NULL DEFAULT 1,
    price             NUMERIC(10,2),
    PRIMARY KEY (id_user_order, id_figure)
);

-- ------------------------------------------------------------
-- payment
-- ------------------------------------------------------------

CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    pay_value NUMERIC(10,2) NOT NULL,
    installment_number INT NOT NULL,
    pay_date DATE,
    valid_date DATE NOT NULL,
    id_payment_type INT NOT NULL REFERENCES payment_type(id),
    id_user_order INT NOT NULL REFERENCES user_order(id)
);

-- ------------------------------------------------------------
-- Índices
-- ------------------------------------------------------------

CREATE INDEX idx_contact_id_user
ON contact(id_user);

CREATE INDEX idx_address_id_user
ON address(id_user);

CREATE INDEX idx_user_order_id_user
ON user_order(id_user);

CREATE INDEX idx_payment_id_payment_type
ON payment(id_payment_type);

CREATE INDEX idx_figure_character
    ON figure(id_character);

CREATE INDEX idx_image_character
    ON image(id_character);

CREATE INDEX idx_image_figure
    ON image(id_figure);

CREATE INDEX idx_payment_order
    ON payment(id_user_order);