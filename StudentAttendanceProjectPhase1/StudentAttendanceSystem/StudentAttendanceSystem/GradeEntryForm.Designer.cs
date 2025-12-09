namespace StudentAttendanceSystem
{
    partial class GradeEntryForm
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Label lblStudentID;
        private System.Windows.Forms.Label lblSubject;
        private System.Windows.Forms.Label lblScore;
        private System.Windows.Forms.TextBox txtStudentID;
        private System.Windows.Forms.TextBox txtSubject;
        private System.Windows.Forms.TextBox txtScore;
        private System.Windows.Forms.Button btnSave;

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
            this.lblStudentID = new System.Windows.Forms.Label();
            this.lblSubject = new System.Windows.Forms.Label();
            this.lblScore = new System.Windows.Forms.Label();
            this.txtStudentID = new System.Windows.Forms.TextBox();
            this.txtSubject = new System.Windows.Forms.TextBox();
            this.txtScore = new System.Windows.Forms.TextBox();
            this.btnSave = new System.Windows.Forms.Button();
            this.SuspendLayout();

            // lblStudentID
            this.lblStudentID.AutoSize = true;
            this.lblStudentID.Location = new System.Drawing.Point(30, 30);
            this.lblStudentID.Name = "lblStudentID";
            this.lblStudentID.Size = new System.Drawing.Size(65, 15);
            this.lblStudentID.Text = "Student ID:";

            // txtStudentID
            this.txtStudentID.Location = new System.Drawing.Point(120, 27);
            this.txtStudentID.Name = "txtStudentID";
            this.txtStudentID.Size = new System.Drawing.Size(200, 23);

            // lblSubject
            this.lblSubject.AutoSize = true;
            this.lblSubject.Location = new System.Drawing.Point(30, 70);
            this.lblSubject.Name = "lblSubject";
            this.lblSubject.Size = new System.Drawing.Size(50, 15);
            this.lblSubject.Text = "Subject:";

            // txtSubject
            this.txtSubject.Location = new System.Drawing.Point(120, 67);
            this.txtSubject.Name = "txtSubject";
            this.txtSubject.Size = new System.Drawing.Size(200, 23);

            // lblScore
            this.lblScore.AutoSize = true;
            this.lblScore.Location = new System.Drawing.Point(30, 110);
            this.lblScore.Name = "lblScore";
            this.lblScore.Size = new System.Drawing.Size(39, 15);
            this.lblScore.Text = "Score:";

            // txtScore
            this.txtScore.Location = new System.Drawing.Point(120, 107);
            this.txtScore.Name = "txtScore";
            this.txtScore.Size = new System.Drawing.Size(200, 23);

            // btnSave
            this.btnSave.Location = new System.Drawing.Point(120, 150);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(100, 30);
            this.btnSave.Text = "Save";
            this.btnSave.UseVisualStyleBackColor = true;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);

            // GradeEntryForm
            this.ClientSize = new System.Drawing.Size(360, 220);
            this.Controls.Add(this.lblStudentID);
            this.Controls.Add(this.txtStudentID);
            this.Controls.Add(this.lblSubject);
            this.Controls.Add(this.txtSubject);
            this.Controls.Add(this.lblScore);
            this.Controls.Add(this.txtScore);
            this.Controls.Add(this.btnSave);
            this.Name = "GradeEntryForm";
            this.Text = "Grade Entry";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion
    }
}
