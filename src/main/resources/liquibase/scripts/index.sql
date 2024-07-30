-- liquibase formatted sql

-- changeset mariiam:1

CREATE TABLE notification_task (
	id bigserial PRIMARY KEY,
	chat_id int8 NOT NULL,
	message VARCHAR(255) NOT NULL,
	date_time timestamptz NOT NULL
);