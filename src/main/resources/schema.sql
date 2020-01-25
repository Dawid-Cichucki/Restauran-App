CREATE TABLE client(
id serial,
name varchar(255),
surname varchar(255),
discout double precision,
PRIMARY KEY (id)
);

CREATE TABLE dish(
id serial,
receipt_id bigint,
name varchar(255),
description varchar (255),
price double precision,
is_available boolean,
PRIMARY KEY (id),
FOREIGN KEY (receipt_id) REFERENCES receipt (id)
);

CREATE TABLE drink(
id serial,
receipt_id bigint,
name varchar(255),
description varchar(255),
price double precision,
serving double precision,
is_available boolean,
PRIMARY KEY (id),
FOREIGN KEY (receipt_id) REFERENCES receipt (id)
);

CREATE TABLE employee(
id serial,
name varchar(255),
surname varchar(255),
personal_identity_number varchar(255),
job varchar(255),
salary bigint,
superior_id bigint,
superior_number bigint,
PRIMARY KEY (id),
FOREIGN KEY (superior_id) REFERENCES employee (id)
)

CREATE TABLE receipt(
id serial,
dish_id bigint,
drink_id bigint,
total_price double precision,
client_id bigint,
employee_id bigint,
local_date_time date,
PRIMARY KEY (id),
FOREIGN KEY (drink_id) REFERENCES drink (id),
FOREIGN KEY (dish_id) REFERENCES dish (id)
)


