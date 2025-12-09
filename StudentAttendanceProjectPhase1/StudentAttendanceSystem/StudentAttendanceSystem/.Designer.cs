using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    partial class AttendanceEntryForm
    {
        private System.ComponentModel.IContainer components = null;
        private ComboBox cmbStudent;
        private DateTimePicker dtpDate;
        private ComboBox cmbStatus;
        private Button btnSave;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.cmbStudent = new ComboBox();
            this.dtpDate = new DateTimePicker();
            this.cmbStatus = new ComboBox();
            this.btnSave = new Button();
            this.SuspendLayout();

            // cmbStudent
            this.cmbStudent.DropDownStyle = ComboBoxStyle.DropDownList;
            this.cmbStudent.Location = new System.Drawing.Point(20, 20);
            this.cmbStudent.Size = new System.Drawing.Size(200, 24);

            // dtpDate
            this.dtpDate.Location = new System.Drawing.Point(20, 60);
            this.dtpDate.Size = new System.Drawing.Size(200, 22);

            // cmbStatus
            this.cmbStatus.DropDownStyle = ComboBoxStyle.DropDownList;
            this.cmbStatus.Items.AddRange(new object[] { "Present", "Absent" });
            this.cmbStatus.Location = new System.Drawing.Point(20, 100);
            this.cmbStatus.Size = new System.Drawing.Size(200, 24);

            // btnSave
            this.btnSave.Location = new System.Drawing.Point(20, 140);
            this.btnSave.Size = new System.Drawing.Size(100, 30);
            this.btnSave.Text = "Save";
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);

            // AttendanceEntryForm
            this.ClientSize = new System.Drawing.Size(250, 200);
            this.Controls.Add(this.cmbStudent);
            this.Controls.Add(this.dtpDate);
            this.Controls.Add(this.cmbStatus);
            this.Controls.Add(this.btnSave);
            this.Name = "AttendanceEntryForm";
            this.Text = "Attendance Entry";

            this.ResumeLayout(false);
        }
    }
}
