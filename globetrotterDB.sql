DROP DATABASE IF EXISTS globetrotterdb;

CREATE DATABASE globetrotterdb;

USE globetrotterdb;

 
  CREATE TABLE IF NOT EXISTS traveller (
	travellerId INT NOT NULL AUTO_INCREMENT,
	firstName  VARCHAR(50) NOT NULL,
	lastName  VARCHAR(50) NOT NULL,
	postalCode CHAR(6),
	city  VARCHAR(50),
    phoneNumber CHAR(10) NOT NULL,
	PRIMARY KEY pk_travellerId (travellerId) 
);

CREATE TABLE IF NOT EXISTS trip (
	tripId INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	type VARCHAR(30) NOT NULL,
	description VARCHAR(255),
    startDate DATE NOT NULL,
    endDate Date NOT NULL,
    PRIMARY KEY pk_tripId (tripId)
);

CREATE TABLE IF NOT EXISTS accomodation (
	accomodationId INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL,
	description VARCHAR(255) NOT NULL,
    tripId INT NOT NULL,
	PRIMARY KEY pk_accomodationId (accomodationId)
);

CREATE TABLE IF NOT EXISTS budget (
		budgetId  INT NOT NULL AUTO_INCREMENT,
        	name VARCHAR(100) NOT NULL,
    accomodationCost Decimal(7,2) NOT NULL,
    foodCost DECIMAL(7,2) NOT NULL,
    activityCost DECIMAL(7,2) NOT NULL,
    transportationCost DECIMAL(7,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    tripId INT NOT NULL,

	PRIMARY KEY pk_budgetId (budgetId)
);


CREATE TABLE IF NOT EXISTS tripTraveller (
	travellerId INT ,
	tripId INT,
	PRIMARY KEY pk_tripTraveller (tripId, travellerId)
);


CREATE TABLE IF NOT EXISTS activity (
	activityId INT NOT NULL AUTO_INCREMENT,
    activityName VARCHAR(100) NOT NULL,
    description VARCHAR(255),
	address VARCHAR(150) NOT NULL,   
    tripId INT NOT NULL,
  	PRIMARY KEY pk_activityId (activityId)
  );
  

ALTER TABLE accomodation
ADD CONSTRAINT fk_tripId_a
  FOREIGN KEY fk_tripId_a (tripId)
   REFERENCES trip (tripId);
    
    ALTER TABLE activity
	ADD CONSTRAINT fk_tripId
    FOREIGN KEY fk_tripId (tripId)
    REFERENCES trip (tripId);
  
   ALTER TABLE budget
	ADD CONSTRAINT fk_tripId_b
    FOREIGN KEY fk_tripId_b (tripId)
    REFERENCES trip (tripId);
    
ALTER TABLE tripTraveller
	ADD CONSTRAINT fk_tripTravellerTrip
    FOREIGN KEY fk_tripTravellerTrip (tripId)
    REFERENCES trip (tripId);
    
    ALTER TABLE tripTraveller
	ADD CONSTRAINT fk_tripTravellerTraveller
    FOREIGN KEY fk_tripTravellerTraveller (travellerId)
    REFERENCES traveller (travellerId);