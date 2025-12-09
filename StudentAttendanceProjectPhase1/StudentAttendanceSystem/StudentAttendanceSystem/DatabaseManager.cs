using System;
using System.Data;
using System.Data.SqlClient;

namespace StudentAttendanceSystem
{
    public class DatabaseManager
    {
        // --- Defined database connection string here ---
        private string connectionString = @"Data Source=(localdb)\MSSQLLocalDB;Initial Catalog=StudentAttendanceDB;Integrated Security=True";

        // --- CRUD FOR STUDENTS ---
        public void AddStudent(string id, string name, string className, string section, string contact)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "INSERT INTO Students (StudentID, Name, Class, Section, ContactInfo) VALUES (@StudentID, @Name, @Class, @Section, @ContactInfo)";
                using (SqlCommand cmd = new SqlCommand(query, con))
                {
                    cmd.Parameters.AddWithValue("@StudentID", id);
                    cmd.Parameters.AddWithValue("@Name", name);
                    cmd.Parameters.AddWithValue("@Class", className);
                    cmd.Parameters.AddWithValue("@Section", section);
                    cmd.Parameters.AddWithValue("@ContactInfo", contact);
                    con.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }

        public DataTable GetAllStudents()
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "SELECT * FROM Students";
                using (SqlDataAdapter adapter = new SqlDataAdapter(query, con))
                {
                    DataTable dt = new DataTable();
                    adapter.Fill(dt);
                    return dt;
                }
            }
        }

        // --- CRUD FOR ATTENDANCE ---
        public void AddAttendance(string studentId, DateTime date, string status)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "INSERT INTO Attendance (StudentID, Date, Status) VALUES (@sid, @d, @s)";
                using (SqlCommand cmd = new SqlCommand(query, con))
                {
                    cmd.Parameters.AddWithValue("@sid", studentId);
                    cmd.Parameters.AddWithValue("@d", date);
                    cmd.Parameters.AddWithValue("@s", status);
                    con.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }

        public DataTable GetAllAttendance()
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "SELECT * FROM Attendance";
                using (SqlDataAdapter adapter = new SqlDataAdapter(query, con))
                {
                    DataTable dt = new DataTable();
                    adapter.Fill(dt);
                    return dt;
                }
            }
        }

        public void UpdateAttendance(int attendanceId, string studentId, DateTime date, string status)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "UPDATE Attendance SET StudentID=@sid, Date=@d, Status=@s WHERE AttendanceID=@id";
                using (SqlCommand cmd = new SqlCommand(query, con))
                {
                    cmd.Parameters.AddWithValue("@sid", studentId);
                    cmd.Parameters.AddWithValue("@d", date);
                    cmd.Parameters.AddWithValue("@s", status);
                    cmd.Parameters.AddWithValue("@id", attendanceId);
                    con.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }

        public void DeleteAttendance(int attendanceId)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "DELETE FROM Attendance WHERE AttendanceID=@id";
                using (SqlCommand cmd = new SqlCommand(query, con))
                {
                    cmd.Parameters.AddWithValue("@id", attendanceId);
                    con.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }

        // --- CRUD FOR GRADES ---
        public void AddGrade(string studentId, string subject, decimal score)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "INSERT INTO Grades (StudentID, Subject, Score) VALUES (@sid, @sub, @sc)";
                using (SqlCommand cmd = new SqlCommand(query, con))
                {
                    cmd.Parameters.AddWithValue("@sid", studentId);
                    cmd.Parameters.AddWithValue("@sub", subject);
                    cmd.Parameters.AddWithValue("@sc", score);
                    con.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }

        public DataTable GetAllGrades()
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "SELECT * FROM Grades";
                using (SqlDataAdapter adapter = new SqlDataAdapter(query, con))
                {
                    DataTable dt = new DataTable();
                    adapter.Fill(dt);
                    return dt;
                }
            }
        }

        public void UpdateGrade(int gradeId, string studentId, string subject, decimal score)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "UPDATE Grades SET StudentID=@sid, Subject=@sub, Score=@sc WHERE GradeID=@id";
                using (SqlCommand cmd = new SqlCommand(query, con))
                {
                    cmd.Parameters.AddWithValue("@sid", studentId);
                    cmd.Parameters.AddWithValue("@sub", subject);
                    cmd.Parameters.AddWithValue("@sc", score);
                    cmd.Parameters.AddWithValue("@id", gradeId);
                    con.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }

        public void DeleteGrade(int gradeId)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                string query = "DELETE FROM Grades WHERE GradeID=@id";
                using (SqlCommand cmd = new SqlCommand(query, con))
                {
                    cmd.Parameters.AddWithValue("@id", gradeId);
                    con.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }
    }
}
