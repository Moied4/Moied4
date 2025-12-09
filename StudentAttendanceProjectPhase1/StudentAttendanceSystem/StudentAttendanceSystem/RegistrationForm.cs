using System;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class RegistrationForm : Form
    {
        public RegistrationForm()
        {
            InitializeComponent();
        }

        private void BtnReportCard_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtID.Text))
            {
                MessageBox.Show("Please select a student first.");
                return;
            }

            ReportCardForm reportForm = new ReportCardForm();
            reportForm.SetStudent(txtID.Text);
            reportForm.ShowDialog();
        }

        private void btnRegister_Click(object sender, EventArgs e)
        {
            try
            {
                if (string.IsNullOrWhiteSpace(txtID.Text) ||
                    string.IsNullOrWhiteSpace(txtName.Text) ||
                    string.IsNullOrWhiteSpace(txtClass.Text) ||
                    string.IsNullOrWhiteSpace(txtSection.Text))
                {
                    MessageBox.Show("Please fill in all fields.", "Validation Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                DatabaseManager db = new DatabaseManager();
                db.AddStudent(txtID.Text, txtName.Text, txtClass.Text, txtSection.Text, txtContact.Text);

                MessageBox.Show("Student registered successfully!", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);

                txtID.Clear();
                txtName.Clear();
                txtClass.Clear();
                txtSection.Clear();
                txtName.Focus();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message, "Database Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnAttendance_Click(object sender, EventArgs e)
        {
            AttendanceForm attendanceForm = new AttendanceForm();
            attendanceForm.Show();
        }

        private void btnGrades_Click(object sender, EventArgs e)
        {
            GradesForm gradesForm = new GradesForm();
            gradesForm.Show();
        }

        private void btnCheckAlerts_Click(object sender, EventArgs e)
        {
            try
            {
                AlertManager alertManager = new AlertManager();
                alertManager.CheckAlerts();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error checking alerts: " + ex.Message,
                    "Alert Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
