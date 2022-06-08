
drop table vehicle_statuses;
drop table rents;
drop table vehicle_details;
drop table vehicles;
drop table categories;
drop table car_classes;
drop table engines;
drop table clients;
drop table users_companies;
drop table companies;
drop table roles_permissions;
drop table permissions;
drop table roles;

create table roles(
	id serial not null primary key,
	role_name varchar(255) not null,
	inherit_role_id integer null,
	constraint ux_roles_role_name unique(role_name),
	constraint fk_roles_inherit_role_id foreign key (inherit_role_id) references roles(id)
);

create table permissions(
	id serial not null primary key,
	permission_name varchar(255) not null,
	constraint ux_permissions_permision_name unique(permission_name)
);

create table roles_permissions(
	role_id int8 not null,
	permission_id int8 not null,
	constraint fk_roles_permissions_role_id foreign key(role_id) references roles(id),
	constraint fk_roles_permissions_permission_id foreign key(permission_id) references permissions(id)
);

create table users(
	id bigserial not null primary key,
	username varchar(255) not null,
	password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
	role_id int2 not null,
	created_ts timestamp not null default now(),
	updated_ts timestamp not null default now(),
	constraint ux_users_username unique(username),
	constraint fk_users_role_id foreign key(role_id) references roles(id)
);

create table companies(
    id bigserial not null primary key,
    name varchar(255) not null,
	created_ts timestamp not null default now(),
	updated_ts timestamp not null default now()
);

create table users_companies(
    user_id int8 not null,
    company_id int8 not null,
    constraint fk_users_companies_user_id foreign key(user_id) references users(id),
    constraint fk_users_companies_company_id foreign key(company_id) references companies(id)
);

create table clients(
	id bigserial not null primary key,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	email varchar(320) not null,
	phone varchar(100) not null,
	created_ts timestamp not null default now(),
	updated_ts timestamp not null default now(),
	company_id int not null,
	constraint fk_clients_company_id foreign key(company_id) references companies(id),
	constraint ux_clients_email unique(email)
);

create table engines(
	id bigserial not null primary key,
	name varchar(100) not null,
	constraint ux_engines_name unique(name)
);

create table car_classes(
	id bigserial not null primary key,
	name varchar(100) not null,
	constraint ux_car_classes_name unique(name)
);

create table categories(
	id bigserial not null primary key,
	name varchar(100) not null,
	constraint ux_categories_name unique(name)
);

create table vehicles(
	id bigserial not null primary key,
	make varchar(255) not null,
	model varchar(255) not null,
	year int not null,
	car_class_id int not null,
	category_id int not null,
	engine_id int not null,
	available bool not null,
	--vehicle_details_id int,
	company_id int not null,
	created_ts timestamp not null default now(),
	updated_ts timestamp not null default now(),
	constraint fk_vehicles_car_class_id foreign key(car_class_id) references car_classes(id),
	constraint fk_vehicles_category_id foreign key(category_id) references categories(id),
	constraint fk_vehicles_engine_id foreign key(engine_id) references engines(id),
	--constraint fk_vehicles_vehicle_details_id foreign key(vehicle_details_id) references vehicle_details(id),
	constraint fk_vehicles_company_id foreign key(company_id) references companies(id)
);

create table vehicle_details(
	id bigserial not null primary key,
	vehicle_id int unique,
	doors varchar(100) not null,
	day_price numeric not null,
	kilometer_price numeric not null,
	air_conditioner bool not null,
	photo_url varchar(2048) not null,
	smoke bool not null,
	created_ts timestamp not null default now(),
	updated_ts timestamp not null default now(),
	constraint fk_vehicle_details_vehicle_id foreign key(vehicle_id) references vehicles(id)
);

create table rents(
	id bigserial not null primary key,
	date_from timestamp not null,
	date_to timestamp not null,
	final_price numeric not null,
	user_id int not null,
	client_id int not null,
	vehicle_id int not null,
	date_returned timestamp,
	created_ts timestamp not null default now(),
	updated_ts timestamp not null default now(),
	kilometers int,
	constraint fk_rents_user_id foreign key(user_id) references users(id),
	constraint fk_rents_client_id foreign key(client_id) references clients(id),
	constraint fk_rents_vehicle_id foreign key(vehicle_id) references vehicles(id)
);


create table vehicle_statuses(
	id bigserial not null primary key,
	vehicle_id int not null,
	rent_id int not null,
	description text not null,
	created_ts timestamp not null default now(),
	updated_ts timestamp not null default now(),
	constraint fk_vehicle_statuses_vehicle_id foreign key(vehicle_id) references vehicles(id),
	constraint fk_vehicle_statuses_rent_id foreign key(rent_id) references rents(id)
);

INSERT INTO public.permissions
(id, permission_name)
VALUES(1, 'view');
INSERT INTO public.permissions
(id, permission_name)
VALUES(2, 'add');
INSERT INTO public.permissions
(id, permission_name)
VALUES(3, 'edit');

INSERT INTO public.roles
(id, role_name, inherit_role_id)
VALUES(1, 'agent', NULL);
INSERT INTO public.roles
(id, role_name, inherit_role_id)
VALUES(2, 'manager', 1);
INSERT INTO public.roles
(id, role_name, inherit_role_id)
VALUES(3, 'admin', 2);

INSERT INTO public.roles_permissions
(role_id, permission_id)
VALUES(1, 1);
INSERT INTO public.roles_permissions
(role_id, permission_id)
VALUES(2, 2);
INSERT INTO public.roles_permissions
(role_id, permission_id)
VALUES(2, 3);

INSERT INTO public.users
(username, "password", first_name, last_name, role_id)
VALUES('user1', '$2a$10$m3hJHvGrXGlvToBrXRKxQ.Nz/p3JxEhnBpv3Pu61CljJHY17wyuIq', 'Ivan', 'Ivanov', 3);
