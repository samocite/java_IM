package im.server;

import java.sql.*;

public class DBUtil {

    String driver;
    String url;
    String username;
    String password;
    Connection con;
    PreparedStatement pstmt;

    public DBUtil() {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
        this.username = "root";
        this.password = "root";
    }

    public void getDBconnect() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    private void close() {
        try {
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
