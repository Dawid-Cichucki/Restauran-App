CREATE SEQUENCE hibernate_sequence;

CREATE TABLE client(
id serial,
name varchar(255) NOT NULL,
surname varchar(255) NOT NULL,
discount double precision NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE employee(
id serial,
name varchar(255) NOT NULL,
surname varchar(255) NOT NULL,
personal_identity_number varchar(255) UNIQUE,
job varchar(255) NOT NULL,
salary bigint NOT NULL,
superior_id bigint,
superior_number bigint,
PRIMARY KEY (id),
FOREIGN KEY (superior_id) REFERENCES employee (id)
);

CREATE TABLE dish(
id serial,
name varchar(255) NOT NULL,
description varchar (255) NOT NULL,
price double precision NOT NULL,
is_available boolean NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE drink(
id serial,
name varchar(255) NOT NULL,
description varchar(255) NOT NULL,
price double precision NOT NULL,
serving double precision NOT NULL,
is_available boolean NOT NULL,
PRIMARY KEY (id)
);



CREATE TABLE receipt(
id serial,
total_price double precision,
client_id bigint NOT NULL,
employee_id bigint NOT NULL,
local_date_time date NOT NULL,
tip double precision,
PRIMARY KEY (id),
FOREIGN KEY (employee_id) REFERENCES employee (id),
FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE drink_receipt(
receipt_id bigint NOT NULL,
drink_id bigint NOT NULL,
FOREIGN KEY (receipt_id) REFERENCES receipt (id),
FOREIGN KEY (drink_id) REFERENCES drink (id)
);

CREATE TABLE receipt_dish(
receipt_id bigint NOT NULL,
dish_id bigint NOT NULL,
FOREIGN KEY (receipt_id) REFERENCES receipt (id),
FOREIGN KEY (dish_id) REFERENCES dish (id)
);


