-- begin SEC_USER
alter table SEC_USER add column CUSTOMER_ID uuid ^
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'bookstore_ExtendedUser' where DTYPE is null ^

alter table SEC_USER add constraint FK_SEC_USER_ON_CUSTOMER foreign key (CUSTOMER_ID) references BOOKSTORE_CUSTOMER(ID)^
create index IDX_SEC_USER_ON_CUSTOMER on SEC_USER (CUSTOMER_ID)^
-- end SEC_USER