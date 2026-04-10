using System;

public class AttendanceRecord
{
    public string StudentID { get; set; }

    // Use DateTime for the date to allow easier manipulation and comparison
    public DateTime Date { get; set; }

    // Use Enum for Status to enforce consistency (e.g., "Present", "Absent")
    public AttendanceStatus Status { get; set; }
}

// Enum to represent attendance status
public enum AttendanceStatus
{
    Present,
    Absent,
    Late,
    Excused
}

