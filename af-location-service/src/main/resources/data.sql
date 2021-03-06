
--Location inserts
insert into "LOCATION" (state,city,zip_code)
VALUES('VA','Reston','20190');

insert into "LOCATION" (state,city,zip_code)
VALUES('FL','Tampa','33620');

insert into "LOCATION" (state,city,zip_code)
VALUES('TX','Arlington','76019');

--Building Inserts
insert into "BUILDING" (city,street_address, location_id, total_floors)
VALUES('Reston','11730 Plaza America Dr',1, 6),
('Tampa','4202 E Fowler Ave',2, 4),
('Tampa','USF Apple Dr',2, 7),
('Arlington','701 S Nedderman Dr',3, 3);

----Room Inserts
insert into "ROOM" (name,type,occupation,capacity,building_id,floor_number)
VALUES('201',0,1,30,1,0),
('202',0,0,45,1,0),
('203',0,1,30,1,0),
('204',0,0,20,1,0),
('205',0,1,35,1,0),
('206',1,0,12,1,0),
('207',1,0,4,1,0),
('208',1,1,3,1,0),
('209',1,0,3,1,0),
('306',2,1,2,1,0),
('301',0,0,60,2,0),
('303',0,1,35,2,0),
('332',1,0,5,2,0),
('333',1,0,4,2,0),
('334',1,0,4,2,0),
('220',2,0,30,3,0),
('221',0,0,40,3,0),
('224',2,1,3,3,0),
('225',1,0,3,3,0),
('227',2,0,3,3,0),
('136',0,1,45,4,0),
('140',0,0,25,4,0),
('166',1,0,4,4,0),
('167',1,1,5,4,0),
('168',1,0,3,4,0);
