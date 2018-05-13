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
            clearTableEmplProj(connection);
            clearTableProject(connection);
            clearTableEmployee(connection);
            clearTableAddress(connection);
            System.out.println("Finish clearing Database");
        } catch (SQLException e) {
            System.out.println("Clearing Database: ERROR");
            e.printStackTrace();
        }
    }

    private static void clearTableEmplProj(Connection connection) throws SQLException {
        String sql = "DELETE FROM EMPL_PROJ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Clear table: EMPL_PROJ - OK!");
    }

    private static void clearTableProject(Connection connection) throws SQLException {
        String sql = "DELETE FROM PROJECT";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Clear table: PROJECT - OK!");
    }

    private static void clearTableEmployee(Connection connection) throws SQLException {
        String sql = "DELETE FROM EMPLOYEE";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Clear table: EMPLOYEE - OK!");
    }

    private static void clearTableAddress(Connection connection) throws SQLException {
        String sql = "DELETE FROM ADDRESS";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Clear table: ADDRESS - OK!");
    }
}
