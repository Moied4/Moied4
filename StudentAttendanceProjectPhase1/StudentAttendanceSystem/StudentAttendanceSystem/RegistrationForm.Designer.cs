using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    partial class RegistrationForm
    {
        private System.ComponentModel.IContainer components = null;

        private Label labelName;
        private Label labelID;
        private Label labelClass;
        private Label labelSection;
        private Label labelContact;

        private TextBox txtName;
        private TextBox txtID;
        private TextBox txtClass;
        private TextBox txtSection;
        private TextBox txtContact;

        private Button btnRegister;
        private Button btnAttendance;
        private Button btnGrades;
        private Button btnCheckAlerts;
        private Button btnReportCard; // Added button

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();

            // Labels
            this.labelName = new Label();
            this.labelID = new Label();
            this.labelClass = new Label();
            this.labelSection = new Label();
            this.labelContact = new Label();

            // TextBoxes
            this.txtName = new TextBox();
            this.txtID = new TextBox();
            this.txtClass = new TextBox();
            this.txtSection = new TextBox();
            this.txtContact = new TextBox();

            // Buttons
            this.btnRegister = new Button();
            this.btnAttendance = new Button();
            this.btnGrades = new Button();
            this.btnCheckAlerts = new Button();
            this.btnReportCard = new Button(); // Initialize the new button

            // Label properties
            this.labelName.AutoSize = true;
            this.labelName.Location = new System.Drawing.Point(30, 20);
            this.labelName.Text = "Name:";

            this.labelID.AutoSize = true;
            this.labelID.Location = new System.Drawing.Point(30, 55);
            this.labelID.Text = "ID:";

            this.labelClass.AutoSize = true;
            this.labelClass.Location = new System.Drawing.Point(30, 90);
            this.labelClass.Text = "Class:";

            this.labelSection.AutoSize = true;
            this.labelSection.Location = new System.Drawing.Point(30, 125);
            this.labelSection.Text = "Section:";

            this.labelContact.AutoSize = true;
            this.labelContact.Location = new System.Drawing.Point(30, 160);
            this.labelContact.Text = "Contact:";

            // TextBox properties
            this.txtName.Location = new System.Drawing.Point(90, 18);
            this.txtName.Size = new System.Drawing.Size(150, 20);

            this.txtID.Location = new System.Drawing.Point(90, 53);
            this.txtID.Size = new System.Drawing.Size(150, 20);

            this.txtClass.Location = new System.Drawing.Point(90, 88);
            this.txtClass.Size = new System.Drawing.Size(150, 20);

            this.txtSection.Location = new System.Drawing.Point(90, 123);
            this.txtSection.Size = new System.Drawing.Size(150, 20);

            this.txtContact.Location = new System.Drawing.Point(90, 158);
            this.txtContact.Size = new System.Drawing.Size(150, 20);

            // Button properties
            this.btnRegister.Location = new System.Drawing.Point(90, 190);
            this.btnRegister.Size = new System.Drawing.Size(75, 25);
            this.btnRegister.Text = "Register";
            this.btnRegister.Click += new System.EventHandler(this.btnRegister_Click);

            this.btnAttendance.Location = new System.Drawing.Point(270, 18);
            this.btnAttendance.Size = new System.Drawing.Size(130, 25);
            this.btnAttendance.Text = "Manage Attendance";
            this.btnAttendance.Click += new System.EventHandler(this.btnAttendance_Click);

            this.btnGrades.Location = new System.Drawing.Point(270, 55);
            this.btnGrades.Size = new System.Drawing.Size(130, 25);
            this.btnGrades.Text = "Manage Grades";
            this.btnGrades.Click += new System.EventHandler(this.btnGrades_Click);

            this.btnCheckAlerts.Location = new System.Drawing.Point(270, 90);
            this.btnCheckAlerts.Size = new System.Drawing.Size(130, 25);
            this.btnCheckAlerts.Text = "Check Alerts";
            this.btnCheckAlerts.Click += new System.EventHandler(this.btnCheckAlerts_Click);

            this.btnReportCard.Location = new System.Drawing.Point(270, 125); // Aligned below other buttons
            this.btnReportCard.Size = new System.Drawing.Size(130, 25);
            this.btnReportCard.Text = "Generate Report Card";
            this.btnReportCard.Click += new System.EventHandler(this.BtnReportCard_Click); // Handler in RegistrationForm.cs

            // Add controls to form
            this.Controls.Add(this.labelName);
            this.Controls.Add(this.labelID);
            this.Controls.Add(this.labelClass);
            this.Controls.Add(this.labelSection);
            this.Controls.Add(this.labelContact);
            this.Controls.Add(this.txtName);
            this.Controls.Add(this.txtID);
            this.Controls.Add(this.txtClass);
            this.Controls.Add(this.txtSection);
            this.Controls.Add(this.txtContact);
            this.Controls.Add(this.btnRegister);
            this.Controls.Add(this.btnAttendance);
            this.Controls.Add(this.btnGrades);
            this.Controls.Add(this.btnCheckAlerts);
            this.Controls.Add(this.btnReportCard); // Add the new button

            // Form properties
            this.ClientSize = new System.Drawing.Size(450, 240);
            this.Text = "Student Registration";
            this.FormBorderStyle = FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
        }
    }
}
