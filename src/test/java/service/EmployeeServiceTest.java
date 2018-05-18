package service;

import bl.Util;
import entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static testData.AddressData.*;
import static testData.EmployeeData.*;

public class EmployeeServiceTest {

    private AddressService addressService = new AddressService();
    private EmployeeService employeeService = new EmployeeService();

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        addressService.add(ADDRESS_1);
        addressService.add(ADDRESS_2);
        addressService.add(ADDRESS_3);
        addressService.add(UPDATE_ADDRESS_4);
        addressService.add(UPDATE_ADDRESS_5);
        addressService.add(UPDATE_ADDRESS_6);

        employeeService.add(EMPLOYEE_1);
        employeeService.add(EMPLOYEE_2);
        employeeService.add(EMPLOYEE_3);
    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_EMPLOYEE() {
        Util.clearAllTables();

        addressService.add(ADDRESS_1);
        addressService.add(ADDRESS_2);
        addressService.add(ADDRESS_3);
        addressService.add(UPDATE_ADDRESS_4);
        addressService.add(UPDATE_ADDRESS_5);
        addressService.add(UPDATE_ADDRESS_6);

        employeeService.add(EMPLOYEE_1);
        employeeService.add(EMPLOYEE_2);
        employeeService.add(EMPLOYEE_3);

        List<Employee> actual = employeeService.getAll();
        List<Employee> expected = new ArrayList<>();
        expected.add(EMPLOYEE_1);
        expected.add(EMPLOYEE_2);
        expected.add(EMPLOYEE_3);

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_EMPLOYEE() {
        List<Employee> expected = employeeService.getAll();

        List<Employee> actual = new ArrayList<>();
        actual.add(EMPLOYEE_1);
        actual.add(EMPLOYEE_2);
        actual.add(EMPLOYEE_3);

        assertEquals(expected, actual);
    }

    @Test
    public void getById_EMPLOYEE() {
        Employee actual1 = employeeService.getById(1);
        Employee actual2 = employeeService.getById(2);
        Employee actual3 = employeeService.getById(3);

        assertEquals(EMPLOYEE_1, actual1);
        assertEquals(EMPLOYEE_2, actual2);
        assertEquals(EMPLOYEE_3, actual3);
    }

    @Test
    public void update_EMPLOYEE() {
        Employee employee1 = new Employee(
                1, "updatefirstName 1", "updatelastName 1", new Date(5000 + 500), ADDRESS_1);
        Employee employee2 = new Employee(
                2, "updatefirstName 2", "updatelastName 3", new Date(10000 + 500), ADDRESS_2);
        Employee employee3 = new Employee(
                3, "updatefirstName 3", "updatelastName 3", new Date(20000 + 500), ADDRESS_3);

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
        employeeService.remove(EMPLOYEE_1);

        List<Employee> expected = new ArrayList<>();
        expected.add(EMPLOYEE_2);
        expected.add(EMPLOYEE_3);

        List<Employee> actual = employeeService.getAll();

        assertEquals(expected, actual);
    }
}