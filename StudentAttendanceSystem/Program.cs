using System;
using System.Windows.Forms;

namespace StudentAttendanceSystem
{
    static class Program
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Start the application with RegistrationForm as the main form.
            Application.Run(new RegistrationForm());
        }
    }
}

