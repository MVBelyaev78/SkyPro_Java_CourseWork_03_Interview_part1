-- liquibase formatted sql

-- changeset mikhail.belyaev:1

create table public.tbl_notification_task (
	id_notification_task serial,
	nn_chat              bigint not null,
	vl_notification_task varchar(100) not null,
	dt_notification_task timestamp default current_timestamp,
	constraint notification_task_pk primary key (id_notification_task)
);
