package crud.dao;

import crud.entity.Department;
import crud.util.DaoService;
import crud.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDeoImp implements DaoService<Department> {
    @Override
    public List<Department> findAll() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "select * from tbldepartment";
        try (Connection conn = MySQLConnection.getConnection();){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("departmentId"));
                department.setName(rs.getString("name"));
                department.setDescription(rs.getString("description"));
                departments.add(department);
            }

        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return departments;
    }

    @Override
    public int updateData(Department obj) throws SQLException, ClassNotFoundException {
       int result = 0;
       String query = "update tbldepartment set name=?, description=? where id=?";
       try (Connection conn = MySQLConnection.getConnection();){
           PreparedStatement stmt = conn.prepareStatement(query);
           stmt.setString(1, obj.getName());
           stmt.setString(2, obj.getDescription());
           stmt.setInt(3, obj.getId());
           if(stmt.executeUpdate() !=0) {
               conn.commit();
               result = 1;
           }else {
               conn.rollback();
           }

       }

        return 0;
    }

    @Override
    public int addData(Department obj) throws SQLException, ClassNotFoundException {

        int result=0;
        String query = "insert into tbldepartment values(?,?)";
        try(Connection conn = MySQLConnection.getConnection();){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, obj.getName());
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
    public int deleteData(Department obj) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public List<Department> searchByName(String name) throws SQLException, ClassNotFoundException {
        return List.of();
    }


}
