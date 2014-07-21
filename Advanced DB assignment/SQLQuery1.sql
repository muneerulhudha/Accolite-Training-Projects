--1

CREATE TABLE BusDepot 
(
	DepotID int PRIMARY KEY,
	DepotName varchar(100),
	Address varchar(200)
)

CREATE TABLE BusDriver
(
	DriverID int PRIMARY KEY,
	DriverName varchar(100),
	Salary money,
	DepotID int,
	FOREIGN KEY(DepotID) REFERENCES BusDepot(DepotID)
)

CREATE TABLE BusCleaner
(
	CleanerID int PRIMARY KEY,
	CleanerName varchar(100),
	Salary money,
	DepotID int,
	FOREIGN KEY(DepotID) REFERENCES BusDepot(DepotID)
)

CREATE TABLE BusRoute
(
	RouteID int PRIMARY KEY,
	RouteName varchar(100),
	StartsFrom int,
	FOREIGN KEY(StartsFrom) REFERENCES BusDepot(DepotID)
)

CREATE TABLE Bus
(
	RegNo int,
	ModelName varchar(30),
	BusType varchar(30),
	DriverID int,
	CleanerID int,
	DepotID int ,
	RouteID int
	FOREIGN KEY(DriverID) REFERENCES BusDriver(DriverID),
	FOREIGN KEY(CleanerID) REFERENCES BusCleaner(CleanerID),
	FOREIGN KEY(DepotID) REFERENCES BusDepot(DepotID),
	FOREIGN KEY(RouteID) REFERENCES BusRoute(RouteID)
)

--2 

CREATE PROCEDURE dbo.InsertBusDepot 
(
	@DepotID int, 
	@DepotName varchar(100),
	@DepotAddress varchar(200)
)
AS 
BEGIN
	INSERT INTO dbo.BusDepot
	(
		DepotID,
		DepotName,
		Address
	)
	 VALUES(@DepotID,@DepotName,@DepotAddress);
END

DECLARE @DepotID1 int
DECLARE @DepotName1 varchar(100)
DECLARE @DepotAddress1 varchar(200)
SET @DepotID1=1001
SET @DepotName1='Mylapore Bus Depot'
SET @DepotAddress1='Mylapore Chennai'
EXEC dbo.InsertBusDepot @DepotID=@DepotID1,@DepotName=@DepotName1,@DepotAddress=@DepotAddress1

--Procedure for inserting into Bus_Driver
CREATE PROCEDURE dbo.InsertBusDriver
(
	@DriverID int, 
	@DriverName varchar(100),
	@DriverSalary money,
	@DepotID int
)
AS 
BEGIN
	INSERT INTO dbo.BusDriver
	(
		DriverID,
		DriverName,
		Salary,
		DepotID
	)
	 VALUES(@DriverID,@DriverName,@DriverSalary,@DepotID);
END

DECLARE @DriverID1 int
DECLARE @DriverName1 varchar(100)
DECLARE @DriverSalary1 varchar(200)
DECLARE @DepotID1 int
SET @DriverID1=5000
SET @DriverName1='Velu'
SET @DriverSalary1=9500
SET @DepotID1=1001
EXEC dbo.InsertBusDriver @DriverID=@DriverID1,@DriverName=@DriverName1,
@DriverSalary=@DriverSalary1,@DepotID=@DepotID1


DECLARE @DriverID1 int
DECLARE @DriverName1 varchar(100)
DECLARE @DriverSalary1 varchar(200)
DECLARE @DepotID1 int
SET @DriverID1=5001
SET @DriverName1='Raghu'
SET @DriverSalary1=8500
SET @DepotID1=1001
EXEC dbo.InsertBusDriver @DriverID=@DriverID1,@DriverName=@DriverName1,
@DriverSalary=@DriverSalary1,@DepotID=@DepotID1


--Procedure for inserting into Bus
CREATE PROCEDURE dbo.InsertBusDetails
(
	@RegistrationNo int, 
	@ModelName varchar(30),
	@BusType varchar(30),
	@DriverID int,
	@CleanerID int,
	@DepotID int,
	@RouteID int
)
AS 
BEGIN
	INSERT INTO dbo.Bus
	(
	RegNo,
	ModelName,
	BusType,
	DriverID,
	CleanerID,
	DepotID,
	RouteID
	)
	 VALUES(@RegistrationNo,@ModelName,@BusType,@DriverID,@CleanerID,@DepotID,@RouteID);
END

DECLARE @RegistrationNo1 int
DECLARE @ModelName1 varchar(30)
DECLARE @BusType1 varchar(30)
DECLARE @DriverID1 int
DECLARE @CleanerID1 int
DECLARE @DepotID1 int
DECLARE @RouteID1 int
SET @DriverID1=5000
SET @CleanerID1=3000
SET @RegistrationNo1=5969
SET @ModelName1='Mercedes'
SET @BusType1='Multiaxle AV'
SET @DepotID1=1001
SET @RouteID1=7000
EXEC dbo.InsertBusDetails @RegistrationNo=@RegistrationNo1,@ModelName=@ModelName1,@BusType=@BusType1,
	@DriverID=@DriverID1,@CleanerID=@CleanerID1,@DepotID=@DepotID1,@RouteID=@RouteID1

--Procedure for inserting into bus_Cleaner


CREATE PROCEDURE dbo.InsertBusCleaner
(
	@CleanerID int, 
	@CleanerName varchar(100),
	@CleanerSalary money,
	@DepotID int
)
AS 
BEGIN
	INSERT INTO dbo.BusCleaner
	(
		CleanerID,
		CleanerName,
		Salary,
		DepotID
	)
	 VALUES(@CleanerID,@CleanerName,@CleanerSalary,@DepotID);
END

DECLARE @CleanerID1 int
DECLARE @CleanerName1 varchar(100)
DECLARE @CleanerSalary1 varchar(200)
DECLARE @DepotID1 int
SET @CleanerID1=3000
SET @CleanerName1='Mahesh'
SET @CleanerSalary1=6500
SET @DepotID1=1001
EXEC dbo.InsertBusCleaner @CleanerID=@CleanerID1,@CleanerName=@CleanerName1,
@CleanerSalary=@CleanerSalary1,@DepotID=@DepotID1


DECLARE @CleanerID1 int
DECLARE @CleanerName1 varchar(100)
DECLARE @CleanerSalary1 varchar(200)
DECLARE @DepotID1 int
SET @CleanerID1=3001
SET @CleanerName1='Vishwa'
SET @CleanerSalary1=5900
SET @DepotID1=1001
EXEC dbo.InsertBusCleaner @CleanerID=@CleanerID1,@CleanerName=@CleanerName1,
@CleanerSalary=@CleanerSalary1,@DepotID=@DepotID1

--Procedure for inserting into Bus_Route

CREATE PROCEDURE dbo.InsertBusRoute 
(
	@RouteID int, 
	@RouteName varchar(100),
	@StartsFrom int
)
AS 
BEGIN
	INSERT INTO dbo.BusRoute
	(
		RouteID,
		RouteName,
		StartsFrom
	)
	 VALUES(@RouteID,@RouteName,@StartsFrom);
END

DECLARE @RouteID1 int
DECLARE @RouteName1 varchar(100)
DECLARE @StartFrom1 int
SET @RouteID1=7000
SET @RouteName1='M.G.Road'
SET @StartFrom1=1001
EXEC dbo.InsertBusRoute @RouteID=@RouteID1,@RouteName=@RouteName1,@StartsFrom=@StartFrom1

--3
CREATE PROCEDURE dbo.CleanerDetails
(
	@CleanerID int
)
AS
BEGIN
 SELECT BC.CleanerName,BC.Salary FROM dbo.BusCleaner BC
 WHERE BC.CleanerID=@CleanerID
END

DECLARE @CleanerID int
SET @CleanerID=3000
EXEC dbo.CleanerDetails @CleanerID

--4

CREATE FUNCTION dbo.NthSalary
(
	@N int,
	@Type varchar(10)
)
RETURNS @MoneyTable Table
(
	Salary money
)
AS
BEGIN
	IF @Type='Cleaner'
		INSERT @MoneyTable
		SELECT Salary
		FROM BusCleaner C1
		WHERE (@N-1) = ( 
			SELECT COUNT(DISTINCT(C2.Salary))
		FROM BusCleaner C2
		WHERE C2.Salary > C1.Salary)
	
	ELSE
	IF @Type='Driver'
		INSERT @MoneyTable
		SELECT Salary
		FROM BusDriver D1
		WHERE (@N-1) = ( 
		SELECT COUNT(DISTINCT(D2.Salary))
		FROM BusDriver D2
		WHERE D2.Salary > D1.Salary)
	RETURN
END


DECLARE @N int
DECLARE @Type varchar(10)
SET @N=1
SET @Type='Driver'
SELECT * FROM NthSalary(@N,@Type)

--5 

CREATE VIEW dbo.RouteDetails AS
SELECT DriverID, DriverName, Salary 
FROM BusDriver INNER JOIN BusRoute
ON BusDriver.DepotID=BusRoute.StartsFrom 
AND BusRoute.RouteName='M.G.Road'

SELECT * FROM dbo.RouteDetails
SELECT * FROM dbo.BusDriver

--5B

SELECT * 
FROM dbo.RouteDetails
WHERE SALARY>2000

--6

CREATE PROCEDURE TopRoutesIndicator
AS
BEGIN
DECLARE @Route int
DECLARE @NoOfBuses int
DECLARE @Message varchar(300)
DECLARE TopRoutes CURSOR FOR
(SELECT TOP(10) B.RouteID ,COUNT(*)[NoofBuses]
FROM Bus B
INNER JOIN BusRoute BR ON
B.RouteID=BR.RouteID
GROUP BY(B.RouteID))

OPEN TopRoutes
FETCH NEXT FROM TopRoutes
INTO @Route,@NoOfBuses

WHILE @@FETCH_STATUS = 0
BEGIN
	SELECT @Message= 'Route number: ' +CAST(@Route AS VARCHAR(10))+ ' No of Buses Plying: '+ CAST(@NoOfBuses AS VARCHAR(10))
	PRINT @Message
	FETCH NEXT FROM TopRoutes
	INTO @Route,@NoOfBuses
END
CLOSE TopRoutes
DEALLOCATE TopRoutes
END

EXEC TopRoutesIndicator

--7

CREATE TRIGGER dbo.SalaryUpdate
ON dbo.BusDriver
INSTEAD OF UPDATE 
AS
DECLARE @OldSalary money
DECLARE @NewSalary money
DECLARE @DriverID int
SELECT @NewSalary=i.Salary,@DriverID=i.DriverID FROM inserted i
SELECT @OldSalary=(SELECT Salary from dbo.BusDriver where DriverID=@DriverID )
PRINT @NewSalary
PRINT @OldSalary
BEGIN
IF @NewSalary < (@OldSalary + @OldSalary * .2)
	BEGIN
		PRINT 'Correct'
		UPDATE BusDriver set Salary=@NewSalary where DriverID=@DriverID
	END
ELSE
	BEGIN
		PRINT 'INCORRECT'
		RAISERROR ('Salary cannot be raised more than 20 percent',16,1)
	END
END

update BusDriver set Salary=20000 where DriverID=1001
select * from BusDriver

--8

CREATE TABLE UnluckyCleaner
(
	CleanerID int PRIMARY KEY,
	CleanerName varchar(100),
	Salary money,
	DepotID int,
	FOREIGN KEY(DepotID) REFERENCES BusDepot(DepotID)
)


CREATE TRIGGER CleanerEntry
ON dbo.BusCleaner
INSTEAD OF INSERT 
AS
DECLARE @CleanerID int
DECLARE @CleanerName varchar(100)
DECLARE @CleanerDepotID int
DECLARE @MinimumSalary money
DECLARE @CleanerSalary money
SELECT @CleanerID=i.CleanerID FROM inserted i
SELECT @CleanerName=i.CleanerName FROM inserted i
SELECT @CleanerSalary=i.Salary FROM inserted i
SELECT @CleanerDepotID=i.DepotID FROM inserted i
SET @MinimumSalary=(SELECT MIN(Salary)FROM BusDriver WHERE DepotID=@CleanerDepotID)
PRINT @CleanerDepotID
PRINT @MinimumSalary
PRINT @CleanerSalary
BEGIN
	IF @CleanerSalary<@MinimumSalary 
		BEGIN	
			INSERT INTO UnluckyCleaner VALUES(@CleanerID,@CleanerName,@CleanerSalary,@CleanerDepotID)
			PRINT 'Inserted into UnluckyCleaner'
		END
	ELSE				
		BEGIN
			INSERT INTO BusCleaner VALUES(@CleanerID,@CleanerName,@CleanerSalary,@CleanerDepotID)
			PRINT 'Inserted into BusCleaner'
		END
END


INSERT INTO BusCleaner Values(3002,'Guru',5500,1001)

SELECT * FROM UnluckyCleaner

INSERT INTO BusDriver VALUES(1002,'Hari',9000,1001)

DECLARE @DepotID1 int
DECLARE @DepotName1 varchar(100)
DECLARE @DepotAddress1 varchar(200)
SET @DepotID1=466
SET @DepotName1='Broadway Bus Depot'
SET @DepotAddress1='Paris, Broadway'
EXEC dbo.InsertBusDepot @DepotID=@DepotID1,@DepotName=@DepotName1,@DepotAddress=@DepotAddress1

--9

CREATE PROCEDURE RemoveDupicates
AS
BEGIN
WITH    numbered
          AS ( SELECT   RegNo,ModelName,BusType,DriverID,CleanerID,DepotID,RouteID
                      , row_number() OVER ( PARTITION BY RegNo,ModelName,BusType,DriverID,CleanerID,DepotID,RouteID 
                      ORDER BY RegNo,ModelName,BusType,DriverID,CleanerID,DepotID,RouteID ) AS nr
               FROM     Bus
             )
    DELETE  FROM numbered
    WHERE   nr > 1

END

EXEC RemoveDupicates

--10

CREATE TRIGGER dbo.BusDuplicateCheck
ON dbo.Bus
INSTEAD OF INSERT
AS
DECLARE @RegistrationNo int
DECLARE @ModelName varchar(30)
DECLARE @BusType varchar(30)
DECLARE @DriverID int
DECLARE @CleanerID int
DECLARE @DepotID int
DECLARE @RouteID int
SELECT @RegistrationNo=i.RegNo FROM inserted i
SELECT @ModelName=i.ModelName FROM inserted i
SELECT @BusType=i.BusType FROM inserted i
SELECT @DriverID=i.DriverID FROM inserted i
SELECT @CleanerID=i.CleanerID FROM inserted i
SELECT @DepotID=i.DepotID FROM inserted i
SELECT @RouteID=i.RouteID FROM inserted i
PRINT @RegNo
PRINT @ModelName
PRINT @BusType
PRINT @DriverID
PRINT @CleanerID
PRINT @DepotID
PRINT @RouteID
BEGIN
	IF EXISTS (SELECT * FROM dbo.Bus WHERE RegNo=@RegistrationNo
	AND ModelName=@ModelName AND BusType=@BusType AND DriverID=@DriverID
	AND CleanerID=@CleanerID AND DepotID=@DepotID AND RouteID=@RouteID)
			PRINT 'Already Exists!'
	INSERT INTO dbo.Bus VALUES(@RegistrationNo,@ModelName,@BusType,@DriverID,@CleanerID,@DepotID,@RouteID)
END

--11

CREATE PROCEDURE dbo.InsertBusDetails2
(
	@RegistrationNo int, 
	@ModelName varchar(30),
	@BusType varchar(30),
	@DriverID int,
	@CleanerID int,
	@DepotID int,
	@RouteID int
)
AS 
DECLARE @RegistrationNo1 int
DECLARE @ModelName1 varchar(30)
DECLARE @BusType1 varchar(30)
DECLARE @DriverID1 int
DECLARE @CleanerID1 int
DECLARE @DepotID1 int
DECLARE @RouteID1 int
BEGIN	
	IF EXISTS (SELECT * FROM dbo.Bus WHERE RegNo=@RegistrationNo
	AND ModelName=@ModelName AND BusType=@BusType AND DriverID=@DriverID
	AND CleanerID=@CleanerID AND DepotID=@DepotID AND RouteID=@RouteID)
	BEGIN
		PRINT 'Already Exists!'
	END
	ELSE
		BEGIN
		PRINT 'Entering else'
		INSERT INTO dbo.Bus
		(
			RegNo,
			ModelName,
			BusType,
			DriverID,
			CleanerID,
			DepotID,
			RouteID
		)
		VALUES(@RegistrationNo,@ModelName,@BusType,@DriverID,@CleanerID,@DepotID,@RouteID);
		END
END
