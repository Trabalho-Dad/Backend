-- ============================================================
-- Schema gerado a partir do diagrama ER
-- ============================================================

-- ------------------------------------------------------------
-- Tabelas independentes (sem FK)
-- ------------------------------------------------------------

CREATE TABLE contact_type (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(14)  NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE character (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE payment_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- ------------------------------------------------------------
-- customer
-- ------------------------------------------------------------

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULl,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    creation_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deactivated_at TIMESTAMP
);

-- ------------------------------------------------------------
-- contact
-- ------------------------------------------------------------

CREATE TABLE contact (
    id              SERIAL PRIMARY KEY,
    value           VARCHAR(255) NOT NULL,
    id_customer     INT NOT NULL REFERENCES customer(id),
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
    id_customer  INT NOT NULL REFERENCES customer(id)
);

-- ------------------------------------------------------------
-- accessory
-- ------------------------------------------------------------

CREATE TABLE accessory (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description TEXT,
    img_url     VARCHAR(500)
);

-- ------------------------------------------------------------
-- figure
-- ------------------------------------------------------------

CREATE TABLE figure (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description TEXT,
    price       NUMERIC(10,2) NOT NULL,
    img_url     VARCHAR(500) NOT NULL,
    quantity    INT DEFAULT 0,
    active      BOOLEAN DEFAULT TRUE,
    id_character INT NOT NULL REFERENCES character(id)
);

CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE figure_acessory (
    id_figure   INT NOT NULL REFERENCES figure(id),
    id_acessory INT NOT NULL REFERENCES accessory(id),
    PRIMARY KEY (id_figure, id_acessory)
);

CREATE TABLE figure_category(
    id_figure   INT NOT NULL REFERENCES figure(id),
    id_category INT NOT NULL REFERENCES category(id),
    PRIMARY KEY (id_figure, id_category)
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
-- customer_order
-- ------------------------------------------------------------

CREATE TABLE customer_order (
    id                 SERIAL PRIMARY KEY,
    price              NUMERIC(10,2),
    final_price        NUMERIC(10,2),
    discount           NUMERIC(10,2),
    status             VARCHAR(50),
    installments_count INT,
    created_at         TIMESTAMP NOT NULL DEFAULT NOW(),
    id_customer        INT NOT NULL REFERENCES customer(id)
);

CREATE TABLE customer_order_coupons (
    id_customer_order INT NOT NULL REFERENCES customer_order(id),
    id_coupon         INT NOT NULL REFERENCES coupon(id),
    PRIMARY KEY (id_customer_order, id_coupon)
);

CREATE TABLE customer_order_figure (
    id_customer_order INT NOT NULL REFERENCES customer_order(id),
    id_figure         INT NOT NULL REFERENCES figure(id),
    quantity          INT NOT NULL DEFAULT 1,
    price             NUMERIC(10,2),
    PRIMARY KEY (id_customer_order, id_figure)
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
    id_customer_order INT NOT NULL REFERENCES customer_order(id)
);

-- ============================================================
-- Índices sugeridos
-- ============================================================

CREATE INDEX idx_contact_id_customer
ON contact(id_customer);

CREATE INDEX idx_address_id_customer
ON address(id_customer);

CREATE INDEX idx_customer_order_id_customer
ON customer_order(id_customer);

CREATE INDEX idx_payment_id_payment_type
ON payment(id_payment_type);