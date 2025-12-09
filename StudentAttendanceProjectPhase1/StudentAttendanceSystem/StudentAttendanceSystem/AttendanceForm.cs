using System;
using System.Collections.Generic;
using System.Data;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class AttendanceForm : Form
    {
        private DatabaseManager db = new DatabaseManager();

        private DataGridView dgvAttendance;
        private Button btnAdd;
        private Button btnEdit;
        private Button btnDelete;

        public AttendanceForm()
        {
            InitializeComponent();
            LoadAttendance();
        }

        private void LoadAttendance()
        {
            try
            {
                dgvAttendance.DataSource = db.GetAllAttendance(); // Make sure this returns a DataTable
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error loading attendance: " + ex.Message);
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            AttendanceEntryForm entryForm = new AttendanceEntryForm(); // Form to select student and status
            if (entryForm.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    db.AddAttendance(entryForm.StudentID, entryForm.Date, entryForm.Status);
                    LoadAttendance();
                    MessageBox.Show("Attendance added successfully.");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error adding attendance: " + ex.Message);
                }
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (dgvAttendance.SelectedRows.Count == 0)
            {
                MessageBox.Show("Please select an attendance record to edit.");
                return;
            }

            dynamic row = dgvAttendance.SelectedRows[0].DataBoundItem;

            AttendanceEntryForm entryForm = new AttendanceEntryForm(
                row.StudentID.ToString(),
                Convert.ToDateTime(row.Date),
                row.Status.ToString()
            );

            if (entryForm.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    db.UpdateAttendance(
                        row.AttendanceID,  // Make sure DB has a unique AttendanceID column
                        entryForm.StudentID,
                        entryForm.Date,
                        entryForm.Status
                    );
                    LoadAttendance();
                    MessageBox.Show("Attendance updated successfully.");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error updating attendance: " + ex.Message);
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (dgvAttendance.SelectedRows.Count == 0)
            {
                MessageBox.Show("Please select an attendance record to delete.");
                return;
            }

            dynamic row = dgvAttendance.SelectedRows[0].DataBoundItem;

            if (MessageBox.Show("Are you sure you want to delete this attendance record?", "Confirm Delete", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                try
                {
                    db.DeleteAttendance(row.AttendanceID);
                    LoadAttendance();
                    MessageBox.Show("Attendance deleted successfully.");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error deleting attendance: " + ex.Message);
                }
            }
        }

        private void InitializeComponent()
        {
            this.dgvAttendance = new DataGridView();
            this.btnAdd = new Button();
            this.btnEdit = new Button();
            this.btnDelete = new Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgvAttendance)).BeginInit();
            this.SuspendLayout();

            // dgvAttendance
            this.dgvAttendance.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvAttendance.Location = new System.Drawing.Point(20, 20);
            this.dgvAttendance.Name = "dgvAttendance";
            this.dgvAttendance.ReadOnly = true;
            this.dgvAttendance.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            this.dgvAttendance.Size = new System.Drawing.Size(440, 200);

            // btnAdd
            this.btnAdd.Location = new System.Drawing.Point(20, 240);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(100, 30);
            this.btnAdd.Text = "Add";
            this.btnAdd.Click += new EventHandler(this.btnAdd_Click);

            // btnEdit
            this.btnEdit.Location = new System.Drawing.Point(150, 240);
            this.btnEdit.Name = "btnEdit";
            this.btnEdit.Size = new System.Drawing.Size(100, 30);
            this.btnEdit.Text = "Edit";
            this.btnEdit.Click += new EventHandler(this.btnEdit_Click);

            // btnDelete
            this.btnDelete.Location = new System.Drawing.Point(280, 240);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(100, 30);
            this.btnDelete.Text = "Delete";
            this.btnDelete.Click += new EventHandler(this.btnDelete_Click);

            // AttendanceForm
            this.ClientSize = new System.Drawing.Size(480, 300);
            this.Controls.Add(this.dgvAttendance);
            this.Controls.Add(this.btnAdd);
            this.Controls.Add(this.btnEdit);
            this.Controls.Add(this.btnDelete);
            this.Name = "AttendanceForm";
            this.Text = "Attendance Management";
            ((System.ComponentModel.ISupportInitialize)(this.dgvAttendance)).EndInit();
            this.ResumeLayout(false);
        }
    }
}
