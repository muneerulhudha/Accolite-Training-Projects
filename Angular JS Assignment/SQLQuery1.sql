create table RegisterUser
(
Username varchar(30)not null,
Email varchar(30),
Fname varchar(40),
Lname varchar(40),
Gender varchar(10),
UPassword varchar(15),
UType varchar(13)
 PRIMARY KEY(Username,Email)
)

CREATE TABLE Offers
(
OfferOwner varchar(30),
OfferName VARCHAR(30),
OfferDesc VARCHAR(300),
Participants INT,
StartDate DATE,
EndDate DATE,
OfferCategory VARCHAR(15),
Price MONEY,
)

CREATE TABLE SignupOffers
(
UserName VARCHAR(30),
OfferName VARCHAR(30),
NoOfParticipants INT,
SignUpDate DATE,
Price Money
)
