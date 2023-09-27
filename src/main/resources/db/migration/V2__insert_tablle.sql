#
# INSERT INTO employees(name, role, project) VALUES('user1', 'Developer', 'Flyway-Project1');
# INSERT INTO employees(name, role, project) VALUES('user2', 'SME', 'Flyway-Project2');

INSERT INTO db_socialapp.users(id,avatar, created_at, date_of_birth, email, enable, first_name, gender, last_name, password, phone_number, role, updated_at, user_name)
VALUES (123,'hinhanh', '2023-09-25', '2001-10-10', 'viet@gmail.com', true, 'viet', false, 'doan', '1234567890', '0987654321', 'admin', '2023-09-25', 'vietdoan')