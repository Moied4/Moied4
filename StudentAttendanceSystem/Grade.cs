namespace StudentAttendanceSystem
{
    public class Grade
    {
        public string StudentID { get; set; }      // Link to the student
        public string Subject { get; set; }        // Subject name, e.g., Math
        public double Score { get; set; }          // Numeric score
        public string StudentName { get; set; }     //StudentName
        public string LetterGrade                    // auto-convert score to letter grade
        {
            get
            {
                if (Score >= 90) return "A";
                if (Score >= 80) return "B";
                if (Score >= 70) return "C";
                if (Score >= 60) return "D";
                return "F";
            }
        }
    }
}


