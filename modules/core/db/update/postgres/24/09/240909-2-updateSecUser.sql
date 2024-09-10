-- begin SEC_USER
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'bookstore_ExtUser' where DTYPE is null ^
-- end SEC_USER