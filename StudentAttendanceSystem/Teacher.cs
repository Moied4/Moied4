namespace StudentAttendanceSystem
{
    public class Teacher
    {
        public string Username { get; set; }    // Username for login
        public string Password { get; set; }    // Password (plain text for now)
        public string FullName { get; set; }    // Full name of the teacher

        // Constructor
        public Teacher() { }

        public Teacher(string username, string password, string fullName)
        {
            Username = username;
            Password = password;
            FullName = fullName;
        }
    }
}
