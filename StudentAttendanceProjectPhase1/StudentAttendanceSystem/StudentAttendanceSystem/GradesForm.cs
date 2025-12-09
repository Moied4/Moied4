using System;
using System.Data;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class GradesForm : Form
    {
        private DatabaseManager db;

        public GradesForm()
        {
            db = new DatabaseManager();
            InitializeComponent();
            LoadGrades();
        }

        private void LoadGrades()
        {
            try
            {
                dgvGrades.DataSource = db.GetAllGrades();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error loading grades: " + ex.Message);
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            GradeEntryForm entryForm = new GradeEntryForm();
            if (entryForm.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    db.AddGrade(entryForm.StudentID, entryForm.Subject, entryForm.Score);
                    LoadGrades();
                    MessageBox.Show("Grade added successfully.");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error adding grade: " + ex.Message);
                }
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (dgvGrades.SelectedRows.Count == 0)
            {
                MessageBox.Show("Please select a grade to edit.");
                return;
            }

            DataRowView row = (DataRowView)dgvGrades.SelectedRows[0].DataBoundItem;

            GradeEntryForm entryForm = new GradeEntryForm(
                row["StudentID"].ToString(),
                row["Subject"].ToString(),
                Convert.ToDecimal(row["Score"])
            );

            if (entryForm.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    db.UpdateGrade(
                        Convert.ToInt32(row["GradeID"]),
                        entryForm.StudentID,
                        entryForm.Subject,
                        entryForm.Score
                    );
                    LoadGrades();
                    MessageBox.Show("Grade updated successfully.");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error updating grade: " + ex.Message);
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (dgvGrades.SelectedRows.Count == 0)
            {
                MessageBox.Show("Please select a grade to delete.");
                return;
            }

            DataRowView row = (DataRowView)dgvGrades.SelectedRows[0].DataBoundItem;

            if (MessageBox.Show("Are you sure you want to delete this grade?", "Confirm Delete", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                try
                {
                    db.DeleteGrade(Convert.ToInt32(row["GradeID"]));
                    LoadGrades();
                    MessageBox.Show("Grade deleted successfully.");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error deleting grade: " + ex.Message);
                }
            }
        }
    }
}
