-- begin BOOKSTORE_AUTHOR
create table BOOKSTORE_AUTHOR (
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
    EMAIL varchar(255),
    --
    primary key (ID)
)^

create unique index IDX_BOOKSTORE_AUTHOR_UK_EMAIL on BOOKSTORE_AUTHOR (EMAIL) where DELETE_TS is null ^
-- end BOOKSTORE_AUTHOR