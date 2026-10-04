package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/water_management";

    // MySQL Username
    private static final String USERNAME = "root";

    // MySQL Password
    private static final String PASSWORD = "root"; // Change if your password is different

    // Method to establish connection
    public static Connection getConnection() {

        Connection con = null;

        try {

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected Successfully.");

        }

        catch (ClassNotFoundException e) {

            System.out.println("JDBC Driver Not Found.");

        }

        catch (SQLException e) {

            System.out.println("Connection Failed.");

            e.printStackTrace();

        }

        return con;

    }

}