CREATE TABLE IF NOT exists ums.role_ums (
	role_id uuid,
	role_name varchar(128) NOT NULL,
	CONSTRAINT pk_role_ums PRIMARY KEY (role_id)
);


CREATE TABLE IF NOT exists ums.user_ums (
	user_id uuid,
	login varchar(128) NOT NULL,
	hashed_password varchar(128) NOT NULL,
	role_id uuid NOT NULL DEFAULT '00000000-0000-0000-0000-000000000000',
	CONSTRAINT pk_user_ums  PRIMARY KEY (user_id),
	CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES ums.role_ums(role_id)
);

INSERT INTO ums.role_ums
(role_id, role_name )
VALUES('00000000-0000-0000-0000-000000000000'::uuid, 'ROLE_STUDENT');

INSERT INTO ums.role_ums
(role_id, role_name )
VALUES('30e2582a-ddc8-4358-9bef-e43c874c2093'::uuid, 'ROLE_TEACHER');

INSERT INTO ums.role_ums
(role_id, role_name )
VALUES('cecde5a4-d236-4ed8-82ff-0adc47d6c8c3'::uuid, 'ROLE_ADMIN');

INSERT INTO ums.user_ums
(user_id,
 login,
 hashed_password,
 role_id)
VALUES
('449eb201-a97e-4232-934d-306478b0a039'::uuid,
'admin',
'$2a$10$h.9Nn/VpqXZ4kfzFqtVDu.jpn5bYqjColLxEWuQ2qgr5L5tgfqyEq',
'cecde5a4-d236-4ed8-82ff-0adc47d6c8c3'::uuid);

INSERT INTO ums.user_ums
(user_id,
 login,
 hashed_password,
 role_id)
VALUES
('a7151664-7f38-4265-9f53-16e8921d838e'::uuid,
'teacher',
'$2a$10$17jBoGqJsSJDQQIEoQe2CO5LdJjqazWTTePosxKxx21QibXO8ZjmK',
'30e2582a-ddc8-4358-9bef-e43c874c2093'::uuid);

INSERT INTO ums.user_ums
(user_id,
 login,
 hashed_password)
VALUES
('12611b1e-b277-4e64-8ff3-243a5d6fbc2d'::uuid,
'student',
'$2a$10$doVTJtbKCPFMXANFGeE89OQjnPPjKPfKuMhaz5zl9htepXwebJ7D6');