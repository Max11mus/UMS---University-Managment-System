SET TIME ZONE 'UTC';

CREATE SCHEMA IF NOT EXISTS ums;

--- Group
CREATE TABLE ums.group_table (
	group_id uuid,
	group_name varchar(128) NOT NULL,
	CONSTRAINT pk_group PRIMARY KEY (group_id)
);

--- Location
CREATE TABLE ums.location_table (
	location_id uuid,
	address varchar(255) NOT NULL,
	CONSTRAINT pk_location PRIMARY KEY (location_id)
);

--- Subject
CREATE TABLE ums.subject (
	subject_id uuid,
	subject_name  varchar(255) NOT NULL,
	description varchar(1024),
	CONSTRAINT pk_subject PRIMARY KEY (subject_id)
);

--- Student
CREATE TABLE ums.student (
    person_id uuid,
	person_name varchar(20) NOT NULL,
	surname varchar(20) NOT NULL,
	birth_date date NOT NULL,
	login varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	avatar_path varchar(1024),
	hashed_password varchar(255) NOT NULL,
	time_zone varchar(255),
	enroll_date date NOT NULL,
	dropout_date date,
	group_id uuid,
	CONSTRAINT pk_student_id PRIMARY KEY (person_id),
	CONSTRAINT uc_student_info_email UNIQUE (email),
	CONSTRAINT uc_student_info_login UNIQUE (login),
	CONSTRAINT fk_student_on_group FOREIGN KEY (group_id) REFERENCES ums.group_table(group_id)
);

--- Teacher
CREATE TABLE ums.teacher (
	person_id uuid,
    person_name varchar(20) NOT NULL,
    surname varchar(20) NOT NULL,
    birth_date date NOT NULL,
    login varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    avatar_path varchar(1024),
    hashed_password varchar(255) NOT NULL,
    time_zone varchar(255),
	academic_degree varchar(255) NOT NULL,
	employment_date date NOT NULL,
    CONSTRAINT pk_teacher_id PRIMARY KEY (person_id),
    CONSTRAINT uc_teacher_info_email UNIQUE (email),
    CONSTRAINT uc_teacher_info_login UNIQUE (login)
);

--- Lecture
CREATE TABLE ums.lecture (
	lecture_id uuid,
	topic varchar(255) NOT NULL,
	description varchar(1024),
	teacher_id uuid NOT NULL,
	subject_id uuid NOT NULL,
	CONSTRAINT pk_lecture PRIMARY KEY (lecture_id),
	CONSTRAINT fk_lecture_on_subject FOREIGN KEY (subject_id) REFERENCES ums.subject(subject_id),
	CONSTRAINT fk_lecture_on_teacher FOREIGN KEY (teacher_id) REFERENCES ums.teacher(person_id)
);

--- Time_table_unit definition
CREATE TABLE ums.time_table_unit (
	time_table_unit_id uuid,
	location_id uuid,
	lecture_id uuid,
	begin_time timestamp,
	end_time timestamp,
	CONSTRAINT pk_time_table_unit PRIMARY KEY (time_table_unit_id),
	CONSTRAINT fk_time_table_unit_on_lecture FOREIGN KEY (lecture_id) REFERENCES ums.lecture(lecture_id),
	CONSTRAINT fk_time_table_unit_on_location FOREIGN KEY (location_id) REFERENCES ums.location_table(location_id)
);

--- timetableunit_groups;
CREATE TABLE ums.timetableunit_groups (
	group_id uuid,
	time_table_unit_id uuid,
	CONSTRAINT fk_timtabunigro_on_group FOREIGN KEY (group_id) REFERENCES ums.group_table(group_id),
	CONSTRAINT fk_timtabunigro_on_time_table_unit FOREIGN KEY (time_table_unit_id) REFERENCES ums.time_table_unit(time_table_unit_id)
);