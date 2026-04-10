using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class RegistrationForm : Form
    {
        private StudentManager studentManager; // Instance of StudentManager to handle students

        public RegistrationForm()
        {
            InitializeComponent();
            studentManager = new StudentManager(); // Initialize the StudentManager
        }

        private void btnRegister_Click(object sender, EventArgs e)
        {
            // Validate input fields
            if (string.IsNullOrWhiteSpace(txtName.Text) ||
                string.IsNullOrWhiteSpace(txtID.Text) ||
                string.IsNullOrWhiteSpace(txtClass.Text) ||
                string.IsNullOrWhiteSpace(txtSection.Text))
            {
                MessageBox.Show("Please fill in all the fields.", "Input Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // Create a new student object
            Student newStudent = new Student
            {
                Name = txtName.Text.Trim(),
                ID = txtID.Text.Trim(),
                Class = txtClass.Text.Trim(),
                Section = txtSection.Text.Trim()
            };

            // Add to the student manager
            studentManager.AddStudent(newStudent);

            // Show success message
            MessageBox.Show("Student registered successfully!", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);

            // Open the AttendanceForm and pass the student manager's list
            AttendanceForm attendanceForm = new AttendanceForm(studentManager.GetAllStudents());
            attendanceForm.Show();

            // Clear form fields
            txtName.Clear();
            txtID.Clear();
            txtClass.Clear();
            txtSection.Clear();
        }

        private void RegistrationForm_Load(object sender, EventArgs e)
        {
            // Any form load logic goes here
        }
    }
}
