ALTER TABLE person_info DROP COLUMN time_zone;

ALTER TABLE person_info ADD time_zone VARCHAR(255) NOT NULL;