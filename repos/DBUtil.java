package repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static Connection connection = null;
    // Private constructor for singleton class
    private DBUtil() {}

    public static Connection getConnection() throws SQLException {
        if(connection == null) {
            String username = "root";
            String password = "12345678";
            String url = "jdbc:mysql://localhost:3306/Project_Repository_Database";
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
