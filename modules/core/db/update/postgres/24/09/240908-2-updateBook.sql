update BOOKSTORE_BOOK set TITLE = '' where TITLE is null ;
alter table BOOKSTORE_BOOK alter column TITLE set not null ;
