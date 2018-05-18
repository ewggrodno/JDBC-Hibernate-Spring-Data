package service;

import bl.Util;
import dao.AddressDAO;
import entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressService implements AddressDAO {

    private static Connection connection = Util.getConnection();

    private static final String ID = "id";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String POST_CODE = "post_code";

    @Override
    public void add(Address address) {
        String sql = String.format(
                "INSERT INTO address (%s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?)",
                ID, COUNTRY, CITY, STREET, POST_CODE);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, address.getId());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getPostCode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> getAll() {
        String sql = String.format(
                "SELECT %s, %s, %s, %s, %s FROM address",
                ID, COUNTRY, CITY, STREET, POST_CODE);

        List<Address> addressList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Address address = new Address();

                address.setId(resultSet.getLong(ID));
                address.setCountry(resultSet.getString(COUNTRY));
                address.setCity(resultSet.getString(CITY));
                address.setStreet(resultSet.getString(STREET));
                address.setPostCode(resultSet.getString(POST_CODE));

                addressList.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    @Override
    public Address getById(long id) {
        String sql = String.format(
                "SELECT %s, %s, %s, %s, %s FROM address WHERE ID=?",
                ID, COUNTRY, CITY, STREET, POST_CODE);

        Address address = new Address();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            address.setId(resultSet.getLong(ID));
            address.setCountry(resultSet.getString(COUNTRY));
            address.setCity(resultSet.getString(CITY));
            address.setStreet(resultSet.getString(STREET));
            address.setPostCode(resultSet.getString(POST_CODE));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void update(Address address) {
        String sql = String.format(
                "UPDATE address SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                COUNTRY, CITY, STREET, POST_CODE, ID);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getPostCode());
            preparedStatement.setLong(5, address.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Address address) {
        String sql = String.format(
                "DELETE FROM address WHERE %s=?",
                ID);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, address.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
