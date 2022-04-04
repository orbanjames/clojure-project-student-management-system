CREATE TABLE users
(id bigint not null PRIMARY KEY,
username character varying(255) not null,
 firstname character varying(50) not null ,
 lastname character varying(50) not null,
 email character varying(50),
 contact bigint,
 password character varying(255) not null );

CREATE TABLE student
(id bigint not null PRIMARY KEY,
 firstname character varying(50) not null ,
 lastname character varying(50) not null,
 programmeofstudy character varying(100) not null,
 email character varying(50),
 contact bigint,
 yearofaward integer,
 country character varying(50) not null );

CREATE TABLE university
(id bigint not null PRIMARY KEY,
 name character varying(25) not null ,
 address text,
 location character varying(50) );

CREATE TABLE faculty
(id bigint not null PRIMARY KEY,
 name character varying(100) not null ,
 address character varying(255) not null );

CREATE TABLE course
(id bigint not null PRIMARY KEY,
 title character varying(50) not null ,
 code character varying(10) not null );


