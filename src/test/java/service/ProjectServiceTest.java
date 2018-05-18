package service;

import bl.Util;
import entity.Employee;
import entity.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testData.AddressData;
import testData.EmplProjData;
import testData.EmployeeData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static testData.AddressData.*;
import static testData.EmployeeData.*;
import static testData.ProjectData.*;

public class ProjectServiceTest {

    private AddressService addressService = new AddressService();
    private EmployeeService employeeService = new EmployeeService();
    private EmplProjServise emplProjServise = new EmplProjServise();
    private ProjectService projectService = new ProjectService();

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        addressService.add(ADDRESS_1);
        addressService.add(ADDRESS_2);
        addressService.add(ADDRESS_3);

        employeeService.add(EmployeeData.EMPLOYEE_1);
        employeeService.add(EmployeeData.EMPLOYEE_2);
        employeeService.add(EmployeeData.EMPLOYEE_3);

        projectService.add(PROJECT_1);
        projectService.add(PROJECT_2);
        projectService.add(PROJECT_3);

    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_PROJECT() {

        List<Project> expected = new ArrayList<>();
        expected.add(PROJECT_1);
        expected.add(PROJECT_2);
        expected.add(PROJECT_3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_PROJECT() {
        List<Project> expected = new ArrayList<>();
        expected.add(PROJECT_1);
        expected.add(PROJECT_2);
        expected.add(PROJECT_3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getById_PROJECT() {
        Project actual1 = projectService.getById(1);
        Project actual2 = projectService.getById(2);
        Project actual3 = projectService.getById(3);

        assertEquals(PROJECT_1, actual1);
        assertEquals(PROJECT_2, actual2);
        assertEquals(PROJECT_3, actual3);
    }

    @Test
    public void update_PROJECT() {
        Project project1 = new Project(1, "updateTitle 1", SET_EMPLOYEE_COUNT_1);
        Project project2 = new Project(2, "updateTitle 2", SET_EMPLOYEE_COUNT_1);
        Project project3 = new Project(3, "updateTitle 3", SET_EMPLOYEE_COUNT_2);

        projectService.update(project1);
        projectService.update(project2);
        projectService.update(project3);

        Project actual1 = projectService.getById(1);
        Project actual2 = projectService.getById(2);
        Project actual3 = projectService.getById(3);

        assertEquals(project1, actual1);
        assertEquals(project2, actual2);
        assertEquals(project3, actual3);
    }

    @Test
    public void remove_PROJECT() {
        projectService.remove(PROJECT_1);

        List<Project> expected = new ArrayList<>();
        expected.add(PROJECT_2);
        expected.add(PROJECT_3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }
}