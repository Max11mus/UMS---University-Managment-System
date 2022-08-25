SET REFERENTIAL_INTEGRITY false;

TRUNCATE TABLE ums.group_table;
TRUNCATE TABLE ums.lecture;
TRUNCATE TABLE ums.location_table;
TRUNCATE TABLE ums.student;
TRUNCATE TABLE ums.subject;
TRUNCATE TABLE ums.teacher;
TRUNCATE TABLE ums.time_table_unit;
TRUNCATE TABLE ums.timetableunit_groups;

SET REFERENTIAL_INTEGRITY true;