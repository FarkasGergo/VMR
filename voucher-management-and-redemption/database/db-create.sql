CREATE DATABASE wecan
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;


CREATE SCHEMA vmr
    AUTHORIZATION postgres;


CREATE TABLE vmr.voucher (
	id integer not null,
	name character varying(100),
	limitation_type character varying(100),
	remaining_redemption integer,
	deadline date,
	constraint pk_voucher primary key (id)
);


INSERT INTO vmr.voucher (id, name, limitation_type, remaining_redemption, deadline)
VALUES (1, 'test_voucher', 'LIMITED', 2, '2022-08-10');

SELECT * FROM vmr.voucher;