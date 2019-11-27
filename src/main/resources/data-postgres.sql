insert into clinic (name, country, city, address) values ('Clinic_A', 'Country_A', 'City_A', 'Street_A');
insert into clinic (name, country, city, address) values ('Clinic_B', 'Country_A', 'City_B', 'Street_A');

insert into doctor (name, last_name, clinic_id) values ('Doctor_Name_A', 'Doctor_Last_Name_A', 1);
insert into doctor (name, last_name, clinic_id) values ('Doctor_Name_B', 'Doctor_Last_Name_B', 1);
insert into doctor (name, last_name, clinic_id) values ('Doctor_Name_C', 'Doctor_Last_Name_B', 2);

insert into checkup_type (name, available) values ('Checkup_Type_A', true);
insert into checkup_type (name, available) values ('Checkup_Type_B', true);
insert into checkup_type (name, available) values ('Checkup_Type_C', false);

