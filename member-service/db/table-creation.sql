
use mysql;
CREATE USER 'admin'@'%' IDENTIFIED BY 'admin';
GRANT ALL ON *.* TO 'admin'@'%';
flush privileges;





CREATE DATABASE IF NOT EXISTS MemberServiceDB;
USE MemberServiceDB;


CREATE TABLE  Member(
	memberID INT AUTO_INCREMENT,
    memberFirstName VARCHAR(60),
    memberLastName VARCHAR(30) NOT NULL,
    memberBirthdate DATE NOT NULL,
    memberEmail VARCHAR(30),
    memberPhone VARCHAR(12) NOT NULL,
    memberOriginVillage VARCHAR (20),
    memberInscriptionDate DATE,
    
    CONSTRAINT Member_PK PRIMARY KEY (memberID)
    );

CREATE TABLE  Adress(
	adressID INT AUTO_INCREMENT,
    adressStreet VARCHAR(60),
    adressZipCode INT,
    adressCity VARCHAR(30) NOT NULL,
    adressCountry VARCHAR(20) NOT NULL,
    adressMemberID INT,
    
    CONSTRAINT Adress_PK PRIMARY KEY (adressID),
    CONSTRAINT Adress_Member_FK FOREIGN KEY (adressMemberID) REFERENCES Member (memberID) 
		ON UPDATE CASCADE ON DELETE CASCADE
    );
    
CREATE TABLE  Donor(
	donorID INT AUTO_INCREMENT,
    donorFirstName VARCHAR(60),
    donorLastName VARCHAR(30) NOT NULL,
    donorBirthdate DATE NOT NULL,
    donorEmail VARCHAR(30),
    donorPhone VARCHAR(12) NOT NULL,
    donorCity VARCHAR(30) NOT NULL,
    donorCountry VARCHAR(20) NOT NULL,
    donorInscriptionDate DATE NOT NULL,
    
    CONSTRAINT donor_PK PRIMARY KEY (donorID)
    );

CREATE TABLE Partner(
	partnerID INT AUTO_INCREMENT,
    partnerName VARCHAR(20) NOT NULL,
    partnerContactPerson VARCHAR (30),
	partnerEmail VARCHAR(30),
    partnerPhone VARCHAR(12) NOT NULL,
    partnerCity VARCHAR(30) NOT NULL,
    partnerCountry VARCHAR(20) NOT NULL,
    partnerInscriptionDate DATE NOT NULL,
    
    CONSTRAINT Partner_PK PRIMARY KEY (partnerID)
    
	);
    
CREATE TABLE  Post(
	postID INT AUTO_INCREMENT,
    postName VARCHAR(30) NOT NULL,
    postDescription VARCHAR(100) NOT NULL,
    
    CONSTRAINT Post_PK PRIMARY KEY (postID)
    );

CREATE TABLE  PostOccupied(
	postOccupiedID INT AUTO_INCREMENT,
	postOccupiedMemberID INT,
    postOccupiedPostID INT,
    postOccupiedStartDate DATE NOT NULL,
    postOccupiedEndDate DATE NOT NULL,
    
    CONSTRAINT PostOccupied_PK PRIMARY KEY (postOccupiedID),
    CONSTRAINT Member_PostOccupied_FK FOREIGN KEY (postOccupiedMemberID) REFERENCES Member(memberID) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT Post_PostOccupied_FK FOREIGN KEY (postOccupiedPostID) REFERENCES Post(postID) ON UPDATE CASCADE ON DELETE CASCADE
    );

CREATE TABLE  Project(
	projectID INT AUTO_INCREMENT,
    projectName VARCHAR(60) NOT NULL,
	projectManagerID INT,
    projectStartDate DATE NOT NULL,
    projectEndDate DATE NOT NULL,
    projectDescription VARCHAR(100),
    
    
    CONSTRAINT Project_PK PRIMARY KEY (projectID),
    CONSTRAINT Project_Member_FK FOREIGN KEY (projectManagerID) REFERENCES Member(memberID) ON UPDATE CASCADE ON DELETE CASCADE
    );
    
    
INSERT INTO Member(memberFirstName, memberLastName, memberBirthdate, memberEmail, 
					memberPhone, memberOriginVillage, memberInscriptionDate)
	VALUES('Jane', 'DOE', now(), 'jane.doe@gmail.com', '0779962916', 'Balengou', now());

INSERT INTO Member(memberFirstName, memberLastName, memberBirthdate, memberEmail, 
					memberPhone, memberOriginVillage, memberInscriptionDate)
	VALUES('Jane', 'WEST', now(), 'jane.west@gmail.com', '0779962916', 'Balengou', now());
    
    
INSERT INTO Member(memberFirstName, memberLastName, memberBirthdate, memberEmail, 
					memberPhone, memberOriginVillage, memberInscriptionDate)
	VALUES('Jane', 'MAY', now(), 'jane.may@gmail.com', '0779962916', 'Balengou', now());
    

INSERT INTO Adress (adressStreet, adressZipCode, adressCity, adressCountry, adressMemberID)
	VALUES('avenue des sports 13', 1400, 'Yverdon-les-Bains', 'Suisse', 1);

INSERT INTO Adress (adressStreet, adressZipCode, adressCity, adressCountry, adressMemberID)
	VALUES('route de Lausanne 2', 1400, 'Yverdon-les-Bains', 'Suisse', 2);

