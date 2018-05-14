package bl;


import java.sql.*;

public class Util {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/myTest";
    private static final String DB_USERNAME = "ewgen";
    private static final String DB_PASSWORD = "Alinochka2010";

    public static Connection getConnection() {
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

    public static void main(String[] args) {
        clearAllTables();
    }

    public static void clearAllTables() {
        try (Connection connection = Util.getConnection()){
            System.out.println("Start clearing Database");
            clearTable(connection, "empl_proj");
            clearTable(connection, "project");
            clearTable(connection, "employee");
            clearTable(connection, "address");
            System.out.println("Finish clearing Database");
        } catch (SQLException e) {
            System.out.println("Clearing Database: ERROR");
            e.printStackTrace();
        }
    }

    private static void clearTable(Connection connection, String nameTable) throws SQLException {
        String sql = String.format("DELETE FROM %s", nameTable);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.printf("Clear table: %s - OK!\n", nameTable);
    }

}
