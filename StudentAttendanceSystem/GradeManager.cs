using System;
using System.Collections.Generic;
using System.Linq;

namespace StudentAttendanceSystem
{
    public class GradesManager
    {
        private List<Grade> grades = new List<Grade>();

        // Add a new grade
        public void AddGrade(Grade grade)
        {
            grades.Add(grade);
        }

        // Get all grades
        public List<Grade> GetAllGrades()
        {
            return grades;
        }

        // Get grades by student ID
        public List<Grade> GetGradesByStudent(string studentID)
        {
            return grades.Where(g => g.StudentID == studentID).ToList();
        }

        // Update a grade
        public bool UpdateGrade(string studentID, string subject, double newScore)
        {
            var grade = grades.FirstOrDefault(g => g.StudentID == studentID && g.Subject == subject);
            if (grade != null)
            {
                grade.Score = newScore;
                return true;
            }
            return false;
        }

        // Delete a grade
        public bool DeleteGrade(string studentID, string subject)
        {
            var grade = grades.FirstOrDefault(g => g.StudentID == studentID && g.Subject == subject);
            if (grade != null)
            {
                grades.Remove(grade);
                return true;
            }
            return false;
        }
    }
}
