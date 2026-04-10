namespace StudentAttendanceSystem
{
    partial class RegistrationForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        private System.Windows.Forms.Label labelName;
        private System.Windows.Forms.Label labelID;
        private System.Windows.Forms.Label labelClass;
        private System.Windows.Forms.Label labelSection;
        private System.Windows.Forms.TextBox txtName;
        private System.Windows.Forms.TextBox txtID;
        private System.Windows.Forms.TextBox txtClass;
        private System.Windows.Forms.TextBox txtSection;
        private System.Windows.Forms.Button btnRegister;  // Renamed button
        private System.Windows.Forms.Label label1; // can update this label

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support — do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.labelName = new System.Windows.Forms.Label();
            this.labelID = new System.Windows.Forms.Label();
            this.labelClass = new System.Windows.Forms.Label();
            this.labelSection = new System.Windows.Forms.Label();
            this.txtName = new System.Windows.Forms.TextBox();
            this.txtID = new System.Windows.Forms.TextBox();
            this.txtClass = new System.Windows.Forms.TextBox();
            this.txtSection = new System.Windows.Forms.TextBox();
            this.btnRegister = new System.Windows.Forms.Button();  // Renamed button
            this.SuspendLayout();
            // 
            // labelName
            // 
            this.labelName.AutoSize = true;
            this.labelName.Location = new System.Drawing.Point(38, 20);
            this.labelName.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelName.Name = "labelName";
            this.labelName.Size = new System.Drawing.Size(38, 13);
            this.labelName.TabIndex = 0;
            this.labelName.Text = "Name:";
            // 
            // labelID
            // 
            this.labelID.AutoSize = true;
            this.labelID.Location = new System.Drawing.Point(38, 53);
            this.labelID.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelID.Name = "labelID";
            this.labelID.Size = new System.Drawing.Size(21, 13);
            this.labelID.TabIndex = 1;
            this.labelID.Text = "ID:";
            // 
            // labelClass
            // 
            this.labelClass.AutoSize = true;
            this.labelClass.Location = new System.Drawing.Point(38, 85);
            this.labelClass.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelClass.Name = "labelClass";
            this.labelClass.Size = new System.Drawing.Size(35, 13);
            this.labelClass.TabIndex = 2;
            this.labelClass.Text = "Class:";
            // 
            // labelSection
            // 
            this.labelSection.AutoSize = true;
            this.labelSection.Location = new System.Drawing.Point(38, 118);
            this.labelSection.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelSection.Name = "labelSection";
            this.labelSection.Size = new System.Drawing.Size(46, 13);
            this.labelSection.TabIndex = 3;
            this.labelSection.Text = "Section:";
            // 
            // txtName
            // 
            this.txtName.Location = new System.Drawing.Point(90, 18);
            this.txtName.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.txtName.Name = "txtName";
            this.txtName.Size = new System.Drawing.Size(136, 20);
            this.txtName.TabIndex = 4;
            // 
            // txtID
            // 
            this.txtID.Location = new System.Drawing.Point(90, 50);
            this.txtID.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.txtID.Name = "txtID";
            this.txtID.Size = new System.Drawing.Size(136, 20);
            this.txtID.TabIndex = 5;
            // 
            // txtClass
            // 
            this.txtClass.Location = new System.Drawing.Point(90, 83);
            this.txtClass.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.txtClass.Name = "txtClass";
            this.txtClass.Size = new System.Drawing.Size(136, 20);
            this.txtClass.TabIndex = 6;
            // 
            // txtSection
            // 
            this.txtSection.Location = new System.Drawing.Point(90, 115);
            this.txtSection.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.txtSection.Name = "txtSection";
            this.txtSection.Size = new System.Drawing.Size(136, 20);
            this.txtSection.TabIndex = 7;
            // 
            // btnRegister
            // 
            this.btnRegister.Location = new System.Drawing.Point(90, 154);
            this.btnRegister.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.btnRegister.Name = "btnRegister";  // Renamed button
            this.btnRegister.Size = new System.Drawing.Size(75, 24);
            this.btnRegister.TabIndex = 8;
            this.btnRegister.Text = "Register";
            this.btnRegister.UseVisualStyleBackColor = true;
            this.btnRegister.Click += new System.EventHandler(this.btnRegister_Click);
            // 
            // RegistrationForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1254, 350);
            this.Controls.Add(this.btnRegister);  // Renamed button
            this.Controls.Add(this.txtSection);
            this.Controls.Add(this.txtClass);
            this.Controls.Add(this.txtID);
            this.Controls.Add(this.txtName);
            this.Controls.Add(this.labelSection);
            this.Controls.Add(this.labelClass);
            this.Controls.Add(this.labelID);
            this.Controls.Add(this.labelName);
            this.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.Name = "RegistrationForm";
            this.Text = "Student Registration";
            this.Load += new System.EventHandler(this.RegistrationForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion
    }
}

