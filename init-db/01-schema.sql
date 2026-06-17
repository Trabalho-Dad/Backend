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
    customer_id     INT NOT NULL REFERENCES customer(id),
    contact_type_id INT NOT NULL REFERENCES contact_type(id)
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
    customer_id  INT NOT NULL REFERENCES customer(id)
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
    character_id INT NOT NULL REFERENCES character(id)
);

CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE figure_acessory (
    figure_id   INT NOT NULL REFERENCES figure(id),
    acessory_id INT NOT NULL REFERENCES accessory(id),
    PRIMARY KEY (figure_id, acessory_id)
);

CREATE TABLE figure_category(
    figure_id   INT NOT NULL REFERENCES figure(id),
    category_id INT NOT NULL REFERENCES category(id),
    PRIMARY KEY (figure_id, category_id)
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
    customer_id        INT NOT NULL REFERENCES customer(id)
);

CREATE TABLE customer_order_coupons (
    customer_order_id INT NOT NULL REFERENCES customer_order(id),
    coupon_id        INT NOT NULL REFERENCES coupon(id),
    PRIMARY KEY (customer_order_id, coupon_id)
);

CREATE TABLE customer_order_figure (
    customer_order_id INT NOT NULL REFERENCES customer_order(id),
    figure_id         INT NOT NULL REFERENCES figure(id),
    quantity          INT NOT NULL DEFAULT 1,
    price             NUMERIC(10,2),
    PRIMARY KEY (customer_order_id, figure_id)
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
    payment_type_id INT NOT NULL REFERENCES payment_type(id),
    customer_order_id INT NOT NULL REFERENCES customer_order(id)
);

-- ============================================================
-- Índices sugeridos
-- ============================================================

CREATE INDEX idx_contact_id_customer
ON contact(customer_id);

CREATE INDEX idx_address_id_customer
ON address(customer_id);

CREATE INDEX idx_customer_order_id_customer
ON customer_order(customer_id);

CREATE INDEX idx_payment_id_payment_type
ON payment(payment_type_id);