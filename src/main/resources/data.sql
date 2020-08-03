DROP TABLE IF EXISTS customer cascade;
 
CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  uuid uuid default random_uuid() UNIQUE,
  name VARCHAR(255) NOT NULL,
  is_active boolean NOT NULL default true
);

DROP TABLE IF EXISTS person cascade;
 
CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  uuid uuid default random_uuid() UNIQUE,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  is_deleted boolean NOT NULL default true,
  customer_id uuid,

  CONSTRAINT FK_PERSON_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(UUID)
);

