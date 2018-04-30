set autocommit off;

CREATE TABLE Department
(
	ID integer NOT NULL Primary Key,
	Name varchar2(256) NOT NULL
);

CREATE TABLE Advisor
(
	ID integer NOT NULL Primary Key,
	Name varchar2(256) NOT NULL,
	Position varchar2(256) NOT NULL,
	DepartmentID integer NOT NULL REFERENCES Department (ID),
	TelephoneNumber integer NOT NULL,
	Email varchar2(256) NOT NULL
);

CREATE TABLE Student
(
	ID integer NOT NULL Primary Key,
	Name varchar2(256) NOT NULL,
	Address varchar2(256) NOT NULL,
	PhoneNumber integer NOT NULL,
	Email varchar2(256) NOT NULL,
	Gender char(1) NOT NULL CHECK (Gender IN ('M', 'F', 'O')),
	DOB date NOT NULL,
	Category varchar2(256) NOT NULL CHECK (Category IN ('freshman', 'sophmore', 'junior', 'senior', 'graduate')),
	MajorID integer NULL REFERENCES Department (ID),
	MinorID integer NULL REFERENCES Department (ID),
	AdvisorID integer NOT NULL REFERENCES Advisor (ID)
);

CREATE TABLE JobTitle
(
    ID integer NOT NULL Primary Key,
    Name varchar2(256) NOT NULL
);

CREATE TABLE Staff
(
    ID integer NOT NULL Primary Key,
    Name varchar2(256) NOT NULL,
    Email varchar2(256) NOT NULL,
    HomeAddress varchar2(256) NOT NULL,
    DOB DATE NOT NULL,
    Gender varchar2(256) NOT NULL,
    JobTitleID integer NOT NULL REFERENCES JobTitle (ID),
	Location varchar2(256) NOT NULL
);

CREATE TABLE Building
(
    ID integer NOT NULL Primary Key,
    Name varchar2(256) NULL,
    Address varchar2(256) NOT NULL,
	IsApartment integer NOT NULL,
    TelephoneNumber integer NULL,
    ManagerID integer NOT NULL REFERENCES Staff (ID),
	NumberOfStudents integer NULL
);

CREATE TABLE Room
(
    ID integer NOT NULL Primary Key,
    RoomNumber integer NOT NULL,
    BuildingID integer NOT NULL REFERENCES Building (ID),
    StudentID integer NOT NULL REFERENCES Student (ID),
    MonthlyRentRate integer NOT NULL
);

CREATE TABLE StudentLease
(
    ID integer NOT NULL Primary Key,
    RoomID integer NOT NULL REFERENCES Room (ID),
    StudentID integer NOT NULL REFERENCES Student (ID),
    Duration integer NOT NULL,
    MonthlyCost integer NOT NULL,
    LeaseStartDate Date NOT NULL
);

CREATE TABLE Invoice
(
	ID integer NOT NULL Primary Key,
	LeaseID integer NOT NULL REFERENCES StudentLease (ID),
	Semester varchar2(256) NOT NULL,
	PaymentDue DATE NOT NULL,
	DatePaid DATE
);

CREATE TABLE Inspection
(
    ID integer NOT NULL Primary Key,
    InspectionDate DATE NOT NULL,
    InspectorID integer NOT NULL REFERENCES Staff (ID),
    InspectedRoomID integer NOT NULL REFERENCES Room (ID),
    Condition varchar2(256) NOT NULL,
    Action varchar2(256) NOT NULL
);

commit;
set autocommit on;
