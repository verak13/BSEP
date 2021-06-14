CREATE EXTENSION IF NOT EXISTS pgcrypto;


INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');

INSERT INTO users (first_name, last_name, email, password, last_login) VALUES ( 'John', 'Smith', 'admin@gmail.com', '$2y$12$.PWVvztLIA1VBvOcpykig.CZPr78dwT3mVrtCda8YjjyNOas4P6j2', '2020-04-11');
insert into user_authority (user_id, authority_id) values (1, 1);


insert into patients (jmbg, first_name, last_name, birth_date, height, weight, gender, blood_type) VALUES ( 'q6yxgJQ/7gs79aQyFB7saQ==', 'tzWUbzeJ6ehmv9VauNu04A==', '9VsnQUjFH/aY/lz9KuZ2iw==', '1998-06-10 12:00:00', 180.1, 81.6, 'MALE', 'B');
insert into patients ( jmbg, first_name, last_name, birth_date, height, weight, gender, blood_type) VALUES ('vXuP0385ZlLm/uKHum/Jjw', 'G7LY9xKWqUTaDhwJzAb/8Q', '9qMBEwMwMCNvazaOoOdJww==', '1959-02-05 12:00:00', 169.3, 75.2, 'MALE', 'AB');

insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 12 , 739 , 10 , '2021-04-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 12 , 500 , 100 , '2021-04-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 0 , 739 , 80 , '2021-05-11' , 0 , 93 , 0 , 0 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 7 , 11 , 3 , '2021-05-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 33 , 11 , 12 , '2021-06-11' , 1 , 93 , 30 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 1 , 11 , 40 , '2021-06-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );

insert into messages(patient_id, blood_pressure_diastolic, blood_pressure_systolic, body_temperature, pulse_raye, respiration_rate, timestamp) values (1, 120.0, 90.0, 36.9, 80.1, 15.0, '2021-06-10 12:00:00');
insert into messages(patient_id, blood_pressure_diastolic, blood_pressure_systolic, body_temperature, pulse_raye, respiration_rate, timestamp) values (1, 123.0, 93.0, 37.7, 80.4, 14.0, '2021-06-10 12:15:00');
insert into messages(patient_id, blood_pressure_diastolic, blood_pressure_systolic, body_temperature, pulse_raye, respiration_rate, timestamp) values (2, 122.0, 92.0, 36.1, 90.1, 16.0, '2021-06-10 12:00:00');
insert into messages(patient_id, blood_pressure_diastolic, blood_pressure_systolic, body_temperature, pulse_raye, respiration_rate, timestamp) values (2, 130.0, 90.0, 36.4, 85.0, 15.0, '2021-06-10 12:00:00');
insert into messages(patient_id, blood_pressure_diastolic, blood_pressure_systolic, body_temperature, pulse_raye, respiration_rate, timestamp) values (1, 119.0, 87.0, 36.2, 79.0, 16.0, '2021-06-10 12:00:00');
insert into messages(patient_id, blood_pressure_diastolic, blood_pressure_systolic, body_temperature, pulse_raye, respiration_rate, timestamp) values (2, 121.0, 91.0, 36.6, 91.0, 14.0, '2021-06-10 12:00:00');
insert into messages(patient_id, blood_pressure_diastolic, blood_pressure_systolic, body_temperature, pulse_raye, respiration_rate, timestamp) values (1, 120.0, 91.0, 37.4, 83.0, 15.0, '2021-06-10 12:30:00');

insert into brute_force_alarm(attempts, username) values (100, 'doctor1@gmail.com');
insert into brute_force_alarm(attempts, username) values (100, 'doctor2@gmail.com');

insert into custom_log_alarm(date, error_msg) values ('2021-06-10 13:00:00', 'Something is wrong!');
insert into custom_log_alarm(date, error_msg) values ('2021-06-10 13:05:00', 'Something is wrong again!');

insert into blacklisted_ip_alarm(date, ip, user_email) values ('2021-06-10 13:05:00', '20.20.20.23', 'hacker1@gmail.com');
insert into blacklisted_ip_alarm(date, ip, user_email) values ('2021-06-10 13:06:00', '20.20.20.25', 'hacker1@gmail.com');
insert into blacklisted_ip_alarm(date, ip, user_email) values ('2021-06-10 13:07:00', '20.20.20.23', 'hacker2@gmail.com');
insert into blacklisted_ip_alarm(date, ip, user_email) values ('2021-06-10 13:08:00', '20.20.20.23', 'hacker2@gmail.com');

insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:08:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:09:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:09:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:10:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:07:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:06:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:05:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:04:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:03:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:01:00', 'Unsuccessful login');
insert into error_log_alarm(date, error_msg) values ('2021-06-10 13:02:00', 'Unsuccessful login');

insert into inactive_user_alarm(days_inactive, user_email) values (90, 'inactiveuser@gmail.com');

insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 1, 'bodyTemperature', 40.1, null);
insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 1, 'pulseRate', 100.0, null);
insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 2, 'bloodPressure', 93.1, 109.8);
insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 2, 'Custom Rule Alarm!', null, null);
insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 1, 'bodyTemperature', 40.1, null);
 insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 1, 'pulseRate', 100.0, null);
 insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 2, 'bloodPressure', 93.1, 109.8);
 insert into message_alarms(date, patient_id, variable, value, other_value) values ('2021-06-10 13:08:00', 2, 'Custom Rule Alarm!', null, null);



