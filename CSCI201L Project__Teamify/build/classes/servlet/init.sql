CREATE DATABASE Project;
CREATE TABLE Users (
	Username varchar(50) PRIMARY KEY NOT NULL,
	Password varchar(50) NOT NULL,
	Email varchar(50) NOT NULL,
	UserType enum("Contributor", "Organizer") NOT NULL,
	Description varchar(1000)
);
CREATE TABLE Projects (
	ProjectName varchar(100) PRIMARY KEY NOT NULL,
	Description varchar(2000) NOT NULL
);
CREATE TABLE Tags (
	TagID int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	TagName varchar(20) NOT NULL,
	Username varchar(50),
	ProjectName varchar(100),
	FOREIGN KEY Username REFERENCES Users(Username),
	FOREIGN KEY ProjectName REFERENCES Projects(ProjectName)
);
CREATE TABLE ProjectMembers (
	PMID int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	ProjectName varchar(100) NOT NULL,
	Username varchar(50) NOT NULL,
	FOREIGN KEY ProjectName REFERENCES Projects(ProjectName),
	FOREIGN KEY Username REFERENCES Users(Username)
);