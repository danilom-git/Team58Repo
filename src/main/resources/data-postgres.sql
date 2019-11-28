insert into clinic (name, country, city, address) values ('Clinic_A', 'Country_A', 'City_A', 'Street_A');
insert into clinic (name, country, city, address) values ('Clinic_B', 'Country_A', 'City_B', 'Street_A');

insert into hall (name, clinic_id) values ('Hall_A', 1);
insert into hall (name, clinic_id) values ('Hall_B', 1);
insert into hall (name, clinic_id) values ('Hall_C', 1);
insert into hall (name, clinic_id) values ('Hall_A', 2);
insert into hall (name, clinic_id) values ('Hall_B', 2);
insert into hall (name, clinic_id) values ('Hall_C', 2);

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

insert into doctor_checkup_types (doctor_id, checkup_types_id) values (1, 1);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (1, 2);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (2, 2);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (3, 2);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (3, 3);

-- MM (01 - 12)
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id) values
    (to_timestamp('2019 12 30 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2019 12 30 15:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id) values
    (to_timestamp('2019 12 30 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2019 12 30 14:00', 'YYYY MM DD HH24 MI'), 2, 2, 1, 1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id) values
    (to_timestamp('2019 12 31 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2019 12 31 13:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1);

insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 1, 250);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 2, 300);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 3, 350);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 1, 400);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 2, 450);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 3, 550);
