insert into clinic (name, country, city, address,x_coord,y_coord) values ('Clinic_A', 'Country_A', 'City_A', 'Street_A',45.29,19.83);
insert into clinic (name, country, city, address,x_coord,y_coord) values ('Clinic_B', 'Country_A', 'City_B', 'Street_A',45.10,19.75);

insert into hall (name,number , clinic_id) values ('Hall_A','101', 1);
insert into hall (name,number , clinic_id) values ('Hall_B','102', 1);
insert into hall (name,number , clinic_id) values ('Hall_C','103', 1);
insert into hall (name,number , clinic_id) values ('Hall_A','201', 2);
insert into hall (name,number , clinic_id) values ('Hall_B','202', 2);
insert into hall (name,number , clinic_id) values ('Hall_C','203', 2);

insert into authority (name) values ('ROLE_PATIENT');
insert into authority (name) values ('ROLE_DOCTOR');
insert into authority (name) values ('ROLE_CLINIC_ADMIN');
insert into authority (name) values ('ROLE_CLINIC_CENTER_ADMIN');

insert into clinic_center_admin (email, last_password_reset_date, password, authority_id) values ('clinicCenterAdmin01@somemail.com',
                                                                                                  to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', 4);

insert into doctor (name, last_name, working_time, clinic_id, email, password, last_password_reset_date, authority_id,first_password_changed,rating) values ('Name_A', 'Last_Name_A', 5, 1,
                                                                                                                                                             'isaprojektovanjeUsers@gmail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), '2',true,7);
insert into doctor (name, last_name, working_time, clinic_id, email, password, last_password_reset_date, authority_id,first_password_changed,rating) values ('Name_B', 'Last_Name_B', 6, 1,
                                                                                                                                                             'doctor02@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), '2',true,10);
insert into doctor (name, last_name, working_time, clinic_id, email, password, last_password_reset_date, authority_id,first_password_changed,rating) values ('Name_C', 'Last_Name_B', 7, 2,
                                                                                                                                                             'doctor03@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), '2',true,5);

insert into clinic_admin (email, password, name, last_name, authority_id, last_password_reset_date, clinic_id,first_password_changed) values
('clinicAdmin01@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', 'Name_A', 'Last_Name_A', 3,
 to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), 1,true);

insert into checkup_type (name) values ('Checkup_Type_A');
insert into checkup_type (name) values ('Checkup_Type_B');
insert into checkup_type (name) values ('Checkup_Type_C');
insert into checkup_type (name) values ('Checkup_Type_D');

insert into medical_record (age, blood_type, diopter, height, weight) values (20, 'A+', 0, 185, 93);
insert into patient (email, password, health_insurance_id, name, last_name, country, city, address, phone_number, last_password_reset_date, authority_id, medical_record_id) values
('patient01@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', '1', 'Patient_A', 'Last_A',
 'Country_A', 'City_A', 'Address_A', '0651', to_timestamp('2019 12 10 7:00', 'YYYY MM DD HH24 MI'), 1, 1);

insert into medical_record (age, blood_type, diopter, height, weight) values (46, 'B-', 1.25, 171, 79);
insert into patient (email, password, health_insurance_id, name, last_name, country, city, address, phone_number, last_password_reset_date, authority_id, medical_record_id) values
('patient02@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', '2', 'Patient_B', 'Last_B',
 'Country_A', 'City_A', 'Address_B', '0652', to_timestamp('2019 11 10 7:00', 'YYYY MM DD HH24 MI'), 1, 2);

insert into medical_record (age, blood_type, diopter, height, weight) values (23, 'AB+', -0.5, 184, 86);
insert into patient (email, password, health_insurance_id, name, last_name, country, city, address, phone_number, last_password_reset_date, authority_id, medical_record_id) values
('patient03@somemail.com', '$2a$10$j8XGgt8oCja0ioWD/JK0C.iL22NKpW3n40THQBnjxO7E4h.qyT0Fu', '3', 'Patient_C', 'Last_C',
 'Country_A', 'City_A', 'Address_C', '0653', to_timestamp('2019 12 1 7:00', 'YYYY MM DD HH24 MI'), 1, 3);

insert into clinic_rating (patient_id, clinic_id, rating) values (1, 1, 4);
insert into clinic_rating (patient_id, clinic_id, rating) values (2, 1, 4);
insert into clinic_rating (patient_id, clinic_id, rating) values (3, 1, 3);
insert into clinic_rating (patient_id, clinic_id, rating) values (1, 2, 5);
insert into clinic_rating (patient_id, clinic_id, rating) values (2, 2, 2);

insert into doctor_rating(clinic_id,doctor_id,rating) values (1, 1, 5);
insert into doctor_rating(clinic_id,doctor_id,rating) values (1, 2, 4);
insert into doctor_rating(clinic_id,doctor_id,rating) values (2, 3, 10);

insert into doctor_checkup_types (doctor_id, checkup_types_id) values (1, 1);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (1, 2);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (2, 2);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (3, 2);
insert into doctor_checkup_types (doctor_id, checkup_types_id) values (3, 3);

-- MM (01 - 12)
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 15 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 15 10:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 15 12:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 15 13:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 15 14:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 15 16:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 15 8:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 15 12:00', 'YYYY MM DD HH24 MI'), 2, 2, 2, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 16 12:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 16 14:00', 'YYYY MM DD HH24 MI'), 2, 2, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 16 10:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 16 13:00', 'YYYY MM DD HH24 MI'), 1, 1, 2, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 9 07:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 9 23:30', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 8 10:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 8 11:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 8 12:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 8 15:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 7 10:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 7 11:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 7 12:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 7 14:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 2 6 10:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 6 11:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 1 7 12:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 1 7 14:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);
insert into checkup (start_date, end_date, checkup_type_id, doctor_id, hall_id, patient_id,clinic_id) values
(to_timestamp('2020 1 6 10:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 1 6 11:00', 'YYYY MM DD HH24 MI'), 1, 1, 1, 1,1);



insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 1, 250);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 2, 300);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (1, 3, 350);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 1, 400);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 2, 450);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 3, 550);
insert into clinic_checkup_type (clinic_id, checkup_type_id, price) values (2, 4, 1000);


insert into one_click (duration,end_time,price,start_time,checkup_type_id,clinic_id,doctor_id,hall_id)
values ('1h',to_timestamp('2020 2 25 13:00', 'YYYY MM DD HH24 MI'),1000,to_timestamp('2020 2 25 14:00', 'YYYY MM DD HH24 MI'),1,1,1,1);

insert into one_click (start_time, end_time, duration, clinic_id, hall_id, doctor_id, checkup_type_id, price) values
(to_timestamp('2020 2 26 13:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 26 14:00', 'YYYY MM DD HH24 MI'), '1h', 1, 2, 2, 2, 100);
insert into one_click (start_time, end_time, duration, clinic_id, hall_id, doctor_id, checkup_type_id, price) values
(to_timestamp('2020 2 26 14:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 26 15:00', 'YYYY MM DD HH24 MI'), '1h', 1, 2, 2, 2, 100);
insert into one_click (start_time, end_time, duration, clinic_id, hall_id, doctor_id, checkup_type_id, price) values
(to_timestamp('2020 2 27 10:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 27 11:00', 'YYYY MM DD HH24 MI'), '1h', 2, 1, 3, 2, 100);
insert into one_click (start_time, end_time, duration, clinic_id, hall_id, doctor_id, checkup_type_id, price) values
(to_timestamp('2020 2 27 12:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 27 13:00', 'YYYY MM DD HH24 MI'), '1h', 2, 2, 3, 3, 100);

insert into absence_request (answered,start_date,end_date,type,clinic_id,doctor_id) values
(false,to_timestamp('2020 2 16','YYYY MM DD'),to_timestamp('2020 2 18','YYYY MM DD'),'Annual leave',1,1);

insert into checkup_request(date_added,start_date,end_date,doctor_id,checkup_type_id,clinic_id,patient_id,on_wait) values ( to_timestamp('2020 2 6 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 18 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 18 08:00', 'YYYY MM DD HH24 MI') ,1,1,1,1,false);
insert into checkup_request(date_added,start_date,end_date,doctor_id,checkup_type_id,clinic_id,patient_id,on_wait) values ( to_timestamp('2020 2 10 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 15 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 15 08:00', 'YYYY MM DD HH24 MI') ,1,1,1,1,false);
insert into checkup_request(date_added,start_date,end_date,doctor_id,checkup_type_id,clinic_id,patient_id,on_wait) values ( to_timestamp('2020 2 10 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 20 7:00', 'YYYY MM DD HH24 MI'), to_timestamp('2020 2 20 08:00', 'YYYY MM DD HH24 MI') ,1,1,1,1,false);
