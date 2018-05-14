package service;

import bl.Util;
import entity.Address;
import entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private AddressService addressService = new AddressService();
    private EmployeeService employeeService = new EmployeeService();

    private Address address1 = null;
    private Address address2 = null;
    private Address address3 = null;

    private Employee employee1 = null;
    private Employee employee2 = null;
    private Employee employee3 = null;

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        address1 = new Address(1, "Country 1", "City 1", "Street 1", "000001");
        address2 = new Address(2, "Country 2", "City 2", "Street 2", "000002");
        address3 = new Address(3, "Country 3", "City 3", "Street 3", "000003");

        employee1 = new Employee(1, "firstName 1", "lastName 1", new Date(5000), 3);
        employee2 = new Employee(2, "firstName 2", "lastName 3", new Date(10000), 2);
        employee3 = new Employee(3, "firstName 3", "lastName 3", new Date(20000), 1);

        addressService.add(address1);
        addressService.add(address2);
        addressService.add(address3);

        employeeService.add(employee1);
        employeeService.add(employee2);
        employeeService.add(employee3);
    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_EMPLOYEE() {
        Util.clearAllTables();

        addressService.add(address1);
        addressService.add(address2);
        addressService.add(address3);

        employeeService.add(employee1);
        employeeService.add(employee2);
        employeeService.add(employee3);

        List<Employee> actual = employeeService.getAll();
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_EMPLOYEE() {
        List<Employee> expected = employeeService.getAll();

        List<Employee> actual = new ArrayList<>();
        actual.add(employee1);
        actual.add(employee2);
        actual.add(employee3);

        assertEquals(expected, actual);
    }

    @Test
    public void getById_EMPLOYEE() {
        Employee actual1 = employeeService.getById(1);
        Employee actual2 = employeeService.getById(2);
        Employee actual3 = employeeService.getById(3);

        assertEquals(employee1, actual1);
        assertEquals(employee2, actual2);
        assertEquals(employee3, actual3);
    }

    @Test
    public void update_EMPLOYEE() {
        employee1 = new Employee(1, "updatefirstName 1", "updatelastName 1", new Date(5000 + 500), 1);
        employee2 = new Employee(2, "updatefirstName 2", "updatelastName 3", new Date(10000 + 500), 3);
        employee3 = new Employee(3, "updatefirstName 3", "updatelastName 3", new Date(20000 + 500), 2);

        employeeService.update(employee1);
        employeeService.update(employee2);
        employeeService.update(employee3);

        Employee actual1 = employeeService.getById(1);
        Employee actual2 = employeeService.getById(2);
        Employee actual3 = employeeService.getById(3);

        assertEquals(employee1, actual1);
        assertEquals(employee2, actual2);
        assertEquals(employee3, actual3);
    }

    @Test
    public void remove_EMPLOYEE() {
        employeeService.remove(employee1);

        List<Employee> expected = new ArrayList<>();
        expected.add(employee2);
        expected.add(employee3);

        List<Employee> actual = employeeService.getAll();

        assertEquals(expected, actual);
    }
}