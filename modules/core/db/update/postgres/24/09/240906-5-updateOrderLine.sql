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
);

alter table BOOKSTORE_ORDER_LINE add constraint FK_BOOKSTORE_ORDER_LINE_ON_BOOK foreign key (BOOK_ID) references BOOKSTORE_BOOK(ID)^
alter table BOOKSTORE_ORDER_LINE add constraint FK_BOOKSTORE_ORDER_LINE_ON_ONLINE_ORDER foreign key (ONLINE_ORDER_ID) references BOOKSTORE_ONLINE_ORDER(ID)^
create index IDX_BOOKSTORE_ORDER_LINE_ON_BOOK on BOOKSTORE_ORDER_LINE (BOOK_ID)^
create index IDX_BOOKSTORE_ORDER_LINE_ON_ONLINE_ORDER on BOOKSTORE_ORDER_LINE (ONLINE_ORDER_ID)^
-- end BOOKSTORE_ORDER_LINE