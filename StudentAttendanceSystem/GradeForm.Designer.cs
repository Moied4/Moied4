namespace StudentAttendanceSystem
{
    partial class GradeForm
    {
        private System.ComponentModel.IContainer components = null;

        private System.Windows.Forms.ComboBox comboBoxStudent;
        private System.Windows.Forms.TextBox txtSubject;
        private System.Windows.Forms.TextBox txtScore;
        private System.Windows.Forms.DataGridView dataGridViewGrades;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.Button btnUpdate;
        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.Label labelStudent;
        private System.Windows.Forms.Label labelSubject;
        private System.Windows.Forms.Label labelScore;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.comboBoxStudent = new System.Windows.Forms.ComboBox();
            this.txtSubject = new System.Windows.Forms.TextBox();
            this.txtScore = new System.Windows.Forms.TextBox();
            this.dataGridViewGrades = new System.Windows.Forms.DataGridView();
            this.btnAdd = new System.Windows.Forms.Button();
            this.btnUpdate = new System.Windows.Forms.Button();
            this.btnDelete = new System.Windows.Forms.Button();
            this.labelStudent = new System.Windows.Forms.Label();
            this.labelSubject = new System.Windows.Forms.Label();
            this.labelScore = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewGrades)).BeginInit();
            this.SuspendLayout();
            // 
            // comboBoxStudent
            // 
            this.comboBoxStudent.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.comboBoxStudent.Location = new System.Drawing.Point(90, 15);
            this.comboBoxStudent.Name = "comboBoxStudent";
            this.comboBoxStudent.Size = new System.Drawing.Size(180, 21);
            // 
            // txtSubject
            // 
            this.txtSubject.Location = new System.Drawing.Point(90, 50);
            this.txtSubject.Name = "txtSubject";
            this.txtSubject.Size = new System.Drawing.Size(180, 20);
            // 
            // txtScore
            // 
            this.txtScore.Location = new System.Drawing.Point(90, 85);
            this.txtScore.Name = "txtScore";
            this.txtScore.Size = new System.Drawing.Size(180, 20);
            // 
            // dataGridViewGrades
            // 
            this.dataGridViewGrades.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewGrades.Location = new System.Drawing.Point(300, 15);
            this.dataGridViewGrades.Name = "dataGridViewGrades";
            this.dataGridViewGrades.Size = new System.Drawing.Size(450, 250);
            this.dataGridViewGrades.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewGrades.MultiSelect = false;
            this.dataGridViewGrades.ReadOnly = true;
            this.dataGridViewGrades.SelectionChanged += new System.EventHandler(this.dataGridViewGrades_SelectionChanged);
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(30, 130);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(75, 25);
            this.btnAdd.Text = "Add";
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // btnUpdate
            // 
            this.btnUpdate.Location = new System.Drawing.Point(120, 130);
            this.btnUpdate.Name = "btnUpdate";
            this.btnUpdate.Size = new System.Drawing.Size(75, 25);
            this.btnUpdate.Text = "Update";
            this.btnUpdate.Click += new System.EventHandler(this.btnUpdate_Click);
            // 
            // btnDelete
            // 
            this.btnDelete.Location = new System.Drawing.Point(210, 130);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(75, 25);
            this.btnDelete.Text = "Delete";
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // Labels
            // 
            this.labelStudent.AutoSize = true;
            this.labelStudent.Location = new System.Drawing.Point(30, 18);
            this.labelStudent.Name = "labelStudent";
            this.labelStudent.Size = new System.Drawing.Size(47, 13);
            this.labelStudent.Text = "Student:";
            // 
            this.labelSubject.AutoSize = true;
            this.labelSubject.Location = new System.Drawing.Point(30, 53);
            this.labelSubject.Name = "labelSubject";
            this.labelSubject.Size = new System.Drawing.Size(46, 13);
            this.labelSubject.Text = "Subject:";
            // 
            this.labelScore.AutoSize = true;
            this.labelScore.Location = new System.Drawing.Point(30, 88);
            this.labelScore.Name = "labelScore";
            this.labelScore.Size = new System.Drawing.Size(38, 13);
            this.labelScore.Text = "Score:";
            // 
            // GradeForm
            // 
            this.ClientSize = new System.Drawing.Size(780, 280);
            this.Controls.Add(this.labelStudent);
            this.Controls.Add(this.labelSubject);
            this.Controls.Add(this.labelScore);
            this.Controls.Add(this.comboBoxStudent);
            this.Controls.Add(this.txtSubject);
            this.Controls.Add(this.txtScore);
            this.Controls.Add(this.dataGridViewGrades);
            this.Controls.Add(this.btnAdd);
            this.Controls.Add(this.btnUpdate);
            this.Controls.Add(this.btnDelete);
            this.Text = "Grades Management";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewGrades)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();
        }
    }
}
