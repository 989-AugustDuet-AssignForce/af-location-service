create table location(
	location_id serial primary key, 
	state text, 
	city text,
	zipcode text
);

create table room(
	room_id serial primary key,
	name text, 
	type int references room_type,
	capacity int,
	building int references to building(building_id) on delete cascade
	

create table building(
	building_id serial primary key, 
	city text, 
	street_address text,
	location int references location(location_id)
);

create table room_type(
	room_type_id serial primary key,
	type text
);

create table room_occupation(
	occupation_id serial primary key,
	type text
);