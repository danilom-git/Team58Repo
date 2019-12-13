insert into clinic (name, country, city, address) values ('Clinic_A', 'Country_A', 'City_A', 'Street_A');
insert into clinic (name, country, city, address) values ('Clinic_B', 'Country_A', 'City_B', 'Street_A');

insert into hall (name,number , clinic_id) values ('Hall_A','101', 1);
insert into hall (name,number , clinic_id) values ('Hall_B','102', 1);
insert into hall (name,number , clinic_id) values ('Hall_C','103', 1);
insert into hall (name,number , clinic_id) values ('Hall_A','201', 2);
insert into hall (name,number , clinic_id) values ('Hall_B','202', 2);
insert into hall (name,number , clinic_id) values ('Hall_C','203', 2);

insert into doctor (name, last_name, working_time, clinic_id, email, password, last_password_reset_date, authority_id) values ('Name_A', 'Last_Name_A', 5, 1,
    'doctor01@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), '2');
insert into doctor (name, last_name, working_time, clinic_id, email, password, last_password_reset_date, authority_id) values ('Name_B', 'Last_Name_B', 6, 1,
    'doctor02@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), '2');
insert into doctor (name, last_name, working_time, clinic_id, email, password, last_password_reset_date, authority_id) values ('Name_C', 'Last_Name_B', 7, 2,
    'doctor03@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), '2');

insert into clinic_admin (email, password, name, last_name, authority_id, last_password_reset_date) values
    ('clinicAdmin01@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', 'Name_A', 'Last_Name_A', 3, to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'));

insert into checkup_type (name) values ('Checkup_Type_A');
insert into checkup_type (name) values ('Checkup_Type_B');
insert into checkup_type (name) values ('Checkup_Type_C');

insert into authority (name) values ('ROLE_PATIENT');
insert into authority (name) values ('ROLE_DOCTOR');
insert into authority (name) values ('ROLE_CLINIC_ADMIN');

insert into patient (email, password, health_insurance_id, name, last_name, country, city, address, phone_number, last_password_reset_date, authority_id) values
('patient01@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', '1', 'Patient_A', 'Last_A',
 'Country_A', 'City_A', 'Address_A', '0651', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), 1);

insert into patient (email, password, health_insurance_id, name, last_name, country, city, address, phone_number, last_password_reset_date, authority_id) values
('patient02@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', '2', 'Patient_B', 'Last_B',
 'Country_A', 'City_A', 'Address_B', '0652', to_timestamp('2019 11 10 7:00', 'YYYY MM DD HH24 MI'), 1);

insert into patient (email, password, health_insurance_id, name, last_name, country, city, address, phone_number, last_password_reset_date, authority_id) values
('patient03@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', '3', 'Patient_C', 'Last_C',
 'Country_A', 'City_A', 'Address_C', '0653', to_timestamp('2019 12 1 7:00', 'YYYY MM DD HH24 MI'), 1);

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
    (to_timestamp('2019 12 30 8:00', 'YYYY MM DD HH24 MI'), to_timestamp('2019 12 30 10:00', 'YYYY MM DD HH24 MI'), 2, 2, 1, 1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id) values
    (to_timestamp('2019 12 30 12:00', 'YYYY MM DD HH24 MI'), to_timestamp('2019 12 30 14:00', 'YYYY MM DD HH24 MI'), 2, 2, 1, 1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id) values
    (to_timestamp('2019 12 31 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2019 12 31 13:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1);

insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 1, 250);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 2, 300);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 3, 350);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 1, 400);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 2, 450);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 3, 550);




