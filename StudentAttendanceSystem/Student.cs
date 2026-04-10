public class Student
{
    public string Name { get; set; }
    public string ID { get; set; }
    public string Class { get; set; }
    public string Section { get; set; }
    public string Contact { get; set; }

    // Parameterless constructor
    public Student() { }

    // Constructor to initialize student object
    public Student(string name, string id, string className, string section, string contact)
    {
        Name = name;
        ID = id;
        Class = className;
        Section = section;
        Contact = contact;
    }
}
