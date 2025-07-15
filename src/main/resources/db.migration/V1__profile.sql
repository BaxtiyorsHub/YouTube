CREATE TABLE if not exists student
(
    id      serial primary key,
    name    varchar(25) not null,
    surname varchar(25) not null,
    age     int         not null
    );