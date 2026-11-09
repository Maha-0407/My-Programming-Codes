CREATE DATABASE student_attendance;

USE student_attendance;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    roll_no VARCHAR(30),
    department VARCHAR(50),
    year INT
);

INSERT INTO students(name, roll_no, department, year)
VALUES
('Anu', '101', 'CSE', 3),
('Priya', '102', 'CSE', 3),
('Sneha', '103', 'CSE', 3);