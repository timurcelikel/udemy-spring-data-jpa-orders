drop table if exists customer;
create table customer
(
    id                 bigint not null auto_increment primary key,
    customer_name      varchar(50),
    address            varchar(30),
    city               varchar(30),
    state              varchar(30),
    zip_code           varchar(30),
    contact_info       varchar(250),
    phone              varchar(20),
    email              varchar(255),
    created_date       timestamp,
    last_modified_date timestamp
) engine = InnoDB;

alter table order_header
    add column customer_id bigint;
alter table order_header
    add constraint customer_id_fk
        foreign key (customer_id) references customer (id);
alter table order_header
    drop column customer;
