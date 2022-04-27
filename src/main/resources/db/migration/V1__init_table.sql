create table urls (
    id integer not null auto_increment,
    length integer,
    original_url varchar(2048),
    short_url varchar(255) unique,
    life_time long,
    primary key (id)
);

create unique index urls_short_url_uindex
    on urls (short_url);