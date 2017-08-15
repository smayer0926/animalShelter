SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS animals (
  id int PRIMARY KEY auto_increment,
  animalName VARCHAR,
  gender VARCHAR,
  type VARCHAR,
  breed VARCHAR
);