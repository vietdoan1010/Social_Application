

INSERT INTO db_socialapp.users(id,avatar, created_at, date_of_birth, email, enable, first_name, gender, last_name, password, phone_number, updated_at, user_name)
VALUES ('133233','hinhanh', '2023-09-25', '2001-10-10', 'admin1111@gmail.com', true, 'viet', false, 'doan', '1234567890', '623458342576', '2023-09-25', 'admin1112');


INSERT INTO db_socialapp.users(id,avatar, created_at, date_of_birth, email, enable, first_name, gender, last_name, password, phone_number, updated_at, user_name)
VALUES ('123445','hinhanh', '2023-09-25', '2001-10-10', 'admin001@gmail.com', true, 'viet', false, 'doan', 'admin001', '0001', '2023-09-25', 'admin001');


INSERT INTO db_socialapp.users(id,avatar, created_at, date_of_birth, email, enable, first_name, gender, last_name, password, phone_number, updated_at, user_name, roles)
VALUES ('123446','hinhanh', '2023-09-25', '2001-10-10', 'user3@gmail.com', true, 'viet', false, 'doan', 'user3', '0003', '2023-09-25', 'user3' , 'ADMIN')

select * from db_socialapp.follows where user_id = '4c8aa85b-4159-4e0f-91e5-ac2fdd23dafc'

ALTER TABLE follow ADD created_at datetime(6);

select id from follows where user_id ='4c8aa85b-4159-4e0f-91e5-ac2fdd23dafc' and following_user_id = '6a026f64-c3d9-4ecd-b379-b7eab3af2dff'