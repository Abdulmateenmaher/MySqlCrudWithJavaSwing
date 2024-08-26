package crud.dao;

import crud.entity.Employee;
import crud.util.DaoService;
import crud.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDeoImp implements DaoService<Employee> {
    @Override
    public List<Employee> findAll() throws SQLException, ClassNotFoundException {
        List<Employee> employees = new ArrayList<Employee>();
        String query = "SELECT * FROM tblemployee";

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
                employee.setEmp_position_type_id(resultSet.getInt("positionTypeId"));
                employee.setEmp_salary(String.valueOf(resultSet.getDouble("salary")));
                employee.setEmp_status(resultSet.getString("status"));
                employee.setEmp_description(resultSet.getString("description"));
                employee.setEmp_image(resultSet.getString("image"));
                employees.add(employee);
            }

        return employees;
    }

    @Override
    public int updateData(Employee obj) throws SQLException, ClassNotFoundException {
        int result =0;
        String query="update tblemployee set fullName=?,fatherName=?,tazkeraNo=?,phoneNumber=?,whatsAppNo=?,positionTypeId=?,salary=? ,status=?,description=?,image=? where employeeId=? ";

        try(Connection connection= MySQLConnection.getConnection();){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,obj.getEmp_name());
            ps.setString(2,obj.getEmp_father_name());
            ps.setString(3,obj.getEmp_tazkira_no());
            ps.setString(4,obj.getEmp_phone_no());
            ps.setString(5,obj.getEmp_whatsapp_no());
            ps.setInt(6,obj.getEmp_position_type_id());
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
    public int addData(Employee obj) throws SQLException, ClassNotFoundException {
        int result =0;
        String query="INSERT INTO tblemployee( fullName, fatherName, tazkeraNo, phoneNumber, whatsAppNo, positionTypeId, salary, status, description, image) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try(Connection connection= MySQLConnection.getConnection();){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,obj.getEmp_name());
            ps.setString(2,obj.getEmp_father_name());
            ps.setString(3,obj.getEmp_tazkira_no());
            ps.setString(4,obj.getEmp_phone_no());
            ps.setString(5,obj.getEmp_whatsapp_no());
            ps.setInt(6,obj.getEmp_position_type_id());
            ps.setString(7,obj.getEmp_salary());
            ps.setString(8,obj.getEmp_status());
            ps.setString(9,obj.getEmp_description());
            ps.setString(10,obj.getEmp_image());

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
    public List<Employee> searchByName(String name) throws SQLException, ClassNotFoundException {
        List<Employee> employees = new ArrayList<Employee>();
        String query = "SELECT * FROM tblemployee WHERE CONCAT(employeeId,fullName,fatherName) LIKE '%"+name+"%' ORDER BY fullName ASC";

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
            employee.setEmp_position_type_id(resultSet.getInt("positionTypeId"));
            employee.setEmp_salary(String.valueOf(resultSet.getDouble("salary")));
            employee.setEmp_status(resultSet.getString("status"));
            employee.setEmp_description(resultSet.getString("description"));
            employee.setEmp_image(resultSet.getString("image"));
            employees.add(employee);
        }

        return employees;
    }

}
