package service;

import jdbc.bl.Util;
import entity.EmplProj;
import jdbc.service.AddressService;
import jdbc.service.EmplProjServise;
import jdbc.service.EmployeeService;
import jdbc.service.ProjectService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static testData.AddressData.*;
import static testData.EmplProjData.*;
import static testData.EmployeeData.*;
import static testData.ProjectData.*;

public class EmplProjServiseTest {

    private AddressService addressService = new AddressService();
    private EmployeeService employeeService = new EmployeeService();
    private ProjectService projectService = new ProjectService();
    private EmplProjServise emplProjServise = new EmplProjServise();

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        addressService.add(ADDRESS_1);
        addressService.add(ADDRESS_2);
        addressService.add(ADDRESS_3);

        employeeService.add(EMPLOYEE_1);
        employeeService.add(EMPLOYEE_2);
        employeeService.add(EMPLOYEE_3);

        projectService.add(PROJECT_1);
        projectService.add(PROJECT_2);
        projectService.add(PROJECT_3);

    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_EMPL_PROJ() {

        List<EmplProj> expected = new ArrayList<>();
        expected.add(EMPL_PROJ_1);
        expected.add(EMPL_PROJ_2);
        expected.add(EMPL_PROJ_4);
        expected.add(EMPL_PROJ_3);

        List<EmplProj> actual = emplProjServise.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void add_EMPL_PROJ_TO_PROJECT() {
        Util.clearAllTables();

        addressService.add(ADDRESS_2);
        addressService.add(ADDRESS_3);

        employeeService.add(EMPLOYEE_2);
        employeeService.add(EMPLOYEE_3);

        projectService.add(PROJECT_3);

        List<EmplProj> expected = new ArrayList<>();
        expected.add(EMPL_PROJ_4);
        expected.add(EMPL_PROJ_3);

        List<EmplProj> actual = emplProjServise.getAll(PROJECT_3);

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_EMPL_PROJ() {
        List<EmplProj> expected = new ArrayList<>();
        expected.add(EMPL_PROJ_1);
        expected.add(EMPL_PROJ_2);
        expected.add(EMPL_PROJ_4);
        expected.add(EMPL_PROJ_3);

        List<EmplProj> actual = emplProjServise.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_EMPL_PROJ_TO_EMPLOYEE() {
        List<EmplProj> expected = new ArrayList<>();
        expected.add(EMPL_PROJ_1);
        expected.add(EMPL_PROJ_2);

        List<EmplProj> actual = emplProjServise.getAll(EMPLOYEE_1);

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_EMPL_PROJ_TO_PROJECT() {
        List<EmplProj> expected = new ArrayList<>();
        expected.add(EMPL_PROJ_4);
        expected.add(EMPL_PROJ_3);

        List<EmplProj> actual = emplProjServise.getAll(PROJECT_3);

        assertEquals(expected, actual);
    }

    @Test
    public void getByEmployeeIdAndProjectId_EMPL_PROJ() {
        EmplProj actual1 = emplProjServise.getByEmployeeIdAndProjectId(1, 1);
        EmplProj actual2 = emplProjServise.getByEmployeeIdAndProjectId(1, 2);
        EmplProj actual3 = emplProjServise.getByEmployeeIdAndProjectId(2, 3);
        EmplProj actual4 = emplProjServise.getByEmployeeIdAndProjectId(3, 3);

        assertEquals(EMPL_PROJ_1, actual1);
        assertEquals(EMPL_PROJ_2, actual2);
        assertEquals(EMPL_PROJ_3, actual3);
        assertEquals(EMPL_PROJ_4, actual4);
    }

    @Test
    public void update_EMPL_PROJ_ChangeEmployee() {
        EmplProj newEmplProj1 = new EmplProj(2, 1);

        emplProjServise.update(EMPL_PROJ_1, newEmplProj1);

        EmplProj actual1 = emplProjServise.getByEmployeeIdAndProjectId(2, 1);

        assertEquals(newEmplProj1, actual1);
    }

    @Test
    public void update_EMPL_PROJ_ChangeProject() {
        EmplProj newEmplProj1 = new EmplProj(1, 2);

        emplProjServise.update(EMPL_PROJ_1, newEmplProj1);

        EmplProj actual1 = emplProjServise.getByEmployeeIdAndProjectId(1, 2);

        assertEquals(newEmplProj1, actual1);
    }

    @Test
    public void update_EMPL_PROJ_ChangeEmployeeAndProject() {
        EmplProj newEmplProj1 = new EmplProj(2, 2);

        emplProjServise.update(EMPL_PROJ_1, newEmplProj1);

        EmplProj actual1 = emplProjServise.getByEmployeeIdAndProjectId(2, 2);

        assertEquals(newEmplProj1, actual1);
    }

    @Test
    public void remove_EMPL_PROJ() {
        emplProjServise.remove(EMPL_PROJ_1);

        List<EmplProj> expected = new ArrayList<>();
        expected.add(EMPL_PROJ_2);
        expected.add(EMPL_PROJ_4);
        expected.add(EMPL_PROJ_3);

        List<EmplProj> actual = emplProjServise.getAll();

        assertEquals(expected, actual);
    }
}