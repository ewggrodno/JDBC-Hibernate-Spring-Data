package service;

import bl.Util;
import dao.EmployeeDAO;
import entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements EmployeeDAO {

    private String ID = "ID";
    private String FIRST_NAME = "FIRST_NAME";
    private String LAST_NAME = "LAST_NAME";
    private String BIRTHDAY = "BIRTHDAY";
    private String ADDRESS_ID = "ADDRESS_ID";

    @Override
    public void add(Employee employee) {
        String sql = String.format("INSERT INTO EMPLOYEE (%s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?)",
                ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstNme());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setDate(4, employee.getBirthday());
            preparedStatement.setLong(5, employee.getAddressId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAll() {
        String sql = String.format("SELECT %s, %s, %s, %s, %s FROM EMPLOYEE",
                ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID);

        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getLong(ID));
                employee.setFirstNme(resultSet.getString(FIRST_NAME));
                employee.setLastName(resultSet.getString(LAST_NAME));
                employee.setBirthday(resultSet.getDate(BIRTHDAY));
                employee.setAddressId(resultSet.getLong(ADDRESS_ID));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public Employee getById(Long id) {
        String sql = String.format(
                "SELECT %s, %s, %s, %s, %s FROM EMPLOYEE WHERE ID=?",
                ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID);

        Employee employee = new Employee();
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            employee.setId(resultSet.getLong(ID));
            employee.setFirstNme(resultSet.getString(FIRST_NAME));
            employee.setLastName(resultSet.getString(LAST_NAME));
            employee.setBirthday(resultSet.getDate(BIRTHDAY));
            employee.setAddressId(resultSet.getLong(ADDRESS_ID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void update(Employee employee) {
        String sql = String.format(
                "UPDATE EMPLOYEE SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID, ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, employee.getFirstNme());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, employee.getBirthday());
            preparedStatement.setLong(4, employee.getAddressId());
            preparedStatement.setLong(5, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Employee employee) {
        String sql = String.format("DELETE FROM EMPLOYEE WHERE %s=?", ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
