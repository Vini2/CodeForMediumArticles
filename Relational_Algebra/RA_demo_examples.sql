-- Projection example
SELECT DISTINCT member_id FROM borrow;
SELECT DISTINCT member_id,book_id FROM borrow;

-- Selection example
SELECT * FROM member WHERE date_of_birth='1997-10-21';

-- Rename example
WITH library_member AS (SELECT * FROM member) SELECT * FROM library_member;

-- Cross product example
SELECT * FROM member,borrow;

-- Natural join example
SELECT * FROM member NATURAL JOIN borrow;

-- Conditional join example
WITH B1 AS (SELECT * from borrow), B2 AS (SELECT * from borrow)
SELECT * FROM B1 INNER JOIN B2 ON B1.member_id=B2.member_id AND B1.book_id<>B2.book_id;

-- Union example
SELECT book_id FROM member natural join borrow where name='Charlie' UNION
SELECT book_id FROM member natural join borrow where name='Mike';

-- Intersection example
SELECT member_id FROM book natural join borrow where name='Fences' INTERSECT
SELECT member_id FROM book natural join borrow where name='Inheritance';

-- Set difference example
SELECT member_id,name,date_of_birth FROM member EXCEPT
SELECT member_id,name,date_of_birth FROM member natural join borrow;