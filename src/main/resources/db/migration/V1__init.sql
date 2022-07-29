CREATE SCHEMA IF NOT EXISTS public;

--- Group
CREATE TABLE public."group" (
	group_id uuid,
	"name" varchar(128) NOT NULL,
	CONSTRAINT pk_group PRIMARY KEY (group_id)
);

--- Location
CREATE TABLE public."location" (
	location_id uuid,
	address varchar(255) NOT NULL,
	CONSTRAINT pk_location PRIMARY KEY (location_id)
);

--- Person_info
CREATE TABLE public.person_info (
	personinfo_id uuid,
	"name" varchar(20) NOT NULL,
	surname varchar(20) NOT NULL,
	birth_date date NOT NULL,
	login varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	avatar_path varchar(1024),
	hashed_password varchar(255) NOT NULL,
	time_zone varchar(255),
	CONSTRAINT pk_person_info PRIMARY KEY (personinfo_id),
	CONSTRAINT uc_person_info_email UNIQUE (email),
	CONSTRAINT uc_person_info_login UNIQUE (login)
);

--- Subject
CREATE TABLE public.subject (
	subject_id uuid,
	"name" varchar(255) NOT NULL,
	description varchar(1024),
	CONSTRAINT pk_subject PRIMARY KEY (subject_id)
);

--- Student
CREATE TABLE public.student (
	student_id uuid,
	person_id uuid,
	enroll_date date NOT NULL,
	dropout_date date,
	group_id uuid,
	CONSTRAINT pk_student PRIMARY KEY (student_id),
	CONSTRAINT fk_student_on_group FOREIGN KEY (group_id) REFERENCES public."group"(group_id),
	CONSTRAINT fk_student_on_person FOREIGN KEY (person_id) REFERENCES public.person_info(personinfo_id)
);

--- Teacher
CREATE TABLE public.teacher (
	teacher_id uuid,
	person_id uuid,
	academic_degree varchar(255) NOT NULL,
	employment_date date NOT NULL,
	CONSTRAINT pk_teacher PRIMARY KEY (teacher_id),
	CONSTRAINT fk_teacher_on_person FOREIGN KEY (person_id) REFERENCES public.person_info(personinfo_id)
);

--- Lecture
CREATE TABLE public.lecture (
	lecture_id uuid,
	topic varchar(255) NOT NULL,
	description varchar(1024),
	teacher_id uuid NOT NULL,
	subject_id uuid NOT NULL,
	CONSTRAINT pk_lecture PRIMARY KEY (lecture_id),
	CONSTRAINT fk_lecture_on_subject FOREIGN KEY (subject_id) REFERENCES public.subject(subject_id),
	CONSTRAINT fk_lecture_on_teacher FOREIGN KEY (teacher_id) REFERENCES public.teacher(teacher_id)
);

--- Time_table_unit definition
CREATE TABLE public.time_table_unit (
	time_table_unit_id uuid,
	location_id uuid,
	lecture_id uuid,
	"begin" timestamp,
	"end" timestamp,
	CONSTRAINT pk_time_table_unit PRIMARY KEY (time_table_unit_id),
	CONSTRAINT fk_time_table_unit_on_lecture FOREIGN KEY (lecture_id) REFERENCES public.lecture(lecture_id),
	CONSTRAINT fk_time_table_unit_on_location FOREIGN KEY (location_id) REFERENCES public."location"(location_id)
);

--- time_table_unit_groups;
CREATE TABLE public.time_table_unit_groups (
	group_id uuid,
	time_table_unit_id uuid,
	CONSTRAINT fk_timtabunigro_on_group FOREIGN KEY (group_id) REFERENCES public."group"(group_id),
	CONSTRAINT fk_timtabunigro_on_time_table_unit FOREIGN KEY (time_table_unit_id) REFERENCES public.time_table_unit(time_table_unit_id)
);