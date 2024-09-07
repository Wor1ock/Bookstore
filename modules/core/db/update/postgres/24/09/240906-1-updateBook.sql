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
);
-- end BOOKSTORE_BOOK