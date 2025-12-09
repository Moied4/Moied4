using System;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class GradeEntryForm : Form
    {
        public string StudentID { get; set; }
        public string Subject { get; set; }
        public decimal Score { get; set; }

        public GradeEntryForm()
        {
            InitializeComponent();
        }

        public GradeEntryForm(string studentId, string subject, decimal score)
        {
            InitializeComponent();
            StudentID = studentId;
            Subject = subject;
            Score = score;

            txtStudentID.Text = studentId;
            txtSubject.Text = subject;
            txtScore.Text = score.ToString();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            StudentID = txtStudentID.Text;
            Subject = txtSubject.Text;
            Score = decimal.Parse(txtScore.Text);

            this.DialogResult = DialogResult.OK;
            this.Close();
        }
    }
}
