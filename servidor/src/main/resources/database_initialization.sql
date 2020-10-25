drop table if exists locationbeat;
drop table if exists vehicle;
drop table if exists vehicletype;

create table vehicletype (
	id serial primary key,
	description varchar(50) unique not null
);

create table vehicle (
	id serial primary key,
	type_id integer not null,
	constraint type_id_fkey foreign key (type_id) references vehicletype(id)
);

create table locationbeat (
	id serial primary key,
	vehicle_id integer not null,
	latitude double precision not null,
	longitude double precision not null,
	created_on timestamp not null default current_timestamp,
	constraint vehicle_id_fkey foreign key (vehicle_id) references vehicle(id)
); 

insert into vehicletype (description) values ('Automovil');   -- id = 1
insert into vehicletype (description) values ('Motocicleta'); -- id = 2
insert into vehicletype (description) values ('Barco');       -- id = 3
insert into vehicletype (description) values ('Avion');       -- id = 4

insert into vehicle (type_id) values (1); -- id = 1
insert into vehicle (type_id) values (1); -- id = 2
insert into vehicle (type_id) values (2); -- id = 3
insert into vehicle (type_id) values (3); -- id = 4
insert into vehicle (type_id) values (3); -- id = 5

insert into locationbeat (vehicle_id, latitude, longitude, created_on) values (1, -25.2637, -57.5759, timestamp '2020-1-1 12:00:00'); -- id = 1, location = Asuncion
insert into locationbeat (vehicle_id, latitude, longitude, created_on) values (1, -25.3286, -57.5453, timestamp '2020-1-1 13:00:00'); -- id = 2, location = FDM
insert into locationbeat (vehicle_id, latitude, longitude, created_on) values (1, -25.3434, -57.5050, timestamp '2020-1-1 14:00:00'); -- id = 3, location = SL
insert into locationbeat (vehicle_id, latitude, longitude, created_on) values (1, -25.3532, -57.4440, timestamp '2020-1-1 15:00:00'); -- id = 4, location = Capiata
insert into locationbeat (vehicle_id, latitude, longitude, created_on) values (1, -25.3897, -57.3615, timestamp '2020-1-1 16:00:00'); -- id = 5, location = Itaugua
