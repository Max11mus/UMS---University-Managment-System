
CREATE TABLE "group" (
  group_id UUID NOT NULL,
   name VARCHAR(128) NOT NULL,
   CONSTRAINT pk_group PRIMARY KEY (group_id)
);

CREATE TABLE lecture (
  lecture_id UUID NOT NULL,
   topic VARCHAR(255) NOT NULL,
   description VARCHAR(1024) NOT NULL,
   teacher_id UUID NOT NULL,
   subject_id UUID NOT NULL,
   CONSTRAINT pk_lecture PRIMARY KEY (lecture_id)
);

CREATE TABLE location (
  location_id UUID NOT NULL,
   address VARCHAR(255) NOT NULL,
   CONSTRAINT pk_location PRIMARY KEY (location_id)
);

CREATE TABLE person_info (
  personinfo_id UUID NOT NULL,
   name VARCHAR(20) NOT NULL,
   surname VARCHAR(20) NOT NULL,
   birth_date date NOT NULL,
   time_zone BYTEA NOT NULL,
   login VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   avatar_path VARCHAR(1024) NOT NULL,
   hashed_password VARCHAR(255) NOT NULL,
   CONSTRAINT pk_person_info PRIMARY KEY (personinfo_id)
);

CREATE TABLE student (
  student_id UUID NOT NULL,
   person_id UUID,
   enroll_date date,
   dropout_date date,
   group_id UUID,
   CONSTRAINT pk_student PRIMARY KEY (student_id)
);

CREATE TABLE subject (
  subject_id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   description VARCHAR(1024) NOT NULL,
   CONSTRAINT pk_subject PRIMARY KEY (subject_id)
);

CREATE TABLE teacher (
  teacher_id UUID NOT NULL,
   person_id UUID,
   academic_degree VARCHAR(255) NOT NULL,
   employment_date date NOT NULL,
   CONSTRAINT pk_teacher PRIMARY KEY (teacher_id)
);

CREATE TABLE time_table_unit (
  time_table_unit_id UUID NOT NULL,
   location_id UUID NOT NULL,
   lecture_id UUID NOT NULL,
   begin TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   "end" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_time_table_unit PRIMARY KEY (time_table_unit_id)
);

CREATE TABLE time_table_unit_groups (
  group_id UUID NOT NULL,
   time_table_unit_id UUID NOT NULL,
   CONSTRAINT pk_time_table_unit_groups PRIMARY KEY (group_id, time_table_unit_id)
);

ALTER TABLE person_info ADD CONSTRAINT uc_person_info_email UNIQUE (email);

ALTER TABLE person_info ADD CONSTRAINT uc_person_info_login UNIQUE (login);

ALTER TABLE lecture ADD CONSTRAINT FK_LECTURE_ON_SUBJECT FOREIGN KEY (subject_id) REFERENCES subject (subject_id);

ALTER TABLE lecture ADD CONSTRAINT FK_LECTURE_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id);

ALTER TABLE student ADD CONSTRAINT FK_STUDENT_ON_GROUP FOREIGN KEY (group_id) REFERENCES "group" (group_id);

ALTER TABLE student ADD CONSTRAINT FK_STUDENT_ON_PERSON FOREIGN KEY (person_id) REFERENCES person_info (personinfo_id);

ALTER TABLE teacher ADD CONSTRAINT FK_TEACHER_ON_PERSON FOREIGN KEY (person_id) REFERENCES person_info (personinfo_id);

ALTER TABLE time_table_unit ADD CONSTRAINT FK_TIME_TABLE_UNIT_ON_LECTURE FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id);

ALTER TABLE time_table_unit ADD CONSTRAINT FK_TIME_TABLE_UNIT_ON_LOCATION FOREIGN KEY (location_id) REFERENCES location (location_id);

ALTER TABLE time_table_unit_groups ADD CONSTRAINT fk_timtabunigro_on_group FOREIGN KEY (group_id) REFERENCES "group" (group_id);

ALTER TABLE time_table_unit_groups ADD CONSTRAINT fk_timtabunigro_on_time_table_unit FOREIGN KEY (time_table_unit_id) REFERENCES time_table_unit (time_table_unit_id);