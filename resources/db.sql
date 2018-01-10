DROP DATABASE IF EXISTS ac;
CREATE DATABASE ac;

USE ac;

CREATE TABLE users (
  user_name VARCHAR(15) UNIQUE NOT NULL,
  user_pass VARCHAR(128) NOT NULL,
  user_email VARCHAR(128) NOT NULL,
  PRIMARY KEY (user_name)
);