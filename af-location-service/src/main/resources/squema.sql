drop table location if exists;
drop table room if exists;
drop table building if exists;
drop table room_type if exists;
drop table room_occupation if exists;

create table location(
	location_id serial primary key, 
	state text, 
	city text,
	zip_code text,
-- 	building_id int not null references building(building_id) on delete cascade;
);

create table room(
	room_id serial primary key,
	name text, 
	type int not null references room_type,
	capacity int,
	building_id int not null references building(building_id) on delete cascade;
);

create table building(
	building_id serial primary key, 
	city text, 
	street_address text,
	location_id int not null references location(location_id) on delete cascade;
);

create table room_type(
	room_type_id serial primary key,
	type text
);

create table room_occupation(
	occupation_id serial primary key,
	type text
);