DROP TABLE IF EXISTS Language;
DROP TABLE IF EXISTS User_Type CASCADE;
DROP TABLE IF EXISTS User_Info CASCADE;
DROP TABLE IF EXISTS Category CASCADE;
DROP TABLE IF EXISTS Team CASCADE;
DROP TABLE IF EXISTS Kit_Type CASCADE;
DROP TABLE IF EXISTS ProductImage CASCADE;
DROP TABLE IF EXISTS Product CASCADE;
DROP TABLE IF EXISTS Delivery_Method CASCADE;
DROP TABLE IF EXISTS OrderStatus CASCADE;
DROP TABLE IF EXISTS Order_Info CASCADE;
DROP TABLE IF EXISTS OrderDetails CASCADE;
DROP TABLE IF EXISTS order_product CASCADE;

CREATE TABLE Language(
    id serial primary key,
    name varchar not null
);

CREATE TABLE UserType(
	id serial primary key,
	name varchar,
	language_id integer not null,
	FOREIGN KEY(language_id) REFERENCES Language(id)
);

CREATE TABLE UserInfo (
	id serial primary key,
	name varchar not null,
	email varchar not null,
	login varchar not null unique,
	password varchar,
	user_type_id int,
	FOREIGN KEY(user_type_id) REFERENCES UserType(id)
);

CREATE TABLE Category(
	id serial primary key,
	name varchar not null,
	language_id integer not null,
	FOREIGN KEY(language_id) REFERENCES Language(id)
);

CREATE TABLE Team(
	id serial primary key,
	name varchar not null,
	logo bytea
);

CREATE TABLE KitType(
	id serial primary key,
	name varchar not null,
	language_id integer not null,
	FOREIGN KEY(language_id) REFERENCES Language(id)
);

CREATE TABLE Product (
	id serial primary key,
	name varchar not null,
	quantity integer default(1),
	category_id integer not null,
	team_id integer not null,
	kit_type_id integer,
	current_price money,
	FOREIGN KEY(category_id) REFERENCES Category(id),
	FOREIGN KEY(team_id) REFERENCES Team(id),
	FOREIGN KEY(kit_type_id) REFERENCES KitType(id)
);

CREATE TABLE ProductImage(
    id serial primary key,
    image bytea,
    product_id integer,
    FOREIGN KEY(product_id) REFERENCES Product(id)
);

CREATE TABLE DeliveryMethod(
	id serial primary key,
	name varchar not null,
	price money,
	language_id integer not null,
	FOREIGN KEY(language_id) REFERENCES Language(id)
);

CREATE TABLE OrderStatus(
    id serial primary key,
    name varchar,
    language_id integer not null,
	FOREIGN KEY(language_id) REFERENCES Language(id)
);

CREATE TABLE OrderInfo(
	id serial primary key,
	user_id integer not null,
	date_of_order timestamp(0),
	date_of_sending date,
	delivery_method_id integer not null,
	status_id integer,
	FOREIGN KEY(delivery_method_id) REFERENCES DeliveryMethod(id),
	FOREIGN KEY(user_id) REFERENCES UserInfo(id),
	FOREIGN KEY (status_id) REFERENCES OrderStatus(id)
);

CREATE TABLE OrderDetails(
    id serial primary key,
	order_id integer,
	product_id integer,
	product_price money,
	FOREIGN KEY(order_id) REFERENCES OrderInfo(id),
	FOREIGN KEY(product_id) REFERENCES Product(id)
);
