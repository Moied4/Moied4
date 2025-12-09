using System;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    partial class ReportCardForm
    {
        private System.ComponentModel.IContainer components = null;

        private ComboBox cmbStudents;
        private Label lblAverage;
        private Label lblAttendance;
        private DataGridView dgvGrades;
        private DataGridView dgvAttendance;
        private Button btnGenerateReport;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();

            // Initialize controls
            this.cmbStudents = new ComboBox();
            this.lblAverage = new Label();
            this.lblAttendance = new Label();
            this.dgvGrades = new DataGridView();
            this.dgvAttendance = new DataGridView();
            this.btnGenerateReport = new Button();

            // ComboBox
            this.cmbStudents.Location = new System.Drawing.Point(30, 20);
            this.cmbStudents.Size = new System.Drawing.Size(200, 21);
            this.cmbStudents.DropDownStyle = ComboBoxStyle.DropDownList;
            this.cmbStudents.SelectedIndexChanged += new System.EventHandler(this.cmbStudents_SelectedIndexChanged);

            // Labels
            this.lblAverage.Location = new System.Drawing.Point(30, 300);
            this.lblAverage.Size = new System.Drawing.Size(200, 20);
            this.lblAverage.Text = "Average: ";

            this.lblAttendance.Location = new System.Drawing.Point(30, 330);
            this.lblAttendance.Size = new System.Drawing.Size(200, 20);
            this.lblAttendance.Text = "Attendance: ";

            // DataGridViews
            this.dgvGrades.Location = new System.Drawing.Point(30, 60);
            this.dgvGrades.Size = new System.Drawing.Size(400, 100);
            this.dgvGrades.AllowUserToAddRows = false;
            this.dgvGrades.AllowUserToDeleteRows = false;
            this.dgvGrades.ReadOnly = true;

            this.dgvAttendance.Location = new System.Drawing.Point(30, 180);
            this.dgvAttendance.Size = new System.Drawing.Size(400, 100);
            this.dgvAttendance.AllowUserToAddRows = false;
            this.dgvAttendance.AllowUserToDeleteRows = false;
            this.dgvAttendance.ReadOnly = true;

            // Button
            this.btnGenerateReport.Location = new System.Drawing.Point(30, 370);
            this.btnGenerateReport.Size = new System.Drawing.Size(120, 25);
            this.btnGenerateReport.Text = "Generate Report";
            this.btnGenerateReport.Click += new EventHandler(this.btnGenerate_Click);


            // Add controls to form
            this.Controls.Add(this.cmbStudents);
            this.Controls.Add(this.lblAverage);
            this.Controls.Add(this.lblAttendance);
            this.Controls.Add(this.dgvGrades);
            this.Controls.Add(this.dgvAttendance);
            this.Controls.Add(this.btnGenerateReport);

            // Form properties
            this.ClientSize = new System.Drawing.Size(480, 420);
            this.Text = "Report Card";
            this.FormBorderStyle = FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
        }

        #endregion
    }
}
