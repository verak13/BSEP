INSERT INTO authority (name) VALUES ('ROLE_SUPERADMIN');

INSERT INTO users (id, first_name, last_name, email, password) VALUES (1, 'Jane', 'Smith', 'superadmin@admin.com', '$2y$12$.PWVvztLIA1VBvOcpykig.CZPr78dwT3mVrtCda8YjjyNOas4P6j2');
insert into user_authority (user_id, authority_id) values (1, 1);

insert into requests (common_name, country_name, organization, organization_unit_name, state_name, locality_name, email, user_id) values ('cn', 'co', 'org', 'orgU', 'sn', 'ln', 'a@gmail.com', 1);
insert into requests (common_name, country_name, organization, organization_unit_name, state_name, locality_name, email, user_id) values ('cn2', 'co', 'org', 'orgU', 'sn', 'ln', 'b@gmail.com', 2);