package crud.dao;

import crud.entity.Employee;
import crud.entity.EmployeeJcomboxListForAttendance;
import crud.util.DaoService;
import crud.util.MySQLConnection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class EmployeeDeoImp implements DaoService<Employee> {
    @Override
    public List<Employee> findAll() throws SQLException, ClassNotFoundException, FileNotFoundException {
        List<Employee> employees = new ArrayList<Employee>();
        String query = "SELECT DISTINCT tblemployee.employeeId,fullName,fatherName,tazkeraNo,phoneNumber,whatsAppNo,tbldepartment.name as departmentName,tblemployee.salary,tblemployee.status,tblemployee.description,tblemployee.image" +
                " from tblemployee left join tbldepartment on tbldepartment.departmentId=tblemployee.departmentId";

            Connection connection= MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmp_id(resultSet.getInt("employeeId"));
                employee.setEmp_name(resultSet.getString("fullName"));
                employee.setEmp_father_name(resultSet.getString("fatherName"));
                employee.setEmp_tazkira_no(resultSet.getString("tazkeraNo"));
                employee.setEmp_phone_no(resultSet.getString("phoneNumber"));
                employee.setEmp_whatsapp_no(resultSet.getString("whatsappNo"));
                employee.setEmp_departmentName(resultSet.getString("departmentName"));
                employee.setEmp_salary(String.valueOf(resultSet.getDouble("salary")));
                employee.setEmp_status(resultSet.getString("status"));
                employee.setEmp_description(resultSet.getString("description"));

            //    String imageName = resultSet.getString(2);
               // byte[] image=resultSet.getBytes("image");
//                FileOutputStream fileOutputStream=new FileOutputStream(
//                        "C:/userImages/"+imageName);
//                System.out.println(imageName+"has been stored in c:userImages");
                System.out.println(resultSet.getString("fullName"));
                employees.add(employee);
            }
        return employees;
    }
// ***************** Update Employee ********************
    @Override
    public int updateData(Employee obj) throws SQLException, ClassNotFoundException {
        int result =0;
        String query="update tblemployee set fullName=?,fatherName=?,tazkeraNo=?,phoneNumber=?,whatsAppNo=?,departmentId=?,salary=? ,status=?,description=?,image=? where employeeId=? ";

        try(Connection connection= MySQLConnection.getConnection();){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,obj.getEmp_name());
            ps.setString(2,obj.getEmp_father_name());
            ps.setString(3,obj.getEmp_tazkira_no());
            ps.setString(4,obj.getEmp_phone_no());
            ps.setString(5,obj.getEmp_whatsapp_no());
            ps.setString(6,obj.get_departmentName());
            ps.setString(7,obj.getEmp_salary());
            ps.setString(8,obj.getEmp_status());
            ps.setString(9,obj.getEmp_description());
            ps.setString(10,obj.getEmp_image());
            ps.setInt(11,obj.getEmp_id());
            if(ps.executeUpdate()!=0){
                connection.commit();
                result=1;
            }else {
                connection.rollback();
            }
        }
        return result;
    }

    @Override
    public  int addData(Employee obj) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "INSERT INTO tblemployee( fullName, fatherName, tazkeraNo, phoneNumber, whatsAppNo, departmentId, salary, status, description, image) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = MySQLConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, obj.getEmp_name());
            ps.setString(2, obj.getEmp_father_name());
            ps.setString(3, obj.getEmp_tazkira_no());
            ps.setString(4, obj.getEmp_phone_no());
            ps.setString(5, obj.getEmp_whatsapp_no());
            int departmentId = parseInt(obj.get_departmentName());
            ps.setInt(6, departmentId);
            ps.setString(7, obj.getEmp_salary());
            ps.setString(8, obj.getEmp_status());
            ps.setString(9, obj.getEmp_description());
            ps.setString(10, obj.getEmp_image());

//            System.out.println("*****************" + obj.getEmp_image());
//            String filePath = obj.getImagePath();
//            File image = new File(obj.getEmp_image());
//            FileInputStream fis = new FileInputStream(image);
//            ps.setBinaryStream(10, fis);
//            Path sourcePath = Paths.get( obj.getEmp_image());
//            Path targetPath = Paths.get("C:\\Users\\pc\\Desktop\\dest\\" + obj.getEmp_image());
//            Path movedPath = Files.move(sourcePath, targetPath);
//            if (movedPath != null) {
//                System.out.println("File renamed and moved successfully");
//            } else {
//                System.out.println("Failed to move the file");
//            }
//
            if(ps.executeUpdate()!=0){
                connection.commit();
                result=1;
            }else {
                connection.rollback();
            }
        }
        return result;
    }

    @Override
    public int deleteData(Employee obj) throws SQLException, ClassNotFoundException {
        int result=0;
        String query="DELETE FROM tblemployee where employeeId=?";
        try(Connection connection= MySQLConnection.getConnection();){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,obj.getEmp_id());
            if(ps.executeUpdate()!=0){
                result=1;
                connection.commit();
            }
            else {
                connection.rollback();
            }
        }
        return result;
    }

    @Override
    public List<Employee> searchByName(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        List<Employee> employees = new ArrayList<Employee>();
        String query = "SELECT DISTINCT tblemployee.employeeId,fullName,fatherName,tazkeraNo,phoneNumber,whatsAppNo,tbldepartment.name as departmentName,tblemployee.salary,tblemployee.status,tblemployee.description,tblemployee.image\" +\n" +
                "                \" from tblemployee left join tbldepartment on tbldepartment.departmentId=tblemployee.departmentId" +
                " WHERE CONCAT(employeeId,fullName,fatherName) LIKE '%"+name+"%' ORDER BY fullName ASC";

        Connection connection= MySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setEmp_id(resultSet.getInt("employeeId"));
            employee.setEmp_name(resultSet.getString("fullName"));
            employee.setEmp_father_name(resultSet.getString("fatherName"));
            employee.setEmp_tazkira_no(resultSet.getString("tazkeraNo"));
            employee.setEmp_phone_no(resultSet.getString("phoneNumber"));
            employee.setEmp_whatsapp_no(resultSet.getString("whatsappNo"));
            employee.setEmp_departmentName(resultSet.getString("departmentName"));
            employee.setEmp_salary(String.valueOf(resultSet.getDouble("salary")));
            employee.setEmp_status(resultSet.getString("status"));
            employee.setEmp_description(resultSet.getString("description"));
            String imageName = resultSet.getString(2);
            Blob imageBlob = resultSet.getBlob(3);
            FileOutputStream fileOutputStream=new FileOutputStream(
                    "C:/userImages/"+imageName);
            employees.add(employee);
        }

        return employees;
    }

    public  List<EmployeeJcomboxListForAttendance> employeesJcomboxList() throws SQLException, ClassNotFoundException {
        List<EmployeeJcomboxListForAttendance> employeesJcombox =new ArrayList<>();
        String query = "SELECT DISTINCT tblemployee.employeeId,fullName,fatherName,tazkeraNo,phoneNumber,whatsAppNo,tbldepartment.name as departmentName,tblemployee.salary,tblemployee.status,tblemployee.description,tblemployee.image" +
                " from tblemployee left join tbldepartment on tbldepartment.departmentId=tblemployee.departmentId";

        Connection connection= MySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            EmployeeJcomboxListForAttendance employee = new EmployeeJcomboxListForAttendance();
            employee.setEmployeeId(resultSet.getInt("employeeId"));
            employee.setEmployeeName(resultSet.getString("fullName"));
            employee.setEmployeeFatherName(resultSet.getString("fatherName"));
            employeesJcombox.add(employee);


        }
        return employeesJcombox;
    }

}
