using StudentAttendanceSystem;
using System.Collections.Generic;
using System.Linq;
using System.Data;
using System.Windows.Forms;
using System;

public class AlertManager
{
    private DatabaseManager db = new DatabaseManager();

    public void CheckAlerts()
    {
        DataTable grades = db.GetAllGrades();
        DataTable attendance = db.GetAllAttendance();

        var alerts = new List<string>();

        foreach (DataRow grade in grades.Rows)
        {
            string studentId = grade["StudentID"].ToString();
            string subject = grade["Subject"].ToString();
            decimal score = Convert.ToDecimal(grade["Score"]);

            if (score < 50)
                alerts.Add($"❌ {studentId} is failing {subject} (Score: {score})");
        }

        var attendanceGroups = attendance.AsEnumerable()
            .GroupBy(r => r["StudentID"].ToString());

        foreach (var group in attendanceGroups)
        {
            string studentId = group.Key;
            int absences = group.Count(r => r["Status"].ToString() == "Absent");
            int lates = group.Count(r => r["Status"].ToString() == "Late");

            if (absences > 3)
                alerts.Add($"⚠️ {studentId} has {absences} absences this month.");
            if (lates > 2)
                alerts.Add($"⏰ {studentId} has {lates} late marks.");
        }

        if (alerts.Any())
            MessageBox.Show(string.Join("\n", alerts), "Alerts", MessageBoxButtons.OK, MessageBoxIcon.Warning);
        else
            MessageBox.Show("✅ No alerts found. All students are doing fine!");
    }
}
