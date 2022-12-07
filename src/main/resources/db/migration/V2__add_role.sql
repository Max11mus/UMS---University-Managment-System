ALTER TABLE ums.student ADD user_role varchar(20) NOT NULL DEFAULT 'ROLE_STUDENT';

ALTER TABLE ums.teacher ADD user_role varchar(20) NOT NULL DEFAULT 'ROLE_TEACHER';
