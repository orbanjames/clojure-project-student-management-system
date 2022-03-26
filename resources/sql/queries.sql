-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(id, user_name, first_name, last_name, email, password, contact)
VALUES (:id, :user_name, :first_name, :last_name, :email, :password, :contact);

-- :name update-user! :! :n
-- :doc updates an existing user record
UPDATE users
SET user_name = :user_name, first_name = :first_name, last_name = :last_name, email = :email, password = :password, contact = :contact
WHERE id = :id;

-- :name get-user :? :1
-- :doc retrieves a user record given the id
SELECT * FROM users
WHERE id = :id;

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
DELETE FROM users
WHERE id = :id;



