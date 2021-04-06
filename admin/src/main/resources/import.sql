INSERT INTO authority (name) VALUES ('ROLE_SUPERADMIN');

INSERT INTO users (id, first_name, last_name, email, password) VALUES (1, 'Jane', 'Smith', 'superadmin@admin.com', '$2y$12$.PWVvztLIA1VBvOcpykig.CZPr78dwT3mVrtCda8YjjyNOas4P6j2');
insert into user_authority (user_id, authority_id) values (1, 1);