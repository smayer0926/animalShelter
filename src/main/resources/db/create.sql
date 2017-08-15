SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS animals (
  id int PRIMARY KEY auto_increment,
  animalName VARCHAR,
  gender VARCHAR,
  type VARCHAR,
  breed VARCHAR
);

CREATE TABLE IF NOT EXISTS owner (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  type VARCHAR,
  breed VARCHAR,
  phone VARCHAR
);

