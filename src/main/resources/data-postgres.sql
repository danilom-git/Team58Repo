insert into clinic (name, country, city, address) values ('Clinic_A', 'Country_A', 'City_A', 'Street_A');
insert into clinic (name, country, city, address) values ('Clinic_B', 'Country_A', 'City_B', 'Street_A');

insert into doctor (name, last_name, clinic_id) values ('Name_A', 'Last_Name_A', 1);
insert into doctor (name, last_name, clinic_id) values ('Name_B', 'Last_Name_B', 1);
insert into doctor (name, last_name, clinic_id) values ('Name_C', 'Last_Name_B', 2);

insert into checkup_type (name) values ('Checkup_Type_A');
insert into checkup_type (name) values ('Checkup_Type_B');
insert into checkup_type (name) values ('Checkup_Type_C');

insert into patient (name, last_name, email, health_insurance_id, country, city, address, phone_number)
    values ('Name_D', 'Last_Name_D', 'Email_D@email.com', '1', 'Country_A', 'City_A', 'Street_A', '0666');
insert into patient (name, last_name, email, health_insurance_id, country, city, address, phone_number)
    values ('Name_E', 'Last_Name_E', 'Email_E@email.com', '2', 'Country_A', 'City_B', 'Street_B', '0667');
insert into patient (name, last_name, email, health_insurance_id, country, city, address, phone_number)
    values ('Name_F', 'Last_Name_F', 'Email_F@email.com', '3', 'Country_A', 'City_A', 'Street_C', '0668');

insert into clinic_rating (patient_id, clinic_id, rating) values (1, 1, 4);
insert into clinic_rating (patient_id, clinic_id, rating) values (2, 1, 4);
insert into clinic_rating (patient_id, clinic_id, rating) values (3, 1, 3);

insert into clinic_rating (patient_id, clinic_id, rating) values (1, 2, 5);
insert into clinic_rating (patient_id, clinic_id, rating) values (2, 2, 2);

