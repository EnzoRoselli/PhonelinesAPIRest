drop database if exists TPFinal;
create database TPFinal;
use TPFinal;
SET GLOBAL time_zone =  '-3:00';

create table provinces(
	id int auto_increment,
    province_name varchar(30) unique not null,
    CONSTRAINT pk_provinces primary key (id)
);

create table cities(
	id int auto_increment,
    city_name varchar(50) not null,
    prefix varchar(4) unique not null,
    id_province int,
    constraint pk_cities primary key (id),
    constraint fk_cities_id_province foreign key (id_province) references provinces(id)
);

create table rates(
	id int auto_increment,
	id_origin_city int,
	id_destination_city int,
	cost_per_minute double not null,
    price_per_minute double not null,
    constraint pk_rates primary key (id),
	constraint fk_rates_city_origin foreign key (id_origin_city) references cities(id),
    constraint fk_rates_city_destination foreign key (id_destination_city) references cities(id),
    unique key unq_rates_origin_destination (id_origin_city,id_destination_city)
);

create table users(
	id int auto_increment,
	user_name varchar(45) not null,
	lastname varchar(45) not null,
    user_type enum("client", "employee", "infrastructure") not null,
	identification_card varchar(10) not null,
    user_password varchar(30) not null,
	id_city integer,
    user_status boolean default true,
	constraint unq_identification_type unique(identification_card,user_type),
    constraint pk_users primary key (id),
	constraint fk_users_city foreign key(id_city) references cities(id)
);


create table phonelines(
	id int auto_increment,
	phone_number varchar(8) not null,
    phoneline_type enum("mobile", "landline") not null,
    phoneline_status boolean default true,
    id_user int,
    id_city int,
    constraint unq_number_with_cityprefix unique(phone_number,id_city),
    constraint pk_phoneLines primary key (id),
    constraint fk_phoneLines_id_user foreign key(id_user)references users(id),
     constraint fk_phoneLines_id_city foreign key(id_city)references cities(id)
);

create table invoices(
	id int auto_increment,
    id_phone_number int,
    calls_quantity int not null,
    total_cost double not null,
    total_price double not null,
    invoice_date timestamp default current_timestamp,
    is_paid boolean default false,
    invoice_expiration_date datetime,
    constraint pk_invoices primary key (id),
    constraint fk_invoices_id_phone_number foreign key(id_phone_number) references phonelines(id)
);


create table calls(
	id int auto_increment,
    id_origin_phone int,
    origin_phone varchar(10),
    id_destination_phone int,
    destination_phone varchar(10),
    id_rate int,
    id_invoice int,
    date_call timestamp default current_timestamp,
    total_cost double,
    duration int,
    total_price double,
    constraint pk_calls primary key (id),
    constraint fk_calls_id_origin_phone foreign key(id_origin_phone)references phonelines(id),
    constraint fk_calls_id_destination_phone foreign key (id_destination_phone) references phonelines(id),
    constraint fk_calls_rate foreign key (id_rate) references rates(id),
    constraint fk_calls_invoice foreign key (id_invoice) references invoices(id)
);


/***************************************************TRIGGER, SP Y FUNCIONES**************************************/
DROP TRIGGER IF EXISTS tbi_upload_calls;

DELIMITER $$

CREATE TRIGGER tbi_upload_calls BEFORE INSERT ON calls FOR EACH ROW
BEGIN
    SET new.id_origin_phone = get_id_phone_number_by_phone_number(new.origin_phone);
	SET new.id_destination_phone = get_id_phone_number_by_phone_number(new.destination_phone);
	SET new.id_rate = get_id_rate(new.origin_phone, new.destination_phone);
	SET new.total_cost = calculate_total_cost(new.id_rate, new.duration);
	SET new.total_price = calculate_total_price(new.id_rate, new.duration);

    call sp_throw_signal_if_error(new.id_origin_phone, new.id_destination_phone, new.id_rate);
END $$
DELIMITER ;


DROP FUNCTION IF EXISTS get_id_phone_number_by_phone_number;

DELIMITER $$
	CREATE FUNCTION get_id_phone_number_by_phone_number(phone_number varchar(10))
	RETURNS int
	DETERMINISTIC
	BEGIN
		DECLARE phoneId int;
		DECLARE cityId int;
        DECLARE prefix_lenght smallint;
        DECLARE phone_without_prefix varchar(8);

		SET cityId = get_id_city_by_phone_number(phone_number);
        SET prefix_lenght = LENGTH((SELECT prefix FROM cities WHERE id = cityId));

        SET phone_without_prefix = (SELECT SUBSTR(phone_number FROM prefix_lenght+1));
        SET phoneId = (ifnull((SELECT id FROM phonelines p WHERE p.phone_number = phone_without_prefix AND p.id_city = cityId), 0));

		return phoneId;
	END $$
DELIMITER ;


DROP FUNCTION IF EXISTS get_id_city_by_phone_number;

DELIMITER $$
	CREATE FUNCTION get_id_city_by_phone_number(phone_number varchar(10))
    RETURNS int
	DETERMINISTIC
	BEGIN
		DECLARE cityId int;
        DECLARE i int;
        DECLARE chars_taken varchar(10);

        SET i = 1;
        SET cityId = 0;
        SET chars_taken = "";

		WHILE (i < 5)  DO

			SET chars_taken = (SELECT LEFT(phone_number, i));
            SET cityId = (SELECT ifnull((SELECT id FROM cities WHERE prefix = chars_taken), cityId));
			SET i = i + 1;
        END WHILE;

		return cityId;
	END $$
DELIMITER ;


DROP FUNCTION IF EXISTS get_id_rate;

DELIMITER $$
	CREATE FUNCTION get_id_rate(origin_number varchar(10), destination_number varchar(10))
	RETURNS int
	DETERMINISTIC
	BEGIN
			DECLARE rate_id int;

            SET rate_id = ifnull((select rates.id from rates where
							rates.id_origin_city = get_id_city_by_phone_number(origin_number) and
							rates.id_destination_city = get_id_city_by_phone_number(destination_number)), 0);

			return rate_id;
	END $$
DELIMITER ;


DROP FUNCTION IF EXISTS calculate_total_cost;

DELIMITER $$
CREATE FUNCTION calculate_total_cost(id_rate int,duration long)
RETURNS double
DETERMINISTIC
BEGIN
	DECLARE total_cost float;
    SET total_cost = (duration/60) * (select rates.cost_per_minute from rates where rates.id=id_rate);

    return ROUND(total_cost,2);
END  $$
DELIMITER ;


DROP FUNCTION IF EXISTS calculate_total_price;

DELIMITER $$
CREATE FUNCTION calculate_total_price(id_rate int,duration long)
RETURNS double
DETERMINISTIC
BEGIN
	DECLARE total_price float;
    SET total_price = (duration/60) * (select rates.price_per_minute from rates where rates.id=id_rate);

    return ROUND(total_price,2);
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS sp_throw_signal_if_error;

DELIMITER $$

CREATE PROCEDURE sp_throw_signal_if_error(IN id_origin_phone int, IN id_destination_phone int, IN id_rate int)
BEGIN
	if(id_origin_phone = 0)then
		signal sqlstate '45000' SET MESSAGE_TEXT = 'Invalid origin number', MYSQL_ERRNO = 0001;
	end if;

    if(id_destination_phone = 0)then
		signal sqlstate '45000' SET MESSAGE_TEXT = 'Invalid destination number', MYSQL_ERRNO = 0002;
	end if;

    if(id_rate = 0)then
		signal sqlstate '45000' SET MESSAGE_TEXT = 'No rate matches the origin phone city and the destination phone city', MYSQL_ERRNO = 0003;
	end if;
END $$
DELIMITER ;


 /*****************************************************USUARIOS******************************************/

 /*BACKOFFICE*/
 create user 'backoffice'@'localhost' identified by '1234';
 grant all on TPFinal.users to 'backoffice'@'localhost';
 grant all on TPFinal.phonelines to 'backoffice'@'localhost';
 grant all on TPFinal.rates to 'backoffice'@'localhost';

/*CLIENTES*/
 create user 'clientes'@'localhost' identified by '1234';
 grant select on TPFinal.calls to 'clientes'@'localhost';
 grant select on TPFinal.invoices to 'clientes'@'localhost';

  /*INFRAERSTRUCTURA*/
 create user 'infraestructura'@'localhost' identified by '1234';
 grant insert on TPFinal.calls to 'infraestructura'@'localhost';
 grant trigger on TPFinal.* to 'infraestructura'@'localhost';

 /*FACTURACION*/
 create user 'facturacion'@'localhost' identified by '1234';
 GRANT EVENT ON TPFinal.* TO 'facturacion'@'localhost';
 GRANT EXECUTE ON PROCEDURE sp_get_calls_cost_and_price to 'facturacion'@'localhost';
 GRANT EXECUTE ON PROCEDURE sp_assign_id_invoice_to_calls to 'facturacion'@'localhost';

 /*************************************************EVENT SCHEDULE Y SP*****************************************/
SET GLOBAL event_scheduler = ON;


DROP EVENT IF EXISTS auto_invoice_generator;

DELIMITER $$

CREATE DEFINER = 'root'@'localhost' EVENT IF NOT EXISTS auto_invoice_generator ON SCHEDULE
EVERY 30 DAY
STARTS '2020-06-01 00:00:00'
ENABLE
DO
	BEGIN
		DECLARE phonelineId int;
		DECLARE done INT DEFAULT 0;
		DECLARE cur_phonelines CURSOR FOR SELECT id FROM TPFinal.phonelines;
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

		OPEN cur_phonelines;

		loop_phonelines:LOOP

			FETCH cur_phonelines INTO phonelineId;

			IF done = 1 THEN
				LEAVE loop_phonelines;
			END IF;

            call sp_get_calls_cost_and_price(phonelineId, @callsQuantity, @totalCost, @totalPrice);

            insert into TPFinal.invoices(id_phone_number, calls_quantity, total_cost, total_price, invoice_expiration_date)
								values(phonelineId, @callsQuantity, @totalCost, @totalPrice, NOW() + INTERVAL 15 DAY);

			call sp_assign_id_invoice_to_calls(last_insert_id(), phonelineId);
		END LOOP;

		CLOSE cur_phonelines;
    END$$

DELIMITER ;


DROP PROCEDURE IF EXISTS sp_get_calls_cost_and_price;

DELIMITER $$
CREATE PROCEDURE sp_get_calls_cost_and_price(IN id_phoneline INT, OUT calls_quantity INT, OUT total_cost double, OUT total_price double)
BEGIN
	SET total_cost = ifnull((SELECT SUM(calls.total_cost) FROM calls WHERE id_origin_phone = id_phoneline AND isnull(id_invoice)), 0);

    SET calls_quantity = ifnull((SELECT COUNT(calls.id) FROM calls WHERE id_origin_phone = id_phoneline  AND isnull(id_invoice)), 0);

    SET total_price = ifnull((SELECT SUM(calls.total_price) FROM calls WHERE id_origin_phone = id_phoneline  AND isnull(id_invoice)), 0);
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS sp_assign_id_invoice_to_calls;

DELIMITER $$
CREATE PROCEDURE sp_assign_id_invoice_to_calls(IN invoiceId int,IN phonelineId INT)
BEGIN
	UPDATE calls SET id_invoice = invoiceId WHERE id_origin_phone = phonelineId;
END$$
DELIMITER ;
