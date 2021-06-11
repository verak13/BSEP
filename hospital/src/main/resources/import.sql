CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');

INSERT INTO users (id, first_name, last_name, email, password) VALUES (1, 'John', 'Smith', 'admin@gmail.com', '$2y$12$.PWVvztLIA1VBvOcpykig.CZPr78dwT3mVrtCda8YjjyNOas4P6j2');
insert into user_authority (user_id, authority_id) values (1, 1);

insert into patients (id, jmbg, first_name, last_name, birth_date, height, weight, gender, blood_type) VALUES (2, 'q6yxgJQ/7gs79aQyFB7saQ==', 'tzWUbzeJ6ehmv9VauNu04A==', '9VsnQUjFH/aY/lz9KuZ2iw==', '2020-06-10 12:00:00', 180.0, 75.0, 'MALE', 'A');