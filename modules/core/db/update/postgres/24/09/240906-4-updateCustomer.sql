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

alter table BOOKSTORE_CUSTOMER add constraint FK_BOOKSTORE_CUSTOMER_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
create index IDX_BOOKSTORE_CUSTOMER_ON_USER on BOOKSTORE_CUSTOMER (USER_ID)^
-- end BOOKSTORE_CUSTOMER