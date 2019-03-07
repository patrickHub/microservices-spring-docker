CREATE DATABASE IF NOT EXISTS FinanceServiceDB;
USE FinanceServiceDB;


DROP TABLE  Inscription;
DROP TABLE  CashFund;
DROP TABLE  IncomeTransaction;
DROP TABLE  OutcomeTransaction;
DROP TABLE  EventMember;
DROP TABLE  RecoveryEvent;
DROP TABLE  RecoveryActivity;
DROP TABLE  OutcomeProject;
DROP TABLE  RecoveryProject;
DROP TABLE  Donation;
DROP TABLE  Meal;
DROP TABLE  Sanction;

CREATE TABLE  Inscription(
	inscriptionID INT AUTO_INCREMENT,
    inscriptionMemberID INT,
    inscriptionMentorID INT,
    inscriptionStatus VARCHAR (10),
    inscriptionAmount INT,
    inscriptionDate DATE NOT NULL,
    memberFirstName VARCHAR(60),

    
    CONSTRAINT Inscription_PK PRIMARY KEY (inscriptionID)
    );
    

CREATE TABLE  CashFund(
	cashFundID INT AUTO_INCREMENT,
	cashFundDate DATE NOT NULL,
    cashFundAmount INT,
    cashFundMemberID INT,
    cashFundType VARCHAR(10),
    cashFundDescription VARCHAR(100),
    
    CONSTRAINT CashFund_PK PRIMARY KEY (cashFundID)
    );
    

CREATE TABLE  IncomeTransaction(
	incomeTransactionID INT AUTO_INCREMENT,
	incomeTransactionDate DATE UNIQUE NOT NULL,
    incomeTransactionAmount INT,
    incomeTransactionAuthorID INT NOT NULL,
    incomeTransactionNature VARCHAR(10),
    incomeTransactionDescription VARCHAR(100),
    CONSTRAINT IncomeTransaction_PK PRIMARY KEY (incomeTransactionID)
    );
    
CREATE TABLE  OutcomeTransaction(
	outcomeTransactionID INT AUTO_INCREMENT,
	outcomeTransactionDate DATE UNIQUE NOT NULL,
    outcomeTransactionAmount INT,
    outcomeTransactionAuthorID INT NOT NULL,
    outcomeTransactionNature VARCHAR(10),
    outcomeTransactionDescription VARCHAR(100),
    CONSTRAINT OutcomeTransaction_PK PRIMARY KEY (outcomeTransactionID)
    );


CREATE TABLE  EventMember(
	eventMemberID INT AUTO_INCREMENT,
	eventMemberDate DATE NOT NULL,
    eventMemberReceivedDate DATE NOT NULL,
    eventMemberAmount INT,
    eventMemberRecipientID INT NOT NULL,
    eventMemberNature VARCHAR(10),
    eventMemberDescription VARCHAR(100),
    eventMemberStatus VARCHAR(10),
    eventMemberTransactionID INT,
    CONSTRAINT EventMember_PK PRIMARY KEY (eventMemberID),
    CONSTRAINT EventMember_OutcomeTransaction_FK FOREIGN KEY (eventMemberTransactionID) REFERENCES OutcomeTransaction(outcomeTransactionID) 
		ON UPDATE CASCADE ON DELETE CASCADE
    );  


CREATE TABLE  RecoveryEvent(
	recoveryEventID INT AUTO_INCREMENT,
	recoveryEventDate DATE NOT NULL,
    recoveryEventAmount INT,
    recoveryEventMemberID INT,
    eventMemberID INT,
    recoveryEventNature VARCHAR(10),
    recoveryEventDescription VARCHAR(100),
    CONSTRAINT RecoveryEvent_PK PRIMARY KEY (recoveryEventID),
    CONSTRAINT RecoveryEvent_EventMember_FK FOREIGN KEY (eventMemberID) REFERENCES EventMember(eventMemberID) 
		ON UPDATE CASCADE ON DELETE CASCADE
    ); 
    

CREATE TABLE  Activity(
	activityID INT AUTO_INCREMENT,
	activityDate DATE UNIQUE NOT NULL,
    activityReceivedDate DATE NOT NULL,
    activityAmount INT,
    activityManagerID INT NOT NULL,
    activityNature VARCHAR(10),
    activityDescription VARCHAR(100),
    activityStatus VARCHAR(10),
    activityTransactionID INT,
    CONSTRAINT activity_PK PRIMARY KEY (activityID),
    CONSTRAINT Activity_OutcomeTransaction_FK FOREIGN KEY (activityTransactionID) REFERENCES OutcomeTransaction(outcomeTransactionID) 
		ON UPDATE CASCADE ON DELETE CASCADE
    ); 


CREATE TABLE  RecoveryActivity(
	recoveryActivityID INT AUTO_INCREMENT,
	recoveryActivityDate DATE NOT NULL,
    recoveryActivityAmount INT,
    recoveryActivityMemberID INT,
    activityID INT,
    recoveryActivityNature VARCHAR(10),
    recoveryActivityDescription VARCHAR(100),
    CONSTRAINT RecoveryActivity_PK PRIMARY KEY (recoveryActivityID),
    CONSTRAINT RecoveryActivity_Activity_FK FOREIGN KEY (activityID) REFERENCES Activity(activityID) 
		ON UPDATE CASCADE ON DELETE CASCADE
    ); 
    
    
CREATE TABLE  OutcomeProject(
	outcomeProjectID INT AUTO_INCREMENT,
	outcomeProjectDate DATE NOT NULL,
    outcomeProjectAmount INT,
    outcomeProjectManagerID INT NOT NULL,
    projectID INT,
    outcomeProjectTransactionID INT,
    outcomeProjectNature VARCHAR(10),
    outcomeProjectDescription VARCHAR(100),
    CONSTRAINT IncomeProject_PK PRIMARY KEY (incomeProjectID),
    CONSTRAINT OutcomeProject_OutcomeTransaction FOREIGN KEY (outcomeProjectTransactionID) REFERENCES OutcomeTransaction (outcomeTransactionID)
    );
    
    
CREATE TABLE  RecoveryProject(
	recoveryProjectID INT AUTO_INCREMENT,
	recoveryProjectDate DATE NOT NULL,
    recoveryProjectAmount INT,
    recoveryProjectMemberID INT NOT NULL,
    projectID INT,
    recoveryProjectNature VARCHAR(10),
    recoveryProjectDescription VARCHAR(100),
    CONSTRAINT IncomeProject_PK PRIMARY KEY (incomeProjectID)
    );
    

  CREATE TABLE  Donation(
	donationID INT AUTO_INCREMENT,
	donationDate DATE NOT NULL,
    donationAmount INT,
    donationDonorID INT NOT NULL,
    projectID INT,
    donationNature VARCHAR(10),
    donationDescription VARCHAR(100),
    CONSTRAINT Donation_PK PRIMARY KEY (donationID)
    );  



 CREATE TABLE  Meal(
	mealID INT AUTO_INCREMENT,
	mealDate DATE NOT NULL,
    mealAmount INT,
    mealMemberID INT NOT NULL,
    mealNature VARCHAR(10),
    mealDescription VARCHAR(100),
    CONSTRAINT Donation_PK PRIMARY KEY (mealID)
    ); 
    
    
 CREATE TABLE  Sanction(
	sanctionID INT AUTO_INCREMENT,
    sanctionName VARCHAR(30),
	sanctionDate DATE NOT NULL,
    sanctionAmount INT,
    sanctionMemberID INT NOT NULL,
    sanctionStatut VARCHAR(10),
    sanctionNature VARCHAR(10),
    sanctionDescription VARCHAR(100),
    CONSTRAINT Sancction_PK PRIMARY KEY (sanctionID)
    ); 
