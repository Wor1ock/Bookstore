-- begin BOOKSTORE_ONLINE_ORDER
create table BOOKSTORE_ONLINE_ORDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS_CITY varchar(100) not null,
    ADDRESS_STREET varchar(100) not null,
    ADDRESS_BUILDING varchar(5) not null,
    --
    STATUS varchar(50),
    CUSTOMER_ID uuid,
    --
    primary key (ID)
)^
-- end BOOKSTORE_ONLINE_ORDER
-- begin BOOKSTORE_AUTHOR
create table BOOKSTORE_AUTHOR (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FULL_NAME varchar(450) not null,
    EMAIL varchar(255),
    --
    primary key (ID)
)^
-- end BOOKSTORE_AUTHOR
-- begin BOOKSTORE_CUSTOMER
create table BOOKSTORE_CUSTOMER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FULL_NAME varchar(450) not null,
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end BOOKSTORE_CUSTOMER
-- begin BOOKSTORE_BOOK
create table BOOKSTORE_BOOK (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(250) not null,
    PRICE decimal(10, 2),
    IS_AVAILABLE boolean,
    --
    primary key (ID)
)^
-- end BOOKSTORE_BOOK
-- begin BOOKSTORE_ORDER_LINE
create table BOOKSTORE_ORDER_LINE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    QUANTITY integer not null,
    BOOK_ID uuid,
    BOOK_TITLE varchar(250) not null,
    BOOK_PRICE decimal(10, 2),
    ONLINE_ORDER_ID uuid not null,
    --
    primary key (ID)
)^
-- end BOOKSTORE_ORDER_LINE
-- begin BOOKSTORE_BOOK_AUTHOR_LINK
create table BOOKSTORE_BOOK_AUTHOR_LINK (
    BOOK_ID uuid,
    AUTHOR_ID uuid,
    primary key (BOOK_ID, AUTHOR_ID)
)^
-- end BOOKSTORE_BOOK_AUTHOR_LINK
