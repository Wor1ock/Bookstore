-- begin BOOKSTORE_BOOK_AUTHOR_LINK
create table BOOKSTORE_BOOK_AUTHOR_LINK (
    BOOK_ID uuid,
    AUTHOR_ID uuid,
    primary key (BOOK_ID, AUTHOR_ID)
)^

alter table BOOKSTORE_BOOK_AUTHOR_LINK add constraint FK_BOOAUT_ON_BOOK foreign key (BOOK_ID) references BOOKSTORE_BOOK(ID)^
alter table BOOKSTORE_BOOK_AUTHOR_LINK add constraint FK_BOOAUT_ON_AUTHOR foreign key (AUTHOR_ID) references BOOKSTORE_AUTHOR(ID)^
-- end BOOKSTORE_BOOK_AUTHOR_LINK