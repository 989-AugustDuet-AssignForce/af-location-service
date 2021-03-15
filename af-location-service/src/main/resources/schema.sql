--drop table location if exists;
--drop table room if exists;
--drop table building if exists;
--drop table room_type if exists;
--drop table room_occupation if exists;

create table if not exists location(
	location_id serial not null primary key auto_increment,
	state text not null,
	city text not null ,
	zip_code text not null
);
--
--   create table if not exists room_type(
--	room_type_id serial primary key,
--	type text
--   );
--
--   create table if not exists room_occupation(
--	occupation_id serial primary key,
--	type text
--);
--
create table if not exists building(
	building_id serial not null primary key auto_increment,
	city text not null,
	street_address text not null,
	location_id int not null references location(location_id) on delete cascade,
	total_floors int not null
);
--
create table if not exists room(
	room_id serial not null primary key auto_increment,
	name text not null,
	type int not null,
	occupation int not null,
	capacity int not null,
	building_id int not null references building(building_id) on delete cascade,
	floor_number int not null
);
