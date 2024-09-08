update BOOKSTORE_ORDER_LINE set QUANTITY = 0 where QUANTITY is null ;
alter table BOOKSTORE_ORDER_LINE alter column QUANTITY set not null ;
update BOOKSTORE_ORDER_LINE set BOOK_TITLE = '' where BOOK_TITLE is null ;
alter table BOOKSTORE_ORDER_LINE alter column BOOK_TITLE set not null ;
-- update BOOKSTORE_ORDER_LINE set ONLINE_ORDER_ID = <default_value> where ONLINE_ORDER_ID is null ;
alter table BOOKSTORE_ORDER_LINE alter column ONLINE_ORDER_ID set not null ;
