DROP TABLE IF EXISTS voucher;

CREATE TABLE voucher (
	id INT AUTO_INCREMENT  PRIMARY KEY,
	name character varying(100) not null,
	limitation_type character varying(100) not null,
	redemption_limit integer not null,
	remaining_redemption integer not null,
	deadline date not null
);