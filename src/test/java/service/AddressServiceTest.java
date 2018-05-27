package service;

import jdbc.bl.Util;
import entity.Address;
import jdbc.service.AddressService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static testData.AddressData.*;

public class AddressServiceTest {

    private AddressService addressService = new AddressService();

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        addressService.add(ADDRESS_1);
        addressService.add(ADDRESS_2);
        addressService.add(ADDRESS_3);
    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_ADDRESS() {
        Util.clearAllTables();

        addressService.add(ADDRESS_1);
        addressService.add(ADDRESS_2);
        addressService.add(ADDRESS_3);

        List<Address> expected = new ArrayList<>();
        expected.add(ADDRESS_1);
        expected.add(ADDRESS_2);
        expected.add(ADDRESS_3);

        List<Address> actual = addressService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_ADDRESS() {
        List<Address> expected = new ArrayList<>();
        expected.add(ADDRESS_1);
        expected.add(ADDRESS_2);
        expected.add(ADDRESS_3);

        List<Address> actual = addressService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getById_ADDRESS() {
        Address actual1 = addressService.getById(1);
        Address actual2 = addressService.getById(2);
        Address actual3 = addressService.getById(3);

        assertEquals(ADDRESS_1, actual1);
        assertEquals(ADDRESS_2, actual2);
        assertEquals(ADDRESS_3, actual3);
    }

    @Test
    public void update_ADDRESS() {
        Address address1 = new Address(
                1, "updateCountry 1", "updateCity 1", "updateStreet 1", "update000001");
        Address address2 = new Address(
                2, "updateCountry 2", "updateCity 2", "updateStreet 2", "update000002");
        Address address3 = new Address(
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
        addressService.remove(ADDRESS_1);

        List<Address> expected = new ArrayList<>();
        expected.add(ADDRESS_2);
        expected.add(ADDRESS_3);

        List<Address> actual = addressService.getAll();

        assertEquals(expected, actual);
    }
}