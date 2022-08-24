
--- Groups
INSERT INTO ums.group_table
(group_id, group_name )
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'BackEnd Developers');
INSERT INTO ums.group_table
(group_id, group_name )
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'FrontEnd Developers');
INSERT INTO ums.group_table
(group_id, group_name )
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'DevOps Engineer');

--- students
INSERT INTO ums.student
(person_id, enroll_date, dropout_date, group_id, person_name, surname, birth_date, login, email, avatar_path, hashed_password, time_zone)
VALUES
('f57e0ffe-6118-44a8-b39d-b2da86b65aff','2022-01-05','2022-07-07','91d2f442-fcd2-4d00-a23c-da207f75242d','Mary','Carpenter','1973-01-11','Mary_Carpenter','Mary_Carpenter@gmail.com','','588b72cc2bb56563a0d7c4724dd6684fea8e4446b6f625a48ec1d17eac113f03','GMT-8:00'),
('8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7','2022-01-05','2022-07-07','80242a81-0ec9-48a4-98ff-90dd7550caf1','Frank','Parker','1985-07-15','f_parker','f.parker@gmail.com','','ff241ff70d46c8c858d2bae7d25ce777ce7ad8224f0a26a7cf890d893a052c08','UTC'),
('c3e47148-adcf-4ee3-81f6-6b79b83a41ca','2022-01-05','2022-07-07','bc2322d6-046b-4b1e-a757-b5cf26eff20a','Kathy','Mizer','1977-08-06','Kathy$Mizer','Kathy$Mizer@gmail.com','','52fd199d620b29429bd44393a78a36b14ab1e607378488187f64e117f698e1d1','GMT+9:00');
