-- Listing here with entities in Person:
-- There are 37 Entities in table Person
-- 
-- BusinessEntityID 
	-- PasswordHash 
	-- PasswordSalt 
	-- Person Type 
	-- NameStyle 
	-- Title 
	-- FirstName 
	-- MiddleName 
	-- LastName 
	-- Suffix 				
	-- EmailPromotion 
	-- AdditionalContactInfo 
	-- Demographics 
	-- rowguid 					
	-- ModifiedDate

-- AddressID
	-- AddressLine1 
	-- AddressLine2 
	-- City 
	-- StateProvinceID 
	-- PostalCode 
	-- SpatialLocaton 
			
-- EmailAddressID 
	-- EmailAddress

-- PersonID 
	-- ContactTypeID 

-- StateProvinceID 
	-- StateProvinceCode 
	-- CountryRegionCode 
	-- IsOnlyStateProvinceFlag 
	-- Name 				
	-- TerritoryID 

-- PhoneNumberTypeID 

-- ContactTypeID 

-- AddressTypeID 

-- CountryRegionCode

-- AddressTypeID




-- Listing here with functional dependencies in Person:

-- There are 11 Functional Dependencies are there in person Table

-- BusinessEntityID --> PasswordHash PasswordSalt

-- BusinessEntityID -->Person Type NameStyle Title FirstName MiddleName LastName Suffix EmailPromotion AdditionalContactInfo Demographics rowguid ModifiedDate

-- AddressID --> AddressLine1 AddressLine2 City StateProvinceID PostalCode SpatialLocaton rowguid ModifiedDate

-- BusinessEntityID EmailAddressID --> EmailAddress

-- BusinessEntityID PersonID --> ContactTypeID ModifiedDate

-- StateProvinceID --> StateProvinceCode CountryRegionCode IsOnlyStateProvinceFlag Name TerritoryID rowguid ModifiedDate

-- PhoneNumberTypeID --> Name

-- ContactTypeID --> Name ModifiedDate

-- AddressTypeID --> Name rowguid ModifiedSate

-- CountryRegionCode --> Name ModifiedDate

-- AddressID BusinessEntityID --> AddressTypeID ModifiedDate


-- DML queries

--1
SELECT PPER.FirstName, PPER.MiddleName, PPER.LastName , PPPHO.PhoneNumber, EA.EmailAddress, 
		CC.CardNumber, CC.ExpMonth, CC.ExpYear
	FROM  Sales.PersonCreditCard PCC , Sales.CreditCard CC, Person.PersonPhone PPPHO, 
			Person.EmailAddress EA, Person.Person PPER
	WHERE CC.ExpMonth  = 4 AND CC.ExpYear =2007 AND CC.CreditCardID=P.CreditCardID 
			AND PCC.BusinessEntityID=EA.BusinessEntityID AND PCC.BusinessEntityID=PPP.BusinessEntityID 
			AND PCC.BusinessEntityID=PPER.BusinessEntityID
			
			
--2
--Insert statements used
INSERT INTO Production.ProductProductPhoto values (1,150,1,2001-03-04)
INSERT INTO Production.ProductProductPhoto values (2,150,1,2001-03-04)
INSERT INTO Production.ProductProductPhoto values (3,150,1,2001-03-04)
INSERT INTO Production.ProductProductPhoto values (2,130,1,2001-03-04)
INSERT INTO Production.ProductProductPhoto values (4,150,1,2001-03-04)
INSERT INTO Production.ProductProductPhoto values (2,110,1,2001-03-04)
INSERT INTO Production.ProductProductPhoto values (3,105,1,2001-03-04)
INSERT INTO Production.ProductProductPhoto values (3,160,1,2001-03-04)

SELECT Production.Product.Name [ProductName] FROM Production.Product 
	WHERE Production.Product.ProductID IN
	(SELECT Pro.ProductID
		FROM Production.Product Pro,Production.ProductProductPhoto PPP
		WHERE Pro.ProductID=PPP.ProductID 
		GROUP BY(Pro.ProductID) HAVING COUNT(*)>=2)
		
		
--3	 Displaying the Product Names
SELECT Pro.Name
	FROM Production.Product Pro
	WHERE Pro.ProductID IN
	((SELECT Pro.ProductID 
		FROM Production.Product Pro
		WHERE Pro.ProductID NOT  IN 
		(SELECT PR.ProductID 
			FROM Production.ProductReview PR))
		INTERSECT
		(SELECT Production.Product.ProductID 
			FROM Production.Product 
			WHERE Production.Product.ProductID IN
			(SELECT Pro.ProductID
				FROM Production.Product Pro,Production.ProductProductPhoto PPP
				WHERE Pro.ProductID=PPP.ProductID 
				GROUP BY(Pro.ProductID) HAVING COUNT(*)<2))
			INTERSECT
			(SELECT SOD.ProductID
				FROM Sales.SalesOrderDetail SOD,Sales.SalesOrderHeader SOH
				WHERE SOD.SalesORDERID=SOH.SalesORDERID
				GROUP BY SOD.ProductID
				HAVING COUNT(*)>=10)
			INTERSECT
			(SELECT SOD.ProductID
				FROM Sales.SalesOrderDetail SOD,Sales.SalesOrderHeader SOH
				WHERE SOD.SalesOrderID=SOH.SalesORDERID
				GROUP BY SOH.CustomerID,SOD.ProductID
				HAVING COUNT(*)>3))
				
--4

SELECT COUNT(Ad.rowguid)[No of Products] 
	FROM Person.Address Ad,Sales.SalesOrderHeader SOH,Sales.SalesOrderDetail SOD, 
		Production.Product Pro, Production.ProductSubcategory PSC 
	WHERE Ad.AddressID=SOH.BillToAddressID 
		AND SOH.SalesOrderID=SOD.SalesOrderID 
		AND SOD.ProductID=Pro.ProductID 
		AND Pro.ProductSubcategoryID=PSC.ProductSubcategoryID  
		AND Ad.City='London' AND PSC.Name='Cranksets'
		
--5
--Part 1

SELECT MIN(Pro.StANDardCost) AS StandardCost,Pro.Color 
	FROM Production.Product Pro 
	WHERE Pro.StandardCost>0.0 AND  Pro.Color in('Yellow','Blue','Black')
	GROUP BY Pro.Color

--Part 2
SELECT Pro.Name, Pro.ListPrice,Pro.Color, MSC.StandardCost 
	FROM Production.Product Pro 
	INNER JOIN
	(SELECT MIN(Pro.StandardCost) AS StandardCost,Pro.Color 
			FROM Production.Product Pro 
			WHERE Pro.StandardCost>0.0 AND  Pro.Color in('Yellow','Blue','Black')
			GROUP BY Pro.Color) MSC 
	ON MSC.StandardCost=Pro.StandardCost 
	AND Pro.Color=MSC.Color
	
--6
-- Part1
SELECT PC.ProductCategoryID,Count(Pro.Color)[Color Count],
	ISNULL(Pro.Color,'N/A')[Color],
	AVG(Pro.ListPrice)[Avg Price]
	FROM Production.Product Pro, production.ProductSubCategory SC,Production.ProductCategory PC
	WHERE SC.ProductCategoryID=PC.ProductCategoryID AND SC.ProductSubCategoryID=Pro.ProductSubCategoryID
	GROUP BY PC.ProductCategoryID,Pro.Color

--Part 2
--c)
SELECT PC.Name,Pro.Color,Count(Pro.Color)[Color Count],AVG(Pro.ListPrice)[Avg List price]
	FROM Production.Product Pro, Production.ProductCategory PC, Production.ProductSubCategory PSC
	WHERE Pro.ProductSubcategoryID=PSC.ProductSubcategoryID AND PSC.ProductCategoryID=PC.ProductCategoryID
	GROUP BY PC.Name,Pro.Color
	
	
--d,e

SELECT PC.Name,COL.Color,COL.ColorCount,COL.AvgPrice 
	FROM Production.ProductCategory PC
	INNER JOIN
	(SELECT C.ProductCategoryID,Count(Pro.Color)ColorCount,
		ISNULL(Pro.Color,'N/A') Color,
		AVG(Pro.ListPrice)AvgPrice
		FROM production.product pro, production.ProductSubCategory SC,Production.ProductCategory C
		WHERE SC.ProductCategoryID=C.ProductCategoryID AND SC.ProductSubCategoryID=Pro.ProductSubCategoryID
		GROUP BY C.ProductCategoryID,Pro.Color) COL
	ON COL.ProductCategoryID=PC.ProductCategoryID
	ORDER BY PC.Name,COL.Color

--7
--Customer table does NOT contain company name in it. So i am printing only the CustomerID

SELECT C.CustomerID, SOH.SubTotal, SUM(P.Weight)[Total Order Weight]
	FROM Sales.Customer C, Sales.SalesOrderHeader SOH,Sales.SalesOrderDetail SOD, Production.Product Pro
	WHERE C.CustomerID=SOH.CustomerID AND SOH.SalesOrderID=SOD.SalesOrderID AND SOD.ProductID=Pro.ProductID
	GROUP BY C.CustomerID,SOH.SubTotal
	ORDER BY(SOH.SubTotal) DESC

--8 
--Listing all the Sales Personnel ID's

SELECT SOH.SalesPersonID,COUNT(SOH.SalesPersonID)[Order Count]
	FROM Sales.SalesOrderHeader SOH
	WHERE SOH.SubTotal>10000
	GROUP BY SOH.SalesPersonID
	HAVING COUNT(*)>=5
	ORDER BY SOH.SalesPersonID

--9

(SELECT SOH.SalesPersonID
	FROM Purchasing.PurchaseOrderDetail POD, Sales.SalesOrderDetail SOD,Sales.SalesOrderHeader SOH
	WHERE SOH.SalesPersonID IS NOT NULL 
	AND POD.RejectedQty=0.00 
	AND POD.ProductID=SOD.ProductID 
	AND SOD.SalesOrderID=Soh.SalesOrderID
	)
INTERSECT
(SELECT SOH.SalesPersonID
	FROM Sales.SalesOrderHeader SOH
	WHERE SOH.SubTotal>10000 AND SOH.SalesPersonID IS NOT NULL 
	GROUP BY SOH.SalesPersonID
	having COUNT(*)>=5
	)

--10
CREATE FUNCTION Display(@DateOfDay DATE)
   RETURNS @DisplayTable TABLE
   (
	FirstName VARCHAR(50),
	LastName VARCHAR(50),
	PhoneNumber	NVARCHAR(30),
	SalesPerson VARCHAR(50),
	ProductName VARCHAR(50),
	AddressOfThePerson VARCHAR(60),
	VendorName VARCHAR(1000)
   )
	AS
	BEGIN
	INSERT @DisplayTable
	SELECT 
	P.FirstName,P.LastName,PP.PhoneNumber,P.FirstName [SalesPersonName],Pro.Name,
	A.AddressLine1[Address],
	SUBSTRING((
	SELECT ',' + V.Name AS [text()]FROM Purchasing.Vendor V,Purchasing.PurchaseOrderHeader POH
	WHERE POH.VendorID=V.BusinessEntityID
	AND POH.OrderDate='20031014'
	FOR XML PATH('')
	),2,1000)[Strings]
	FROM Person.Person P,Sales.SalesPerson SP,Sales.Customer C,Person.PersonPhone PP,
	Sales.SalesOrderDetail SOD, Sales.SalesOrderHeader SOH,
	Production.Product PRO,Person.Address A
	WHERE P.BusinessEntityID = SP.BusinessEntityID 
	AND SP.TerritoryID = C.TerritoryID
	AND SOH.CustomerID=C.CustomerID
	AND P.BusinessEntityID=PP.BusinessEntityID
	AND SOH.SalesOrderID=SOD.SalesOrderID
	AND PRO.ProductID=SOD.ProductID
	AND SOH.ShipToAddressID=A.AddressID
	AND SOH.OrderDate='20031014'
	AND PRO.DaysToManufacture>3	
	RETURN 
	END
	
DECLARE @startDate DATETIME = '20010713'
SELECT * FROM Display(@startDate)

--11

CREATE FUNCTION Display2(@DateOfDay DATE)
   RETURNS @DisplayTable TABLE
   (
	FirstName VARCHAR(50),
	LastName VARCHAR(50),
	PhoneNumber	NVARCHAR(30),
	SalesPerson VARCHAR(50),
	ProductName VARCHAR(50),
	AddressOfThePerson VARCHAR(60),
	VendorName VARCHAR(1000)
   )
	AS
	BEGIN
	INSERT @DisplayTable
	SELECT 
	DISTINCT(P.FirstName),P.LastName,PP.PhoneNumber,P.FirstName [SalesPersonName],Pro.Name,
	A.AddressLine1[Address],
	SUBSTRING((
	SELECT ',' + V.Name AS [text()]FROM Purchasing.Vendor V,Purchasing.PurchaseOrderHeader POH
	WHERE POH.VendorID=V.BusinessEntityID
	AND POH.OrderDate='20031014'
	FOR XML PATH('')
	),2,1000)[Strings]
	FROM Person.Person P,Sales.SalesPerson SP,Sales.Customer C,Person.PersonPhone PP,
	Sales.SalesOrderDetail SOD, Sales.SalesOrderHeader SOH,
	Production.Product PRO,Person.Address A
	WHERE P.BusinessEntityID = SP.BusinessEntityID 
	AND SP.TerritoryID = C.TerritoryID
	AND SOH.CustomerID=C.CustomerID
	AND P.BusinessEntityID=PP.BusinessEntityID
	AND SOH.SalesOrderID=SOD.SalesOrderID
	AND PRO.ProductID=SOD.ProductID
	AND SOH.ShipToAddressID=A.AddressID
	AND SOH.OrderDate='20031014'
	AND PRO.DaysToManufacture>3	
	RETURN 
	END
	
DECLARE @startDate DATETIME = '20010713'
SELECT * FROM Display2(@startDate)
