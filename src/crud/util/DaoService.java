package crud.util;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {
 List<T> findAll() throws SQLException, ClassNotFoundException, FileNotFoundException;
 int updateData(T obj) throws SQLException, ClassNotFoundException;
 int addData(T obj) throws SQLException, ClassNotFoundException;
 int deleteData(T obj) throws SQLException, ClassNotFoundException;
 List<T> searchByName(String name) throws SQLException, ClassNotFoundException, FileNotFoundException;

}
