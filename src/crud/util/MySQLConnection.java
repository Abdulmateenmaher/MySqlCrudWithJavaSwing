package crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String url="jdbc:mysql://localhost:3306/javaswingcrudwithmysql";
    private static final String user="root";
    private static final String password="";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection(url,user,password);
        conn.setAutoCommit(false);
        return conn;
    }
}
