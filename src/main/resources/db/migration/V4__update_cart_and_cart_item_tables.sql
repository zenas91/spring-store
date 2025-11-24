alter table carts
    alter column id set default (uuid_to_bin(uuid()));

alter table carts
    change dateCreated date_created date default (curdate()) not null;

alter table cart_items
    change cart cart_id binary(16) not null;

alter table cart_items
    change product product_id bigint not null;

alter table cart_items
    add constraint cart_items_cart_product_unique
        unique (cart_id, product_id);