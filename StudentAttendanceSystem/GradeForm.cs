using System;
using System.ComponentModel;
using System.Linq;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    public partial class GradeForm : Form
    {
        private BindingList<Grade> grades; // BindingList<> of grades UI updates automatically using this generic type
        private BindingList<Student> students; // Reference to registered students

        public GradeForm(BindingList<Student> registeredStudents)
        {
            InitializeComponent();
            students = registeredStudents;

            // Initialize grades list
            grades = new BindingList<Grade>();
            dataGridViewGrades.DataSource = grades;

            // Populate ComboBox with students
            comboBoxStudent.DataSource = students;
            comboBoxStudent.DisplayMember = "Name";
            comboBoxStudent.ValueMember = "ID";
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if (comboBoxStudent.SelectedItem == null || string.IsNullOrWhiteSpace(txtSubject.Text) || string.IsNullOrWhiteSpace(txtScore.Text))
            {
                MessageBox.Show("Please fill all fields.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (!double.TryParse(txtScore.Text, out double score))
            {
                MessageBox.Show("Score must be a number.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var selectedStudent = (Student)comboBoxStudent.SelectedItem;

            grades.Add(new Grade
            {
                StudentID = selectedStudent.ID,
                StudentName = selectedStudent.Name,
                Subject = txtSubject.Text.Trim(),
                Score = score
            });

            ClearFields();
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            if (dataGridViewGrades.CurrentRow == null)
            {
                MessageBox.Show("Select a grade to update.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var grade = (Grade)dataGridViewGrades.CurrentRow.DataBoundItem;

            if (!double.TryParse(txtScore.Text, out double score))
            {
                MessageBox.Show("Score must be a number.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            grade.Subject = txtSubject.Text.Trim();
            grade.Score = score;
            dataGridViewGrades.Refresh(); // Refresh DataGridView to show changes
            ClearFields();
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (dataGridViewGrades.CurrentRow == null)
            {
                MessageBox.Show("Select a grade to delete.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var grade = (Grade)dataGridViewGrades.CurrentRow.DataBoundItem;
            grades.Remove(grade);
            ClearFields();
        }

        private void dataGridViewGrades_SelectionChanged(object sender, EventArgs e)
        {
            if (dataGridViewGrades.CurrentRow != null)
            {
                var grade = (Grade)dataGridViewGrades.CurrentRow.DataBoundItem;
                comboBoxStudent.SelectedItem = students.FirstOrDefault(s => s.ID == grade.StudentID);
                txtSubject.Text = grade.Subject;
                txtScore.Text = grade.Score.ToString();
            }
        }

        private void ClearFields()
        {
            txtSubject.Clear();
            txtScore.Clear();
            comboBoxStudent.SelectedIndex = 0;
            dataGridViewGrades.ClearSelection();
        }
    }
}

