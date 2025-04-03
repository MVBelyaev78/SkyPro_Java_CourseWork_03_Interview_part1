-- liquibase formatted sql

-- changeset mikhail.belyaev:1

create table public.tbl_chat (
	kd_chat integer,
	nm_chat varchar(20) not null,
	constraint chat_pk primary key (kd_chat)
);

insert into public.tbl_chat (kd_chat, nm_chat) values (1, 'telergam');

create table public.tbl_notification_task (
	id_notification_task serial,
	kd_chat              integer not null,
	vl_notification_task varchar(100) not null,
	dt_notification_task timestamp default current_timestamp,
	constraint notification_task_pk primary key (id_notification_task),
	constraint notification_task_fk$1 foreign key (kd_chat) references public.tbl_chat(kd_chat)
);

create index notification_task_i$1 on public.tbl_notification_task (kd_chat);
