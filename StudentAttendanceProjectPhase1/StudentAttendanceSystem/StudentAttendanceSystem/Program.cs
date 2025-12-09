using System;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    static class Program
    {
        [STAThread] // Single Threaded Apartment model for Windows Forms
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Set the startup form here:
            Application.Run(new RegistrationForm());
        }
    }
}
