CREATE TABLE users
(id bigint not null PRIMARY KEY,
user_name character varying(255) not null,
 first_name character varying(50) not null ,
 last_name character varying(50) not null,
 email character varying(50),
 contact bigint,
 password character varying(255) not null );

CREATE TABLE student
(id bigint not null PRIMARY KEY,
 first_name character varying(50) not null ,
 last_name character varying(50) not null,
 programme_of_study character varying(100) not null,
 email character varying(50),
 contact bigint,
 year_of_award integer,
 country character varying(50) not null );

CREATE TABLE scholarship
(id bigint not null PRIMARY KEY,
 scholarship_type character varying(25) not null ,
 year_awarded date not null,
 year_of_graduation integer,
 status character varying(25) not null );

CREATE TABLE faculty
(id bigint not null PRIMARY KEY,
 name_of_faculty character varying(100) not null ,
 address character varying(255) not null );

CREATE TABLE hostel
(id bigint not null PRIMARY KEY,
 hostel_name character varying(50) not null ,
 hostel_address character varying(255) not null,
 hostel_type character varying(50) not null );


