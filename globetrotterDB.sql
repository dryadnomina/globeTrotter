DROP DATABASE IF EXISTS globetrotterdb;

CREATE DATABASE globetrotterdb;

USE globetrotterdb;

  CREATE TABLE IF NOT EXISTS traveller (
	travellerId INT NOT NULL AUTO_INCREMENT,
	tripId INT NOT NULL,
	firstName  VARCHAR(50) NOT NULL,
	lastName  VARCHAR(50) NOT NULL,
	poastalCode CHAR(6) NULL,
	city  VARCHAR(50) NULL,
    phoneNumber CHAR(10) NULL,
	PRIMARY KEY pk_travellerId (travellerId) 
);

CREATE TABLE IF NOT EXISTS trip (
	tripId INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	type VARCHAR(15) NOT NULL,
	description VARCHAR(255) NOT NULL,
    PRIMARY KEY pk_tripId (tripId)
);

CREATE TABLE IF NOT EXISTS accomodation (
	accomodationId INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
    type VARCHAR(15) NOT NULL,
	description VARCHAR(255) NOT NULL,
    tripId INT NOT NULL,
	PRIMARY KEY pk_accomodationId (accomodationId)
);


CREATE TABLE IF NOT EXISTS tripTraveller (
	travellerId INT NOT NULL,
	tripId INT NOT NULL,
	PRIMARY KEY pk_tripTraveller (tripId, travellerId)
);


CREATE TABLE IF NOT EXISTS activity (
	activityId INT NOT NULL AUTO_INCREMENT,
    tripId INT NOT NULL,
    activityName VARCHAR(100) NOT NULL,
  	PRIMARY KEY pk_activityId (activityId)
  );
  
CREATE TABLE IF NOT EXISTS detail (
	detailId INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255) NULL,
	address VARCHAR(150) NOT NULL,
    cost DECIMAL NOT NULL,
    activityId INT NOT NULL,
	PRIMARY KEY pk_detailId (detailId)   
  );

-- FK

ALTER TABLE detail
	ADD CONSTRAINT fk_activityId
    FOREIGN KEY fk_activityId (activityId)
    REFERENCES activity (activityId);
    
    ALTER TABLE activity
	ADD CONSTRAINT fk_tripId
    FOREIGN KEY fk_tripId (tripId)
    REFERENCES trip (tripId);
  
ALTER TABLE trip
	ADD CONSTRAINT fk_accomodationId
    FOREIGN KEY fk_accomodationId (tripId)
    REFERENCES accomodation (accomodationId);
    
ALTER TABLE trip
	ADD CONSTRAINT fk_travellerId
    FOREIGN KEY fk_travellerId (travellerId)
    REFERENCES traveller (travellerId);
    
ALTER TABLE tripTraveller
	ADD CONSTRAINT fk_tripTravellerTrip
    FOREIGN KEY fk_tripTravellerTrip (tripId)
    REFERENCES trip (tripId);
    
    ALTER TABLE tripTraveller
	ADD CONSTRAINT fk_tripTravellerTraveller
    FOREIGN KEY fk_tripTravellerTraveller (travellerId)
    REFERENCES traveller (travellerId);