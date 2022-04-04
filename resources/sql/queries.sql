-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(id, username, firstname, lastname, email, password, contact)
VALUES (:id, :username, :firstname, :lastname, :email, :password, :contact);

-- :name update-user! :! :n
-- :doc updates an existing user record
UPDATE users
SET username = :username, firstname = :firstname, lastname = :lastname, email = :email, password = :password, contact = :contact
WHERE id = :id;

-- :name get-user :? :1
-- :doc retrieves a user record given the id
SELECT * FROM users
WHERE id = :id;

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
DELETE FROM users
WHERE id = :id;


-- :name create-student! :! :n
-- :doc creates a new student record
INSERT INTO student
(id, firstname, lastname, programmeofstudy, email, yearofaward, country, contact)
VALUES (:id, :firstname, :lastname, :programmeofstudy :email, :yearofaward, :country :contact);

-- :name update-student! :! :n
-- :doc updates an existing students record
UPDATE student
SET  firstname = :firstname, lastname = :lastname, programmeofstudy = :programmeofstudy, email = :email, yearofaward = :yearofaward, country = :country, contact = :contact
WHERE id = :id;

-- :name get-student :? :1
-- :doc retrieves a student record given the id
SELECT * FROM student
WHERE id = :id;

-- :name delete-student! :! :n
-- :doc deletes a student record given the id
DELETE FROM student
WHERE id = :id;

-- :name create-university! :! :n
-- :doc creates a new university record
INSERT INTO university
(id, name, address, location)
VALUES (:id, :name, :address, :location);

-- :name update-university! :! :n
-- :doc updates an existing university record
UPDATE university
SET name = :name, address = :address, location = :location
WHERE id = :id;

-- :name get-university :? :1
-- :doc retrieves a university record given the id
SELECT * FROM university
WHERE id = :id;

-- :name delete-university! :! :n
-- :doc deletes a university record given the id
DELETE FROM university
WHERE id = :id;

-- :name create-faculty! :! :n
-- :doc creates a new faculty record
INSERT INTO faculty
(id, name, address)
VALUES (:id, :name, :address);

-- :name update-faculty! :! :n
-- :doc updates an existing faculty record
UPDATE faculty
SET name = :name, address = :address
WHERE id = :id;

-- :name get-faculty :? :1
-- :doc retrieves a faculty record given the id
SELECT * FROM faculty
WHERE id = :id;

-- :name delete-faculty! :! :n
-- :doc deletes a faculty record given the id
DELETE FROM faculty
WHERE id = :id;

-- :name create-course! :! :n
-- :doc creates a new course record
INSERT INTO course
(id, title, code)
VALUES (:id, :title, :code);

-- :name update-course! :! :n
-- :doc updates an existing course record
UPDATE course
SET title = :title, code = :code
WHERE id = :id;

-- :name get-course :? :1
-- :doc retrieves a course record given the id
SELECT * FROM course
WHERE id = :id;

-- :name delete-course! :! :n
-- :doc deletes a course record given the id
DELETE FROM course
WHERE id = :id;


