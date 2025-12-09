package webFacultyREST;

public class Faculty {
    private Integer f_Id;
    private String f_Lname;
    private String f_Fname;
    private String f_zipcodeBirth;
    private double f_Salary;
    private double f_BonusRate;

    public Faculty(Integer f_Id, String f_Lname, String f_Fname, String f_zipcodeBirth,
                   double f_Salary, double f_BonusRate) {
        this.f_Id = f_Id;
        this.f_Lname = f_Lname;
        this.f_Fname = f_Fname;
        this.f_zipcodeBirth = f_zipcodeBirth;
        this.f_Salary = f_Salary;
        this.f_BonusRate = f_BonusRate;
    }

    // Getters
    public Integer getF_Id() {
        return f_Id;
    }

    public String getF_Lname() {
        return f_Lname;
    }

    public String getF_Fname() {
        return f_Fname;
    }

    public String getF_zipcodeBirth() {
        return f_zipcodeBirth;
    }

    public double getF_Salary() {
        return f_Salary;
    }

    public double getF_BonusRate() {
        return f_BonusRate;
    }

    // Bonus calculation: (f_Salary * f_BonusRate / 100)
    public double doCalc_Bonus() {
        return f_Salary * f_BonusRate / 100;
    }
}

