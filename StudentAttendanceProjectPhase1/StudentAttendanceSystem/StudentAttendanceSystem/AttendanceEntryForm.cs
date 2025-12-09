using System;
using System.Data;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class AttendanceEntryForm : Form
    {
        private DatabaseManager db;

        public string StudentID { get; set; }
        public DateTime Date { get; set; }
        public string Status { get; set; }

        public AttendanceEntryForm()
        {
            InitializeComponent();
            db = new DatabaseManager();
            LoadStudents();
            dtpDate.Value = DateTime.Today;
        }

        public AttendanceEntryForm(string studentId, DateTime date, string status)
            : this()
        {
            StudentID = studentId;
            Date = date;
            Status = status;

            cmbStudent.SelectedValue = studentId;
            dtpDate.Value = date;
            cmbStatus.SelectedItem = status;
        }

        private void LoadStudents()
        {
            try
            {
                DataTable students = db.GetAllStudents();
                cmbStudent.DataSource = students;
                cmbStudent.DisplayMember = "Name"; // show student name
                cmbStudent.ValueMember = "ID"; // actual value is student ID
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error loading students: " + ex.Message);
            }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (cmbStudent.SelectedValue == null || cmbStatus.SelectedItem == null)
            {
                MessageBox.Show("Please select a student and status.");
                return;
            }

            StudentID = cmbStudent.SelectedValue.ToString();
            Date = dtpDate.Value.Date;
            Status = cmbStatus.SelectedItem.ToString();

            this.DialogResult = DialogResult.OK;
            this.Close();
        }
    }
}
