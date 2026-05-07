package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connect {

    private static final String URL = "jdbc:postgresql://localhost:5432/Cinematograf";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to database successfully!");
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
