package service;

import bl.Util;
import entity.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddressServiceTest {

    private AddressService addressService = new AddressService();

    private Address address1 = null;
    private Address address2 = null;
    private Address address3 = null;

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        address1 = new Address(1, "Country 1", "City 1", "Street 1", "000001");
        address2 = new Address(2, "Country 2", "City 2", "Street 2", "000002");
        address3 = new Address(3, "Country 3", "City 3", "Street 3", "000003");

        addressService.add(address1);
        addressService.add(address2);
        addressService.add(address3);
    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_ADDRESS() {
        Util.clearAllTables();

        addressService.add(address1);
        addressService.add(address2);
        addressService.add(address3);

        List<Address> expected = new ArrayList<>();
        expected.add(address1);
        expected.add(address2);
        expected.add(address3);

        List<Address> actual = addressService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_ADDRESS() {
        List<Address> expected = new ArrayList<>();
        expected.add(address1);
        expected.add(address2);
        expected.add(address3);

        List<Address> actual = addressService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getById_ADDRESS() {
        Address actual1 = addressService.getById(1);
        Address actual2 = addressService.getById(2);
        Address actual3 = addressService.getById(3);

        assertEquals(address1, actual1);
        assertEquals(address2, actual2);
        assertEquals(address3, actual3);
    }

    @Test
    public void update_ADDRESS() {
        address1 = new Address(
                1, "updateCountry 1", "updateCity 1", "updateStreet 1", "update000001");
        address2 = new Address(
                2, "updateCountry 2", "updateCity 2", "updateStreet 2", "update000002");
        address3 = new Address(
                3, "updateCountry 3", "updateCity 3", "updateStreet 3", "update000003");

        addressService.update(address1);
        addressService.update(address2);
        addressService.update(address3);

        Address actual1 = addressService.getById(1);
        Address actual2 = addressService.getById(2);
        Address actual3 = addressService.getById(3);

        assertEquals(address1, actual1);
        assertEquals(address2, actual2);
        assertEquals(address3, actual3);
    }

    @Test
    public void remove_ADDRESS() {
        addressService.remove(address1);

        List<Address> expected = new ArrayList<>();
        expected.add(address2);
        expected.add(address3);

        List<Address> actual = addressService.getAll();

        assertEquals(expected, actual);
    }
}