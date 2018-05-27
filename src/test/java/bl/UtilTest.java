package bl;

import jdbc.bl.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import jdbc.service.AddressService;
import jdbc.service.EmplProjServise;
import jdbc.service.EmployeeService;
import jdbc.service.ProjectService;

import static org.junit.Assert.*;
import static testData.AddressData.*;
import static testData.EmplProjData.*;
import static testData.EmployeeData.*;
import static testData.ProjectData.*;

public class UtilTest {

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

        emplProjServise.add(EMPL_PROJ_1);
        emplProjServise.add(EMPL_PROJ_2);
        emplProjServise.add(EMPL_PROJ_3);
    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void getConnection() {
        assertNotNull(Util.getConnection());
    }

    @Test
    public void clearAllTables() {
        Util.clearAllTables();

        if (addressService.getAll().size() > 0) {
            fail("Error: tables ADDRESS is not empty.");
        }
        if (employeeService.getAll().size() > 0) {
            fail("Error: tables EMPLOYEE is not empty.");

        }
        if (projectService.getAll().size() > 0) {
            fail("Error: tables PROJECT is not empty.");

        }
        if (emplProjServise.getAll().size() > 0) {
            fail("Error: tables EMPL_PROJ is not empty.");

        }
    }
}