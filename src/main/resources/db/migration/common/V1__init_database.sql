drop table if exists order_header;
create table order_header
(
    id       bigint not null auto_increment,
    customer varchar(255),
    primary key (id)
) engine = InnoDB;