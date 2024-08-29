package crud.dao;

import crud.entity.Attendance;
import crud.util.DaoService;
import crud.util.MySQLConnection;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDaoImp implements DaoService<Attendance> {

    @Override
    public List<Attendance> findAll() throws SQLException, ClassNotFoundException, FileNotFoundException {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT tblattendance.id,tblemployee.employeeId,tblemployee.fullName as empFullName,tblemployee.fatherName as empFatherName,tblattendance.department_id,tbldepartment.name as departmentName,tblattendance.isPresent,tblattendance.a_date from tblattendance LEFT join tblemployee on tblattendance.employee_id=tblemployee.employeeId LEFT JOIN tbldepartment on tblattendance.department_id=tbldepartment.departmentId ORDER by tblemployee.employeeId asc, tblattendance.a_date DESC";
        try(Connection connection= MySQLConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Attendance attendance=new Attendance();
                attendance.setId(resultSet.getInt("id"));
                attendance.setEmployeeId(resultSet.getInt("employeeId"));
                attendance.setEmployeeName(resultSet.getString("empFullName"));
                attendance.setEmployeeFatherName(resultSet.getString("empFatherName"));
                attendance.setDepartmentId(resultSet.getInt("department_id"));
                attendance.setDepartmentName(resultSet.getString("departmentName"));
                attendance.setDate(resultSet.getString("a_date"));
                attendance.setPresent(resultSet.getBoolean("isPresent"));
                attendances.add(attendance);
            }
        }
        return attendances;
    }

    @Override
    public int updateData(Attendance obj) throws SQLException, ClassNotFoundException {
        return 0;
    }


    @Override
    public int addData(Attendance obj) throws SQLException, ClassNotFoundException {
        int result=0;
        String query = "insert into tblattendance(employee_id,department_id,isPresent,a_date) values(?,?,?,?)";
        try(Connection conn = MySQLConnection.getConnection();){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,obj.getEmployeeId());
            ps.setInt(2, obj.getDepartmentId());
            ps.setBoolean(3, obj.isPresent());
            ps.setString(4, obj.getDate());
            if(ps.executeUpdate()!=0){
                conn.commit();
                result = 1;
            }else {
                conn.rollback();
            }

        }


        return result;
    }




    @Override
    public int deleteData(Attendance obj) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public List<Attendance> searchByName(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return List.of();
    }
}
