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

alter table BOOKSTORE_ONLINE_ORDER add constraint FK_BOOKSTORE_ONLINE_ORDER_ON_CUSTOMER foreign key (CUSTOMER_ID) references BOOKSTORE_CUSTOMER(ID)^
create index IDX_BOOKSTORE_ONLINE_ORDER_ON_CUSTOMER on BOOKSTORE_ONLINE_ORDER (CUSTOMER_ID)^
-- end BOOKSTORE_ONLINE_ORDER