package bl;


import java.sql.*;

public class Util {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/myTest";
    private static final String DB_USERNAME = "ewgen";
    private static final String DB_PASSWORD = "Alinochka2010";

    public static Connection getConnection(){
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

    public static void clearAllTables(){
        Connection connection = getConnection();
        System.out.println("Очистка БД");

        String ADDRESS = "DELETE FROM ADDRESS";
        String EMPLOYEE = "DELETE FROM EMPLOYEE";
        String PROJECT = "DELETE FROM PROJECT";
        String EMPL_PROJ = "DELETE FROM EMPL_PROJ";

        try {
            System.out.printf("Удаляем все: %s\n", EMPL_PROJ);
            PreparedStatement preparedStatement = connection.prepareStatement(EMPL_PROJ);
            preparedStatement.executeUpdate();

            System.out.printf("Удаляем все: %s\n", PROJECT);
            preparedStatement = connection.prepareStatement(PROJECT);
            preparedStatement.executeUpdate();

            System.out.printf("Удаляем все: %s\n", EMPLOYEE);
            preparedStatement = connection.prepareStatement(EMPLOYEE);
            preparedStatement.executeUpdate();

            System.out.printf("Удаляем все: %s\n", ADDRESS);
            preparedStatement = connection.prepareStatement(ADDRESS);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
            System.out.println("Очистка БД - OK!");
        } catch (SQLException e) {
            System.out.println("Очистка БД - ERROR!");
            e.printStackTrace();
        }
    }
}
