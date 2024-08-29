package crud.entity;

public class EmployeeJcomboxListForAttendance {
    private  int employeeId;
    private  String  employeeName;
    private  String  employeeFatherName;


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeFatherName() {
        return employeeFatherName;
    }

    public void setEmployeeFatherName(String employeeFatherName) {
        this.employeeFatherName = employeeFatherName;
    }

    @Override
    public String toString() {
            return  employeeId + "   "+ employeeName + " S/O " + employeeFatherName ;
    }
}
