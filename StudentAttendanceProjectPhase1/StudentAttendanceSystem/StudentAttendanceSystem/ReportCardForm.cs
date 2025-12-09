using System;
using System.Data;
using System.Linq;
using System.Windows.Forms;
using System.IO;
using iTextSharp.text;
using iTextSharp.text.pdf;

namespace StudentAttendanceSystem
{
    public partial class ReportCardForm : Form
    {
        private readonly DatabaseManager db = new DatabaseManager();

        public ReportCardForm()
        {
            InitializeComponent();
            LoadStudents();
        }

        private void LoadStudents()
        {
            try
            {
                DataTable dt = db.GetAllStudents();
                cmbStudents.DataSource = dt;
                cmbStudents.DisplayMember = "Name";
                cmbStudents.ValueMember = "StudentID";
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error loading students: " + ex.Message);
            }
        }

        private void btnGenerate_Click(object sender, EventArgs e)
        {
            if (cmbStudents.SelectedValue == null)
            {
                MessageBox.Show("Please select a student.");
                return;
            }

            string studentId = cmbStudents.SelectedValue.ToString();
            GenerateReport(studentId);
        }

        private void GenerateReport(string studentId)
        {
            try
            {
                DataTable grades = db.GetAllGrades();
                DataTable attendance = db.GetAllAttendance();

                var studentGrades = grades.AsEnumerable()
                    .Where(r => r["StudentID"].ToString() == studentId)
                    .ToList();

                var studentAttendance = attendance.AsEnumerable()
                    .Where(r => r["StudentID"].ToString() == studentId)
                    .ToList();

                // Calculate average score
                decimal avgScore = studentGrades.Count > 0
                    ? studentGrades.Average(r => Convert.ToDecimal(r["Score"]))
                    : 0;

                // Calculate attendance stats
                int totalDays = studentAttendance.Count;
                int presentDays = studentAttendance.Count(r => r["Status"].ToString() == "Present");
                double attendancePercent = totalDays > 0 ? (presentDays / (double)totalDays) * 100 : 0;

                // Display on form
                lblAverage.Text = $"Average Score: {avgScore:F2}";
                lblAttendance.Text = $"Attendance: {attendancePercent:F1}% ({presentDays}/{totalDays})";

                // Safe binding for DataGridViews
                dgvGrades.DataSource = studentGrades.Any() ? studentGrades.CopyToDataTable() : null;
                dgvAttendance.DataSource = studentAttendance.Any() ? studentAttendance.CopyToDataTable() : null;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error generating report: " + ex.Message);
            }
        }

        public void SetStudent(string studentId)
        {
            // Make sure the ComboBox is loaded first
            if (cmbStudents.DataSource != null)
            {
                cmbStudents.SelectedValue = studentId;
                GenerateReport(studentId); // optional: immediately generate the report
            }
        }

        private void cmbStudents_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cmbStudents.SelectedValue != null)
            {
                GenerateReport(cmbStudents.SelectedValue.ToString());
            }
        }

        private void btnExportPDF_Click(object sender, EventArgs e)
        {
            if (cmbStudents.SelectedValue == null)
            {
                MessageBox.Show("Please select a student first.");
                return;
            }

            string studentName = ((DataRowView)cmbStudents.SelectedItem)["Name"].ToString();
            string studentId = cmbStudents.SelectedValue.ToString();

            SaveFileDialog sfd = new SaveFileDialog
            {
                Filter = "PDF files (*.pdf)|*.pdf",
                FileName = $"{studentName}_ReportCard.pdf"
            };

            if (sfd.ShowDialog() == DialogResult.OK)
            {
                ExportToPDF(sfd.FileName, studentId, studentName);
                MessageBox.Show("Report card exported successfully!");
            }
        }

        private void ExportToPDF(string filePath, string studentId, string studentName)
        {
            Document doc = new Document(PageSize.A4);
            PdfWriter.GetInstance(doc, new FileStream(filePath, FileMode.Create));
            doc.Open();

            var titleFont = FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 18);
            var sectionFont = FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12);
            var textFont = FontFactory.GetFont(FontFactory.HELVETICA, 10);

            doc.Add(new Paragraph($"Report Card for {studentName} ({studentId})", titleFont));
            doc.Add(new Paragraph($"Date: {DateTime.Now:dd MMM yyyy}\n\n", textFont));

            // Grades
            doc.Add(new Paragraph("Grades", sectionFont));
            PdfPTable gradeTable = new PdfPTable(3) { WidthPercentage = 100 };
            gradeTable.AddCell("Subject");
            gradeTable.AddCell("Score");
            gradeTable.AddCell("Remarks");

            DataTable grades = db.GetAllGrades();
            var studentGrades = grades.AsEnumerable().Where(r => r["StudentID"].ToString() == studentId);

            if (studentGrades.Any())
            {
                foreach (var row in studentGrades)
                {
                    decimal score = Convert.ToDecimal(row["Score"]);
                    gradeTable.AddCell(row["Subject"].ToString());
                    gradeTable.AddCell(score.ToString("F2"));
                    gradeTable.AddCell(score >= 50 ? "Pass" : "Fail");
                }
            }
            else
            {
                PdfPCell noGradesCell = new PdfPCell(new Phrase("No grades available"))
                {
                    Colspan = 3,
                    HorizontalAlignment = Element.ALIGN_CENTER
                };
                gradeTable.AddCell(noGradesCell);
            }

            doc.Add(gradeTable);
            doc.Add(new Paragraph("\n"));

            // Attendance
            doc.Add(new Paragraph("Attendance Summary", sectionFont));
            PdfPTable attendanceTable = new PdfPTable(2) { WidthPercentage = 100 };
            attendanceTable.AddCell("Date");
            attendanceTable.AddCell("Status");

            DataTable attendance = db.GetAllAttendance();
            var studentAttendance = attendance.AsEnumerable().Where(r => r["StudentID"].ToString() == studentId);

            if (studentAttendance.Any())
            {
                foreach (var row in studentAttendance)
                {
                    attendanceTable.AddCell(Convert.ToDateTime(row["Date"]).ToShortDateString());
                    attendanceTable.AddCell(row["Status"].ToString());
                }
            }
            else
            {
                PdfPCell noAttendanceCell = new PdfPCell(new Phrase("No attendance records"))
                {
                    Colspan = 2,
                    HorizontalAlignment = Element.ALIGN_CENTER
                };
                attendanceTable.AddCell(noAttendanceCell);
            }

            doc.Add(attendanceTable);
            doc.Close();
        }
    }
}
