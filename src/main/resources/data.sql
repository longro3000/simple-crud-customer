DROP TABLE IF EXISTS customer cascade;
 
CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  uuid uuid default random_uuid(),
  name VARCHAR(255) NOT NULL,
  is_active boolean NOT NULL default true
);

DROP TABLE IF EXISTS person cascade;
 
CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  uuid uuid default random_uuid(),
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  role VARCHAR(255) DEFAULT NOT NULL,
  is_deleted boolean NOT NULL default true
);

