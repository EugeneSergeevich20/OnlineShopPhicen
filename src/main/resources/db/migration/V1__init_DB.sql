create table carts
(
    id      bigserial not null,
    user_id bigint unique,
    primary key (id)
);

create table carts_products
(
    carts_id   bigint not null,
    product_id bigint not null
);
create table categories
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);
create table orders
(
    order_total numeric(38, 2),
    id          bigserial not null,
    order_date  timestamp(6),
    user_id     bigint,
    address     varchar(255),
    status      varchar(255) check (status in ('NEW', 'APPROVED', 'CANCEL', 'PAID', 'CLOSED')),
    primary key (id)
);
create table orders_details
(
    price      numeric(38, 2),
    quantity   integer   not null,
    id         bigserial not null,
    order_id   bigint,
    product_id bigint,
    primary key (id)
);
create table products
(
    price       numeric(38, 2),
    quantity    integer   not null,
    category_id bigint,
    id          bigserial not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table reviews
(
    id          bigserial not null,
    review_date timestamp(6),
    user_id     bigint,
    review_text varchar(255),
    primary key (id)
);
create table users
(
    id       bigserial not null,
    address  varchar(255),
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    phone    varchar(255),
    role     varchar(255) check (role in ('CLIENT', 'MANAGER', 'ADMIN')),
    surname  varchar(255),
    primary key (id)
);
create table wishlist
(
    id         bigserial not null,
    product_id bigint,
    user_id    bigint unique,
    primary key (id)
);
alter table if exists carts
    add constraint carts_users_fk
    foreign key (user_id) references users;
alter table if exists carts_products
    add constraint carts_products_products_fk
    foreign key (product_id) references products;
alter table if exists carts_products
    add constraint carts_products_carts_fk
    foreign key (carts_id) references carts;
alter table if exists orders
    add constraint orders_users_fk
    foreign key (user_id) references users;
alter table if exists orders_details
    add constraint orders_details_orders_fk
    foreign key (order_id) references orders;
alter table if exists orders_details
    add constraint orders_details_products_fk
    foreign key (product_id) references products;
alter table if exists products
    add constraint products_categories_fk
    foreign key (category_id) references categories;
alter table if exists reviews
    add constraint reviews_users_fk
    foreign key (user_id) references users;
alter table if exists wishlist
    add constraint wishlist_products_fk
    foreign key (product_id) references products;
alter table if exists wishlist
    add constraint wishlist_users_fk
    foreign key (user_id) references users;