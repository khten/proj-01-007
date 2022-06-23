CREATE TABLE IF NOT EXISTS employees (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	pwd VARCHAR(255),
	role_name VARCHAR(255),
	username VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tickets (
	id SERIAL PRIMARY KEY,
	description VARCHAR(50),
	employeeId INTEGER REFERENCES employees(id),
	status BOOLEAN DEFAULT FALSE
);

INSERT INTO employees (first_name, last_name, pwd, role_name, username)
	VALUES ('Admin', 'Admin', 'Admin', 'Admin', 'Admin');
	
SELECT * FROM employees;