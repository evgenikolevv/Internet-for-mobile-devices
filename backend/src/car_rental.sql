drop table users;
drop table companies;
drop table roles_permissions;
drop table roles;
drop table persmissions;

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
	constraint ux_users_username unique(username),
	constraint fk_users_role_id foreign key(role_id) references roles(id)
);

create table companies(
    id bigserial not null primary key,
    name varchar(255) not null
)

create table users_companies(
    user_id int8 not null,
    company_id int8 not null,
    constraint fk_users_companies_user_id foreign key(user_id) references users(id),
    constraint fk_users_companies_company_id foreign key(company_id) references companies(id)
)

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