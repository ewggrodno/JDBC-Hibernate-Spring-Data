package service;

import bl.Util;
import dao.EmployeeDAO;
import entity.Employee;

import java.sql.*;
import java.util.*;

public class EmployeeService implements EmployeeDAO {

    private static Connection connection = Util.getConnection();

    private String ID = "id";
    private String FIRST_NAME = "first_name";
    private String LAST_NAME = "last_name";
    private String BIRTHDAY = "birthday";
    private String ADDRESS_ID = "address_id";

    private AddressService addressService = new AddressService();

    @Override
    public void add(Employee employee) {
        String sql = String.format("INSERT INTO employee (%s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?)",
                ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setDate(4, employee.getBirthday());
            preparedStatement.setLong(5, employee.getAddress().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAll() {
        String sql = String.format("SELECT %s, %s, %s, %s, %s FROM employee",
                ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID);

        List<Employee> employeeList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getLong(ID));
                employee.setFirstName(resultSet.getString(FIRST_NAME));
                employee.setLastName(resultSet.getString(LAST_NAME));
                employee.setBirthday(resultSet.getDate(BIRTHDAY));
                employee.setAddress(addressService.getById(resultSet.getLong(ADDRESS_ID)));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public Employee getById(long id) {
        String sql = String.format(
                "SELECT %s, %s, %s, %s, %s FROM employee WHERE ID=?",
                ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID);

        Employee employee = new Employee();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            employee.setId(resultSet.getLong(ID));
            employee.setFirstName(resultSet.getString(FIRST_NAME));
            employee.setLastName(resultSet.getString(LAST_NAME));
            employee.setBirthday(resultSet.getDate(BIRTHDAY));
            employee.setAddress(addressService.getById(resultSet.getLong(ADDRESS_ID)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void update(Employee employee) {
        String sql = String.format(
                "UPDATE employee SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID, ID);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, employee.getBirthday());
            preparedStatement.setLong(4, employee.getAddress().getId());
            preparedStatement.setLong(5, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Employee employee) {
        String sql = String.format("DELETE FROM employee WHERE %s=?", ID);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
