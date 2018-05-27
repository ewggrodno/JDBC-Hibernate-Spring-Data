package jdbc.bl;

import java.sql.*;

public class Util {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/myTest";
    private static final String DB_USERNAME = "ewgen";
    private static final String DB_PASSWORD = "Alinochka2010";

    private static volatile Connection connection = null;

    private Util() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (Util.class) {
                if (connection == null) {
                    try {
                        Class.forName(DB_DRIVER);
                        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                        System.out.println("Connection: OPEN!");
                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println("Connection: ERROR OPEN!");
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        clearAllTables();
        closeConnection();
    }

    public static void closeConnection(){
        if (connection != null){
            try {
                connection.close();
                connection = null;
                System.out.println("Connection: CLOSE!");
            } catch (SQLException e) {
                System.out.println("Connection: ERROR CLOSE!");
                e.printStackTrace();
            }
        }
    }

    public static void clearAllTables() {
        if (connection == null){
            getConnection();
        }
        try {
            System.out.println("Start clearing Database");
            clearTable("empl_proj");
            clearTable("project");
            clearTable("employee");
            clearTable("address");
            System.out.println("Finish clearing Database");
        } catch (SQLException e) {
            System.out.println("Clearing Database: ERROR");
            e.printStackTrace();
        }
    }

    private static void clearTable(String nameTable) throws SQLException {
        String sql = String.format("DELETE FROM %s", nameTable);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.printf("Clear table: %s - OK!\n", nameTable);
    }
}
