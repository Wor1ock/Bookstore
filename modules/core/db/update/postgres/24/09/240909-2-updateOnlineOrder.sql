update BOOKSTORE_ONLINE_ORDER set ADDRESS_CITY = '' where ADDRESS_CITY is null ;
alter table BOOKSTORE_ONLINE_ORDER alter column ADDRESS_CITY set not null ;
update BOOKSTORE_ONLINE_ORDER set ADDRESS_STREET = '' where ADDRESS_STREET is null ;
alter table BOOKSTORE_ONLINE_ORDER alter column ADDRESS_STREET set not null ;
update BOOKSTORE_ONLINE_ORDER set ADDRESS_BUILDING = '' where ADDRESS_BUILDING is null ;
alter table BOOKSTORE_ONLINE_ORDER alter column ADDRESS_BUILDING set not null ;
