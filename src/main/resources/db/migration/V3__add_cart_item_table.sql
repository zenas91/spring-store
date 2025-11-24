create table cart_items
(
    id       bigint auto_increment
        primary key,
    quantity int        not null,
    cart     binary(16) not null,
    product  bigint     not null,
    constraint cart_items_carts_id_fk
        foreign key (cart) references carts (id)
            on delete cascade,
    constraint cart_items_products_id_fk
        foreign key (product) references products (id)
            on delete cascade
);