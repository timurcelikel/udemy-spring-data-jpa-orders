alter table order_header
    add column shipping_address  varchar(30),
    add column shipping_city     varchar(30),
    add column shipping_state    varchar(30),
    add column shipping_zip_code varchar(30),
    add column billing_address   varchar(30),
    add column billing_city      varchar(30),
    add column billing_state     varchar(30),
    add column billing_zip_code  varchar(30);