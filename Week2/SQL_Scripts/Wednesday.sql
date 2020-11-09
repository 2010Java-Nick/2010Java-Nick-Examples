--2.1
/*SQL Lab*/
/*2.1 SELECT
task – Select all records from the Employee table. 
Task – Select all records from the Employee table where last name is King.
Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.*/

SELECT *
FROM employee;
SELECT *
FROM employee
WHERE lastname = 'King';
SELECT *
FROM employee
WHERE firstname = 'Andrew' AND reportsto IS NULL;


/*2.2 ORDER BY
Task – Select all albums in Album table and sort result set in descending order by title.
Task – Select first name from Customer and sort result set in ascending order by city*/

SELECT *
FROM album
ORDER BY title DESC;

SELECT firstname
FROM customer
ORDER BY city ASC;

/*2.3 INSERT INTO
Task – Insert two new records into Genre table 
Task – Insert two new records into Employee table
Task – Insert two new records into Customer table*/

INSERT INTO Genre
    (GenreId, Name)
VALUES
    (26, 'fnu');
INSERT INTO Employee
    (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES
    (122, N'Adams', N'Andrew', N'General Manager', '1962/2/18', '2002/8/14', N'11120 Jasper Ave NW', N'Edmonton', N'AB', N'Canada', N'T5K 2N1', N'+1 (780) 428-9482', N'+1 (780) 428-3457', N'andrew@chinookcorp.com');
INSERT INTO Employee
    (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES
    (123, N'Edwards', N'Nancy', N'Sales Manager', 1, '1958/12/8', '2002/5/1', N'825 8 Ave SW', N'Calgary', N'AB', N'Canada', N'T2P 2T3', N'+1 (403) 262-3443', N'+1 (403) 262-3322', N'nancy@chinookcorp.com');

INSERT INTO Customer
    (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)
VALUES
    (122, N'Lu�s', N'Gon�alves', N'Embraer - Empresa Brasileira de Aeron�utica S.A.', N'Av. Brigadeiro Faria Lima, 2170', N'S�o Jos� dos Campos', N'SP', N'Brazil', N'12227-000', N'+55 (12) 3923-5555', N'+55 (12) 3923-5566', N'luisg@embraer.com.br', 3);
INSERT INTO Customer
    (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId)
VALUES
    (123, N'Leonie', N'K�hler', N'Theodor-Heuss-Stra�e 34', N'Stuttgart', N'Germany', N'70174', N'+49 0711 2842222', N'leonekohler@surfeu.de', 5);


/*2.4 UPDATE
Task – Update Aaron Mitchell in Customer table to Robert Walter 
Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”*/

UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell'

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival'

/*2.5 LIKE
Task – Select all invoices with a billing address like “T%”*/
SELECT *
FROM invoice
WHERE billingaddress LIKE 'T%';



/*2.6 BETWEEN
Task – Select all invoices that have a total between 15 and 50
Task – Select all employees hired between 1st of June 2003 and 1st of March 2004*/

SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;
SELECT *
FROM employee
WHERE hiredate BETWEEN '2003-06-01' AND '2004-03-01';



/*2.7 DELETE
Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).*/

DELETE FROM customer
WHERE firstname = 'Robert' AND lastname ='Walter';

/*3.0	SQL Functions
In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database*/

/*3.1 System Defined Functions
Task – Create a function that returns the current time.
Task – create a function that returns the length of a mediatype from the mediatype table*/

CREATE OR REPLACE FUNCTION getCurrentTime
() RETURNS timestamp
WITH TIME ZONE AS $$
SELECT NOW();
$$ LANGUAGE SQL


CREATE FUNCTION getLengthOfMediaType() RETURNS bigint AS $$
SELECT COUNT(*)
FROM mediatype;
$$ LANGUAGE SQL

/*3.2 System Defined Aggregate Functions
Task – Create a function that returns the average total of all invoices
Task – Create a function that returns the most expensive track*/


CREATE FUNCTION getAvgOfAllInvoices() RETURNS decimal AS $$
SELECT AVG(total)
FROM invoice;
$$ LANGUAGE SQL


CREATE FUNCTION mostExpTrack() RETURNS numeric AS $$
SELECT MAX(unitprice)
FROM track;
$$ LANGUAGE SQL


/*3.3 User Defined Scalar Functions
Task – Create a function that returns the average price of invoiceline items in the invoiceline table*/

CREATE FUNCTION avgPriceOfInvoiceLine() RETURNS numeric AS $$
SELECT AVG(unitprice)
FROM invoiceline;
$$ LANGUAGE SQL

/*3.4 User Defined Table Valued Functions
Task – Create a function that returns all employees who are born after 1968.*/

CREATE OR REPLACE FUNCTION allEmpBornAfter
() RETURNS SETOF varchar AS $$
SELECT firstname
FROM employee
WHERE birthdate >= timestamp
'1969-01-01' ;
$$ LANGUAGE SQL

/*4.0 Stored Procedures
 In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.*/

/*4.1 Basic Stored Procedure
Task – Create a stored procedure that selects the first and last names of all the employees.*/

/*4.2 Stored Procedure Input Parameters
Task – Create a stored procedure that updates the personal information of an employee.
Task – Create a stored procedure that returns the managers of an employee.*/


/*4.3 Stored Procedure Output Parameters
Task – Create a stored procedure that returns the name and company of a customer.*/

CREATE OR REPLACE PROCEDURE firstAndLastName
() AS $$
SELECT firstname, lastname
FROM employee;
$$ LANGUAGE SQL

CREATE OR REPLACE PROCEDURE updateFirstName
(empId integer , newFirstName varchar) AS $$
UPDATE employee 
 SET firstname = newFirstName
 WHERE employeeid = empId
$$ LANGUAGE SQL

CREATE OR REPLACE FUNCTION nameAndCompany
(custId integer) RETURNS TABLE
(fname varchar, company varchar) AS $$
SELECT firstname, company
FROM customer
WHERE customerid = custId;
$$ LANGUAGE SQL

/*5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table*/

CREATE PROCEDURE delInvoice(invId integer) AS $$
DELETE FROM invoiceline WHERE invoicelineid = invId;
DELETE FROM invoice WHERE invoiceid = invId;
$$ LANGUAGE SQL


CREATE PROCEDURE  insrtRecord(fname integer, custId interger) AS $$
BEGIN;
    INSERT INTO customer
        (customerid , firstname)
    VALUES
        (custId , fname)
END;
$$ LANGUAGE SQL

/*6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.*/
/*6.1 AFTER/FOR
Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
Task – Create an after update trigger on the album table that fires after a row is inserted in the table
Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.*/

CREATE FUNCTION insert_employee() 
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS
	
	$$
BEGIN
    END;
$$

CREATE TRIGGER insert_employee_trigger
	AFTER
INSERT
	ON
employee
FOR
EACH
ROW
EXECUTE PROCEDURE insert_employee
()

CREATE FUNCTION album_update() 
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS
	
	$$
BEGIN
    END;
$$

CREATE TRIGGER album_update_trigger
	AFTER
UPDATE
	ON album 
	FOR EACH ROW
EXECUTE PROCEDURE album_update
()


CREATE FUNCTION customer_delete() 
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS
	
	$$
BEGIN
    END;
$$

CREATE TRIGGER customer_delete_trigger
	AFTER
DELETE
	ON customer 
	FOR EACH
ROW
EXECUTE PROCEDURE customer_delete
()

/*7.0 JOINS
In this section you will be working with combing various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
7.1 INNER
Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
7.2 OUTER
Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
7.3 RIGHT
Task – Create a right join that joins album and artist specifying artist name and title.
7.4 CROSS
Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
7.5 SELF
Task – Perform a self-join on the employee table, joining on the reportsto column.*/

SELECT firstname, invoiceid
FROM customer INNER JOIN invoice ON customer.customerid = invoice.customerid;

SELECT customer.customerid, firstname, lastname, invoiceid, total
FROM customer
    FULL JOIN invoice ON invoice.customerid = customer.customerid;

SELECT title, name
FROM album
    RIGHT JOIN artist ON album.artistid = artist.artistid;

SELECT *
FROM artist 
CROSS JOIN album
ORDER BY artist.name ASC;

SELECT e1.firstname, e2.firstname
FROM
    employee AS e1
    JOIN employee AS e2 ON e1.reportsto = e2.reportsto;


