-- Create Member table
CREATE TABLE member(
   member_id INT PRIMARY KEY,
   name VARCHAR (50) NOT NULL,
   date_of_birth DATE NOT NULL
);

-- Add records to Member table
INSERT INTO member VALUES (1, 'Alice', '1995-03-03');
INSERT INTO member VALUES (2, 'Bob', '1993-03-05');
INSERT INTO member VALUES (3, 'Charlie', '1997-10-21');
INSERT INTO member VALUES (4, 'Mike', '1992-09-16');
INSERT INTO member VALUES (5, 'Katie', '1997-10-21');


-- Create Book table
CREATE TABLE book(
   book_id INT PRIMARY KEY,
   name VARCHAR (50) NOT NULL,
   author VARCHAR (50) NOT NULL
);

-- Add records to Book table
INSERT INTO book VALUES (1, 'Inferno', 'Dan Brown');
INSERT INTO book VALUES (2, 'Ash', 'Malinda Lo');
INSERT INTO book VALUES (3, 'Fences', 'August Wilson');
INSERT INTO book VALUES (4, 'Origin', 'Dan Brown');
INSERT INTO book VALUES (5, 'Inheritance', 'Malinda Lo');


-- Create Borrow table
CREATE TABLE borrow(
   member_id INT REFERENCES member(member_id),
   book_id INT REFERENCES book(book_id),
   borrow_date DATE NOT NULL,
   return_date DATE NOT NULL
);

-- Add records to Borrow table
INSERT INTO borrow VALUES (1, 1, '2020-03-02', '2020-03-12');
INSERT INTO borrow VALUES (3, 5, '2020-03-05', '2020-03-15');
INSERT INTO borrow VALUES (3, 3, '2020-03-10', '2020-03-20');
INSERT INTO borrow VALUES (4, 2, '2020-03-13', '2020-03-23');
INSERT INTO borrow VALUES (5, 4, '2020-03-13', '2020-03-13');