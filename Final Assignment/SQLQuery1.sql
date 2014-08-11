CREATE TABLE INSTITUTIONS
(
	Title VARCHAR(100),
	Descript VARCHAR(400),
	Location VARCHAR(100),
	Branches VARCHAR(200),
	URL VARCHAR(500),
	PRIMARY KEY (Title)
)

INSERT INTO INSTITUTIONS 
VALUES('MIT','Madras Institute of Technology','Chennai','Chrompet','www.mit.edu.in');

SELECT * FROM INSTITUTIONS

drop table INSTITUTIONS

UPDATE INSTITUTIONS SET Branches='Guindy'

CREATE TABLE COURSES
(
	Name VARCHAR(50) NOT NULL PRIMARY KEY,
	Descript VARCHAR(500),
	Duration INT,
	AdmissionProcess VARCHAR(100),
	Eligibility FLOAT,
	Title VARCHAR(100)
	FOREIGN KEY(Title) REFERENCES INSTITUTIONS(Title)
)

DROP TABLE COURSES

INSERT INTO COURSES 
VALUES('B.E in Computer Science','Department of Computer Science and Engineering (DCSE), formerly known as School of Computer Science and Engineering, was carved out of the School of Electronics and Communication Engineering in September 1987. DCSE function as one of the University Department having academic autonomy of having its own syllabus and curriculum. It offers  undergraduate, postgraduate and research programmes in various disciplines of Computer Science & Engineering.',4,'Counselling',75.00,'CEG');

SELECT * FROM COURSES