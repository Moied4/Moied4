-- Create the tables for Student Attendance Management System

-- 1. Users Table
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) UNIQUE NOT NULL,
    PasswordHash NVARCHAR(255) NOT NULL,
    Role NVARCHAR(20) NOT NULL -- e.g., Admin or Teacher
);

-- 2. Students Table
CREATE TABLE Students (
    StudentID NVARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Class NVARCHAR(50),
    Section NVARCHAR(50),
    ContactInfo NVARCHAR(100)
);

-- 3. Attendance Table
CREATE TABLE Attendance (
    AttendanceID INT PRIMARY KEY IDENTITY(1,1),
    StudentID NVARCHAR(50) NOT NULL,
    Date DATE NOT NULL,
    Status NVARCHAR(20) NOT NULL, -- Present, Absent, Late, Excused
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
);

-- 4. Grades Table
CREATE TABLE Grades (
    GradeID INT PRIMARY KEY IDENTITY(1,1),
    StudentID NVARCHAR(50) NOT NULL,
    Subject NVARCHAR(50) NOT NULL,
    Score DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
);

-- Insert sample admin user 
INSERT INTO Users (Username, PasswordHash, Role)
VALUES ('admin', 'your_hashed_password_here', 'Admin');
