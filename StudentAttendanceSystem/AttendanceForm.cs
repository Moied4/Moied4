using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class AttendanceForm : Form
    {
        private BindingList<Student> students; // Changed from List<Student> to BindingList<Student>
        private List<AttendanceRecord> attendanceRecords = new List<AttendanceRecord>();

        private ListBox lstStudents;
        private ComboBox cmbStatus;
        private Button btnSaveAttendance;

        public AttendanceForm(List<Student> registeredStudents)
        {
            // Convert List<Student> to BindingList<Student> for data binding compatibility
            students = new BindingList<Student>(registeredStudents);
            InitializeComponent();
        }

        private void AttendanceForm_Load(object sender, EventArgs e)
        {
            // Populate students in the ListBox
            foreach (var student in students)
            {
                lstStudents.Items.Add($"{student.Name} ({student.ID})");
            }

            // Add enum values to ComboBox
            cmbStatus.Items.AddRange(Enum.GetNames(typeof(AttendanceStatus)));
            cmbStatus.SelectedIndex = 0; // Default to Present
        }

        private void btnSaveAttendance_Click(object sender, EventArgs e)
        {
            if (lstStudents.SelectedIndex == -1)
            {
                MessageBox.Show("Please select a student.", "Input Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            string selectedStudentInfo = lstStudents.SelectedItem.ToString();

            // Extract student ID from "Name (ID)"
            int startIndex = selectedStudentInfo.IndexOf('(');
            int endIndex = selectedStudentInfo.IndexOf(')');
            string studentID = selectedStudentInfo.Substring(startIndex + 1, endIndex - startIndex - 1);

            // Parse enum safely
            if (!Enum.TryParse(cmbStatus.SelectedItem.ToString(), out AttendanceStatus status))
            {
                MessageBox.Show("Invalid attendance status.",
                    "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            // Add attendance record
            AttendanceRecord record = new AttendanceRecord
            {
                StudentID = studentID,
                Date = DateTime.Now,
                Status = (global::AttendanceStatus)status
            };

            attendanceRecords.Add(record);

            MessageBox.Show($"Attendance marked as {status} for student {studentID}.",
                "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);

            // Open the GradeForm and pass the BindingList<Student>
            GradeForm gradeForm = new GradeForm(students);
            gradeForm.Show();

            // Close this form so only GradeForm is active
            this.Close();
        }

        private void InitializeComponent()
        {
            this.lstStudents = new ListBox();
            this.cmbStatus = new ComboBox();
            this.btnSaveAttendance = new Button();

            this.SuspendLayout();

            // lstStudents
            this.lstStudents.FormattingEnabled = true;
            this.lstStudents.Location = new System.Drawing.Point(30, 60);
            this.lstStudents.Name = "lstStudents";
            this.lstStudents.Size = new System.Drawing.Size(220, 100);
            this.lstStudents.TabIndex = 0;

            // cmbStatus
            this.cmbStatus.FormattingEnabled = true;
            this.cmbStatus.Location = new System.Drawing.Point(30, 20);
            this.cmbStatus.Name = "cmbStatus";
            this.cmbStatus.Size = new System.Drawing.Size(150, 21);
            this.cmbStatus.TabIndex = 1;

            // btnSaveAttendance
            this.btnSaveAttendance.Location = new System.Drawing.Point(70, 180);
            this.btnSaveAttendance.Name = "btnSaveAttendance";
            this.btnSaveAttendance.Size = new System.Drawing.Size(140, 35);
            this.btnSaveAttendance.TabIndex = 2;
            this.btnSaveAttendance.Text = "Save Attendance";
            this.btnSaveAttendance.UseVisualStyleBackColor = true;
            this.btnSaveAttendance.Click += new EventHandler(this.btnSaveAttendance_Click);

            // AttendanceForm
            this.ClientSize = new System.Drawing.Size(300, 240);
            this.Controls.Add(this.btnSaveAttendance);
            this.Controls.Add(this.cmbStatus);
            this.Controls.Add(this.lstStudents);
            this.Name = "AttendanceForm";
            this.Text = "Attendance Form";
            this.Load += new EventHandler(this.AttendanceForm_Load);
            this.ResumeLayout(false);
        }
    }

    // Keep enum here if not defined elsewhere
    public enum AttendanceStatus
    {
        Present,
        Absent,
        Late,
        Excused
    }
}
