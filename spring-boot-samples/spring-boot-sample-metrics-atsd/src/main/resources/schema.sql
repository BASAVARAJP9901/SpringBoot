CREATE TABLE PUBLIC.city (id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, name VARCHAR(64), country VARCHAR(64), state VARCHAR(64), map VARCHAR(64))