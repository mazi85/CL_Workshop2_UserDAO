CREATE TABLE users (
  id INT(11) AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE,
  username VARCHAR(255),
  password VARCHAR(60),
  PRIMARY KEY (id)
);

INSERT INTO users (email, username, password)  VALUES (?,?,?);

DELETE FROM users WHERE id=?;

SELECT id FROM users WHERE email=?;
SELECT * FROM users;
SELECT * FROM users WHERE id=?;

UPDATE users SET password=? WHERE id=?;
UPDATE users SET email=?, username=? WHERE id=?;
