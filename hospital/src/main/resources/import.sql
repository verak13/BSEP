CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');

INSERT INTO users (id, first_name, last_name, email, password, last_login) VALUES (1, 'John', 'Smith', 'admin@gmail.com', '$2y$12$.PWVvztLIA1VBvOcpykig.CZPr78dwT3mVrtCda8YjjyNOas4P6j2', '2020-04-11');
insert into user_authority (user_id, authority_id) values (1, 1);

insert into patients (id, jmbg, first_name, last_name, birth_date, height, weight, gender, blood_type) VALUES (2, 'q6yxgJQ/7gs79aQyFB7saQ==', 'tzWUbzeJ6ehmv9VauNu04A==', '9VsnQUjFH/aY/lz9KuZ2iw==', '2020-06-10 12:00:00', 180.0, 75.0, 'MALE', 'A');


insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 12 , 739 , 10 , '2021-04-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 12 , 500 , 100 , '2021-04-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 0 , 739 , 80 , '2021-05-11' , 0 , 93 , 0 , 0 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 7 , 11 , 3 , '2021-05-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 33 , 11 , 12 , '2021-06-11' , 1 , 93 , 30 , 6 , 14333 , 243 , 16653 );
insert into report(alarms_triggered , application_log_count , brute_force_alarms_triggered , date , error_log_alarms_triggered , error_log_count , inactive_user_alarms_triggered , ip_alarms_triggered , login_error_log_count , login_log_count , total_log_count ) values ( 1 , 11 , 40 , '2021-06-11' , 1 , 93 , 5 , 6 , 14333 , 243 , 16653 );

