package crud.entity;

import java.io.File;

public class Employee {
    private int emp_id;
    private String emp_name;
    private String emp_father_name;
    private String emp_tazkira_no;
    private String emp_phone_no;
    private String emp_whatsapp_no;
    private String departmentName;
    private String emp_salary;
    private String emp_status;
    private String emp_description;
    private String fileImage;
    private String imagePath;
    public Employee() {}

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public String getEmp_phone_no() {
        return emp_phone_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getEmp_father_name() {
        return emp_father_name;
    }

    public String getEmp_tazkira_no() {
        return emp_tazkira_no;
    }

    public String getEmp_whatsapp_no() {
        return emp_whatsapp_no;
    }

    public String get_departmentName() {
        return departmentName;
    }

    public String getEmp_salary() {
        return emp_salary;
    }

    public String getEmp_status() {
        return emp_status;
    }

    public String getEmp_description() {
        return emp_description;
    }

    public String getEmp_image() {
        return fileImage;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setEmp_father_name(String emp_father_name) {
        this.emp_father_name = emp_father_name;
    }

    public void setEmp_tazkira_no(String emp_tazkira_no) {
        this.emp_tazkira_no = emp_tazkira_no;
    }

    public void setEmp_whatsapp_no(String emp_whatsapp_no) {
        this.emp_whatsapp_no = emp_whatsapp_no;
    }

    public void setEmp_departmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setEmp_salary(String emp_salary) {
        this.emp_salary = emp_salary;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }

    public void setEmp_description(String emp_description) {
        this.emp_description = emp_description;
    }

    public void setEmp_phone_no(String emp_phone_no) {
        this.emp_phone_no = emp_phone_no;
    }

    public void setEmp_image(String file) {
        this.fileImage = file;
    }
}
