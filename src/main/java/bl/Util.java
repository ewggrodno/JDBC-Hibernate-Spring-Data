package bl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/H2 - myTest";
    private static final String DB_USERNAME = "ewgen";
    private static final String DB_PASSWORD = "Alinochka2010";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection: OK!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection: ERROR!");
            e.printStackTrace();
        }

        return connection;
    }
}
