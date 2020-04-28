drop database if exists TPFinal;
create database TPFinal;
use TPFinal;

create table provinces(
	id int auto_increment,
    province_name varchar(30) unique not null,
    CONSTRAINT pk_provinces primary key (id)
);

create table cities(
	id int auto_increment,
    city_name varchar(50) not null,
    prefix int unique not null,
    id_province int,
    constraint pk_cities primary key (id),
    constraint fk_cities_id_province foreign key (id_province) references provinces(id)
);

create table rates(
	id int auto_increment,
	id_origin_city int,
	id_destination_city int,
	cost_per_minute float not null,
    constraint pk_rates primary key (id),
	constraint fk_rates_city_origin foreign key (id_origin_city) references cities(id),
    constraint fk_rates_city_destination foreign key (id_destination_city) references cities(id),
    unique key unq_rates_origin_destination (id_origin_city,id_destination_city)
);

create table users(
	id int auto_increment,
	name_user varchar(45) not null,
	lastname varchar(45) not null,
	identification_card int not null unique,
    password_user varchar(30) not null,
	id_city integer,
    constraint pk_users primary key (id),
	constraint fk_users_city foreign key(id_city) references cities(id)
);

create table employees(
	id int auto_increment unique not null,
	name_employee varchar(45) not null,
	lastname varchar(45) not null,
	identification_card int not null unique,
    password_employee varchar(30) not null,
	id_city integer,
	constraint fk_employees_city foreign key(id_city) references cities(id)
);

create table phonelines(
	id int auto_increment,
    phone_number int unique not null,
    type_user enum("mobile", "landline") not null,
    id_user int,
    id_city int,
    constraint pk_phoneLines primary key (id),
    constraint fk_phoneLines_id_user foreign key(id_user)references users(id),
     constraint fk_phoneLines_id_city foreign key(id_city)references cities(id)
);

create table invoices(
	id int auto_increment,
    id_phoneline int,
    calls_quantity int not null,
    cost_price float not null,
    total_price float not null,
    invoice_date timestamp default current_timestamp,
    is_paid boolean,
    invoice_expiration_date datetime,
    constraint pk_users primary key (id),
    constraint fk_id_phoneline_invoices foreign key(id_phoneline) references phoneLines(id)
);

create table calls(
	id int auto_increment,
    id_origin_phone int,
    id_destination_phone int,
    id_rate int,
    date_call timestamp default current_timestamp,
    price int,
    duration int,
    totalPrice int,
    constraint pk_calls primary key (id),
    constraint fk_calls_id_origin_phone foreign key(id_origin_phone)references phoneLines(id),
    constraint fk_calls_id_destination_phone foreign key (id_destination_phone) references phoneLines(id),
    constraint fk_calls_rate foreign key (id_rate) references rates(id)
);


