-- Schema created and users are being created for the schema
--1
--2
--3
CREATE LOGIN master 
    WITH PASSWORD = '123456';
    
CREATE USER master FOR LOGIN master;

grant control to master;

create login readuser with password='123456';

create user readuser for login readuser;

EXEC sp_addrolemember N'db_datareader', N'readuser'

USE mydb

-- Appropriate tables are created in the database and normalised such that the queries
-- can execute faster on the database

--4

CREATE TABLE au_2014.customer (
	cust_id VARCHAR(255) NOT NULL,
	cust_name VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(32) NOT NULL,
	mobile_no VARCHAR(20) NOT NULL,
	e_mail VARCHAR(255) NOT NULL,
	cust_addr VARCHAR(255) NOT NULL,
	dob DATE NOT NULL,
	secret_qn VARCHAR(255) NOT NULL,
	secret_ans VARCHAR(255) NOT NULL,
	PRIMARY KEY(cust_id)
);

CREATE TABLE au_2014.account (
    account_no VARCHAR(255) NOT NULL, 
    account_type VARCHAR(255) NOT NULL CHECK (account_type in ('savings','current')),
    balance INT NOT NULL CHECK (balance>0),
    cust_id VARCHAR(255) NOT NULL,
	bank_id VARCHAR(255) NOT NULL,
    PRIMARY KEY(account_no),
    FOREIGN KEY (cust_id) REFERENCES au_2014.customer(cust_id),
    FOREIGN KEY (bank_id) REFERENCES au_2014.bank(bank_id)
);

CREATE TABLE au_2014.transact (
	trans_id VARCHAR(255) NOT NULL,
	amount INT NOT NULL CHECK (amount>0),
	date_time DATETIME NOT NULL,
	PRIMARY KEY(trans_id)
);

CREATE TABLE au_2014.transact_details (
	trans_id VARCHAR(255) NOT NULL,
	from_account VARCHAR(255) NOT NULL,
	to_account VARCHAR(255) NOT NULL,
	from_cust_id VARCHAR(255) NOT NULL,
	to_cust_id VARCHAR(255) NOT NULL,
	FOREIGN KEY (trans_id) REFERENCES au_2014.transact(trans_id),
	FOREIGN KEY (from_account) REFERENCES au_2014.account(account_no),
	FOREIGN KEY (to_account) REFERENCES au_2014.account(account_no),	
	FOREIGN KEY (from_cust_id) REFERENCES au_2014.customer(cust_id),
	FOREIGN KEY (to_cust_id) REFERENCES au_2014.customer(cust_id)
);

CREATE TABLE au_2014.bank (
	bank_id VARCHAR(255) NOT NULL PRIMARY KEY,
	bank_name VARCHAR(255) NOT NULL,
	bank_branch VARCHAR(255) NOT NULL,
);

CREATE TABLE au_2014.cust_account (
	cust_id VARCHAR(255) NOT NULL,
	account_no VARCHAR(255) NOT NULL,
);

--5

CREATE PROCEDURE TopCustomers(@cust_account_no BIGINT) AS 
BEGIN
	SELECT TOP 10 * 
	FROM au_2014.transact T,au_2014.transact_details TD
	WHERE TD.trans_id=T.trans_id 
	AND TD.from_account like @cust_account_no
	order by T.date_time desc
END


CREATE PROCEDURE TopTransactions(@CustUserName NVARCHAR(50)) AS 
BEGIN
	SELECT TOP 20 Customer1.[cust_addr] AS FromAddress, 
		Customer2.[cust_addr] AS ToAddress, transact.[Amount] AS Amount 
	FROM au_2014.[Customers] as custs1 
		INNER JOIN au_2014.transact AS Trans
		ON Customer1.[cust_id] = Trans.[from_account] 
		INNER JOIN  au2014.[Customer] AS Customer2 
		ON Trans.[to_account] = Customer2.[cust_id]
	WHERE Customer1.[Username] LIKE @CustUserName
	ORDER  BY Trans.[Amount] DESC
END

--6
--Denormalization of table transactions to add customer name--

ALTER TABLE au_2014.transact ADD cust_name VARCHAR(255) NOT NULL; 
