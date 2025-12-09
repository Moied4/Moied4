-- Drop tables if they exist in correct order (FK dependencies)
IF OBJECT_ID('Grades', 'U') IS NOT NULL DROP TABLE Grades;
IF OBJECT_ID('Attendance', 'U') IS NOT NULL DROP TABLE Attendance;
IF OBJECT_ID('Students', 'U') IS NOT NULL DROP TABLE Students;

-- Create Students table
CREATE TABLE Students (
    ID VARCHAR(10) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Class VARCHAR(50),
    Section VARCHAR(50),
    Contact VARCHAR(50)
);

-- Insert sample students
INSERT INTO Students (ID, Name, Class, Section, Contact) VALUES
('S001', 'John Calderon', '10', 'A', '123-456-7890'),
('S002', 'Bobby Jackson', '10', 'B', '234-567-8901'),
('S003', 'Desire Brown', '10', 'A', '345-678-9012');

-- Create Attendance table
CREATE TABLE Attendance (
    AttendanceID INT IDENTITY(1,1) PRIMARY KEY,
    StudentID VARCHAR(10) NOT NULL,
    Date DATE NOT NULL,
    Status VARCHAR(10) NOT NULL,
    FOREIGN KEY (StudentID) REFERENCES Students(ID)
);

-- Insert sample attendance
INSERT INTO Attendance (StudentID, Date, Status) VALUES
('S001', '2025-11-13', 'Present'),
('S002', '2025-11-13', 'Absent'),
('S003', '2025-11-13', 'Late');

-- Create Grades table
CREATE TABLE Grades (
    GradeID INT IDENTITY(1,1) PRIMARY KEY,
    StudentID VARCHAR(10) NOT NULL,
    Subject VARCHAR(50) NOT NULL,
    Score DECIMAL(5,2),
    FOREIGN KEY (StudentID) REFERENCES Students(ID)
);

-- Insert sample grades
INSERT INTO Grades (StudentID, Subject, Score) VALUES
('S001', 'Mathematics', 95.50),
('S002', 'English', 88.00),
('S003', 'Science', 76.25);
