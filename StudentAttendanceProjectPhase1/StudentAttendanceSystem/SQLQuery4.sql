-- 1. Remove any previous test data
DELETE FROM Attendance WHERE StudentID = 'TEST01';
DELETE FROM Grades WHERE StudentID = 'TEST01';
DELETE FROM Students WHERE StudentID = 'TEST01';

-- 2. Insert test student (without Contact column)
INSERT INTO Students (StudentID, Name, Class, Section)
VALUES ('TEST01', 'Test Student', '10', 'A');

-- 3. Insert failing grades (to trigger grade alerts)
INSERT INTO Grades (StudentID, Subject, Score)
VALUES 
('TEST01', 'Math', 40),
('TEST01', 'Science', 30),
('TEST01', 'English', 55);

-- 4. Insert low attendance (to trigger attendance alerts)
INSERT INTO Attendance (StudentID, Date, Status)
VALUES 
('TEST01', '2025-11-01', 'Present'),
('TEST01', '2025-11-02', 'Absent'),
('TEST01', '2025-11-03', 'Absent'),
('TEST01', '2025-11-04', 'Absent'),
('TEST01', '2025-11-05', 'Absent');
