--Location inserts
insert into "LOCATION" (location_id,state,city,zipcode)
VALUES(1,'VA','Reston','20190');

insert into "LOCATION" (location_id,state,city,zipcode)
VALUES(2,'FL','Tampa','33620');

insert into "LOCATION" (location_id,state,city,zipcode)
VALUES(3,'TX','Reston','20190');

--Building Inserts
insert into "BUILDING" (building_id,city,street_address, location_location_id)
VALUES(1,'Reston','11730 Plaza America Dr',1),
(2,'Tampa','4202 E Fowler Ave',2),
(3,'Tampa','USF Apple Dr',2),
(4,'Arlington','701 S Nedderman Dr',3);

--Room Inserts
insert into "ROOM" (room_id,name,type,capacity,building_building_id)
VALUES(1,'201',1,30,1),
(2,'202',1,45,1),
(3,'203',1,30,1),
(4,'204',1,20,1),
(5,'205',1,35,1),
(6,'206',2,12,1),
(7,'207',2,4,1),
(8,'208',2,3,1),
(9,'209',2,3,1),
(10,'306',3,2,1),
(11,'301',1,60,2),
(12,'303',1,35,2),
(13,'332',2,5,2),
(14,'333',2,4,2),
(15,'334',2,4,2),
(16,'220',1,30,3),
(17,'221',1,40,3),
(18,'224',3,3,3),
(19,'225',2,3,3),
(20,'227',2,3,3),
(21,'136',1,45,4),
(22,'140',1,25,4),
(23,'166',2,4,4),
(24,'167',2,5,4),
(25,'168',2,3,4);

