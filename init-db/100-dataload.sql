-- ------------------------------------------------------------
-- Contact Types
-- ------------------------------------------------------------

INSERT INTO contact_type (type)
VALUES
('EMAIL'),
('PHONE'),
('WHATSAPP');

-- ------------------------------------------------------------
-- Users
-- ------------------------------------------------------------

INSERT INTO users (name, cpf, email, password, role)
VALUES
('João Silva', '12345678910','joao@email.com', '{bcrypt}$2b$08$y5Ao2yBAFrsL/pkQdQ.ShO9NBJVIn9uRdDmLTdVU.qasNNwz1xbSu', 1),
('Maria Souza', '12345678901', 'maria@email.com', '{bcrypt}$2b$08$y5Ao2yBAFrsL/pkQdQ.ShO9NBJVIn9uRdDmLTdVU.qasNNwz1xbSu', 1),
('Astra Admin', '54161707860', 'admin@system.com', '{bcrypt}$2b$08$y5Ao2yBAFrsL/pkQdQ.ShO9NBJVIn9uRdDmLTdVU.qasNNwz1xbSu', 0);

-- ------------------------------------------------------------
-- Contacts
-- ------------------------------------------------------------

INSERT INTO contact (value, id_user, id_contact_type)
VALUES
('11999999999',1,2),
('joao@email.com',1,1),
('11988888888',2,2);

-- ------------------------------------------------------------
-- Address
-- ------------------------------------------------------------

INSERT INTO address (
    complement,
    cep,
    state,
    city,
    neighborhood,
    street,
    number,
    id_user
)
VALUES
(
    'Apto 12',
    '01000000',
    'SP',
    'São Paulo',
    'Centro',
    'Av. Paulista',
    '1000',
    1
);

-- ------------------------------------------------------------
-- Characters
-- ------------------------------------------------------------

INSERT INTO character (name, description)
VALUES
('Naruto','Ninja protagonista'),
('Goku','Saiyajin mais forte');

-- ------------------------------------------------------------
-- Categories
-- ------------------------------------------------------------

INSERT INTO category (name, description)
VALUES
('Anime','Figuras de anime'),
('Game','Personagens de jogos');

-- ------------------------------------------------------------
-- Figures
-- ------------------------------------------------------------

INSERT INTO figure (
    name,
    description,
    price,
    quantity,
    id_character,
    is_launch
)
VALUES
('Naruto Figure','Figura do Naruto',199.90,10,1, true),
('Goku Figure','Figura do Goku',249.90,5,2, false);

-- ------------------------------------------------------------
-- Images
-- ------------------------------------------------------------

INSERT INTO image (
    description,
    url,
    image_type,
    id_character,
    id_figure
)
VALUES
    ('Naruto personagem','https://img.com/character_naruto.png',3,1,NULL),
    ('Goku personagem','https://img.com/character_goku.png',3,2,NULL),

    ('Naruto principal','https://img.com/naruto1.png',0,NULL,1),
    ('Naruto costas','https://img.com/naruto2.png',2,NULL,1),

    ('Goku principal','https://img.com/goku1.png',0,NULL,2),
    ('Goku costas','https://img.com/goku2.png',2,NULL,2),

    ('Katana','https://img.com/katana.png',4,NULL,NULL),
    ('Capa legal','https://img.com/capa.png',4,NULL,NULL);

-- ------------------------------------------------------------
-- Accessories
-- ------------------------------------------------------------

INSERT INTO accessory (
    name,
    description,
    id_image
)
VALUES
('Katana','Espada japonesa',7),
('Capa','Capa decorativa',8);

-- ------------------------------------------------------------
-- Figure Accessories
-- ------------------------------------------------------------

INSERT INTO figure_accessory (
    id_figure,
    id_accessory
)
VALUES
(1,1),
(1,2),
(2,2);

-- ------------------------------------------------------------
-- Figure Categories
-- ------------------------------------------------------------

INSERT INTO figure_category (
    id_figure,
    id_category
)
VALUES
(1,1),
(2,1);

-- ------------------------------------------------------------
-- Coupons
-- ------------------------------------------------------------

INSERT INTO coupon (
    end_date,
    code,
    description,
    discount_pct,
    usage_limit,
    usage_count,
    active,
    start_date
)
VALUES
('2026-12-31','WELCOME10','10% de desconto',10.00,100,0,TRUE,'2026-01-01'),
('2026-12-31','FRETE0','Frete grátis',0.00,50,0,TRUE,'2026-01-01');

-- ------------------------------------------------------------
-- Orders
-- ------------------------------------------------------------

INSERT INTO user_order (
    price,
    final_price,
    discount,
    status,
    id_user
)
VALUES
(249.90,224.91,24.99,4,1);

INSERT INTO user_order_coupons (
    id_user_order,
    id_coupon
)
VALUES
(1,1);

INSERT INTO user_order_figure (
    id_user_order,
    id_figure,
    quantity,
    price
)
VALUES
(1,2,1,249.90);

-- ------------------------------------------------------------
-- Payments
-- ------------------------------------------------------------

INSERT INTO payment (
    pay_value,
    pay_date,
    due_date,
    id_payment_type,
    id_user_order
)
VALUES
(
    224.91,
    CURRENT_DATE,
    '2026-12-31',
    1,
    1
);