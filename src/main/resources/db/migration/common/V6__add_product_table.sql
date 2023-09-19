drop table if exists product;
create table product
(
    id       bigint not null auto_increment,
    description varchar(255),
    product_status varchar(30),
    created_date timestamp,
    last_modified_date timestamp,
    primary key (id)
) engine = InnoDB;