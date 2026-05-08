package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String URL = "jdbc:postgresql://localhost:5432/Cinematograf";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection connect() {
        try {
            Connection connection =
                    DriverManager.getConnection(URL,USER,PASSWORD);
            return connection;
        } catch (SQLException e) {

            System.out.println("Connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}