INSERT INTO admin (cpf, name, email, password)
VALUES
('123.456.789-00', 'Admin Master', 'admin@system.com', '{bcrypt}$2b$08$y5Ao2yBAFrsL/pkQdQ.ShO9NBJVIn9uRdDmLTdVU.qasNNwz1xbSu');

INSERT INTO customer (name, email, password)
VALUES
('João Silva', 'joao@email.com', '{bcrypt}$2b$08$y5Ao2yBAFrsL/pkQdQ.ShO9NBJVIn9uRdDmLTdVU.qasNNwz1xbSu'),
('Maria Souza', 'maria@email.com', '{bcrypt}$2b$08$y5Ao2yBAFrsL/pkQdQ.ShO9NBJVIn9uRdDmLTdVU.qasNNwz1xbSu');

INSERT INTO contact_type (name)
VALUES
('EMAIL'),
('PHONE'),
('WHATSAPP');

INSERT INTO contact (value, id_customer, id_contact_type)
VALUES
('11999999999', 1, 2),
('joao@email.com', 1, 1),
('11988888888', 2, 2);

INSERT INTO address (
    complement, cep, country, state, city,
    neighborhood, street, number, id_customer
)
VALUES
('Apto 12', '01000-000', 'Brasil', 'SP', 'São Paulo',
 'Centro', 'Av. Paulista', '1000', 1);

INSERT INTO character (name, description)
VALUES
('Naruto', 'Ninja protagonista'),
('Goku', 'Saiyajin mais forte');

INSERT INTO category (name, description, active)
VALUES
('Anime', 'Figuras de anime', TRUE),
('Game', 'Personagens de jogos', TRUE);

INSERT INTO accessory (name, description, img_url)
VALUES
('Katana', 'Espada japonesa', 'https://img.com/katana.png'),
('Capa', 'Capa decorativa', 'https://img.com/capa.png');

INSERT INTO figure (
    name, description, price, img_url, quantity, id_character
)
VALUES
('Naruto Figure', 'Figura do Naruto', 199.90,
 'https://img.com/naruto.png', 10, 1),

('Goku Figure', 'Figura do Goku', 249.90,
 'https://img.com/goku.png', 5, 2);

INSERT INTO figure_acessory (id_figure, id_acessory)
VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO figure_category (id_figure, id_category)
VALUES
(1, 1),
(2, 1);

INSERT INTO coupon (
    end_date, code, description,
    discount_pct, usage_limit, usage_count, active, start_date
)
VALUES
('2026-12-31', 'WELCOME10', '10% de desconto', 10.00, 100, 0, TRUE, '2026-01-01'),
('2026-12-31', 'FRETE0', 'Frete grátis', 0.00, 50, 0, TRUE, '2026-01-01');

INSERT INTO customer_order (
    price, final_price, discount, status,
    installments_count, id_customer
)
VALUES
(249.90, 224.91, 24.99, 'PAID', 1, 1);

INSERT INTO customer_order_coupons (id_customer_order, coupon)
VALUES
(1, 1);

INSERT INTO customer_order_figure (
    id_customer_order, id_figure, quantity, price
)
VALUES
(1, 2, 1, 249.90);

INSERT INTO payment_type (name)
VALUES
('CREDIT_CARD'),
('PIX'),
('BOLETO');

INSERT INTO payment (
    pay_value, installment_number, pay_date,
    valid_date, id_payment_type, id_customer_order
)
VALUES
(224.91, 1, NOW(), '2026-12-31', 1, 1);