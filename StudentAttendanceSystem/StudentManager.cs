namespace StudentAttendanceSystem
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    public class StudentManager
    {
        // A non-static list to store all students (instance-based storage)
        private List<Student> students = new List<Student>();

        // Add a new student
        public void AddStudent(Student student)
        {
            students.Add(student);
        }

        // Get all students
        public List<Student> GetAllStudents()
        {
            return students;
        }

        // Find a student by ID
        public Student GetStudentByID(string studentID)
        {
            return students.FirstOrDefault(s => s.ID == studentID);
        }

        // Update student details by ID
        public bool UpdateStudent(string studentID, Student updatedStudent)
        {
            var student = GetStudentByID(studentID);
            if (student != null)
            {
                student.Name = updatedStudent.Name;
                student.Class = updatedStudent.Class;
                student.Section = updatedStudent.Section;
                student.Contact = updatedStudent.Contact;
                return true;
            }
            return false;
        }

        // Delete a student by ID
        public bool DeleteStudent(string studentID)
        {
            var student = GetStudentByID(studentID);
            if (student != null)
            {
                students.Remove(student);
                return true;
            }
            return false;
        }

        // Sample method to display all students (for debugging/testing)
        public void DisplayAllStudents()
        {
            foreach (var student in students)
            {
                Console.WriteLine($"ID: {student.ID}, Name: {student.Name}, Class: {student.Class}, Section: {student.Section}, Contact: {student.Contact}");
            }
        }
    }
}
