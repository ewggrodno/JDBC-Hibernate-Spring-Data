package bl;

import entity.Address;
import entity.EmplProj;
import entity.Employee;
import entity.Project;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.AddressService;
import service.EmplProjServise;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.*;

public class UtilTest {

    private static AddressService addressService = new AddressService();
    private static EmployeeService employeeService = new EmployeeService();
    private static ProjectService projectService = new ProjectService();
    private static EmplProjServise emplProjServise = new EmplProjServise();

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();
        //Address
        Address address1 = new Address(
                1, "Country 1", "City 1", "Street 1", "000001");
        Address address2 = new Address(
                2, "Country 2", "City 2", "Street 2", "000002");
        Address address3 = new Address(
                3, "Country 3", "City 3", "Street 3", "000003");

        //Employee
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900, Calendar.JANUARY, 1);
        Employee employee1 = new Employee(
                1, "firstName 1", "lastName 1", new Date(calendar.getTime().getTime()), 3);

        calendar.set(1901, Calendar.FEBRUARY, 2);
        Employee employee2 = new Employee(
                2, "firstName 2", "lastName 3", new Date(calendar.getTime().getTime()), 2);

        calendar.set(1903, Calendar.MARCH, 3);
        Employee employee3 = new Employee(
                3, "firstName 3", "lastName 3", new Date(calendar.getTime().getTime()), 1);

        //test Project
        Project project1 = new Project(employee2.getId(), "Title 1");
        Project project2 = new Project(employee1.getId(), "Title 2");

        //test EmplProj
        EmplProj emplProj1 = new EmplProj(employee1.getId(), project2.getId());
        EmplProj emplProj2 = new EmplProj(employee2.getId(), project1.getId());

        //Service
        addressService.add(address1);
        addressService.add(address2);
        addressService.add(address3);

        employeeService.add(employee1);
        employeeService.add(employee2);
        employeeService.add(employee3);

        projectService.add(project1);
        projectService.add(project2);

        emplProjServise.add(emplProj1);
        emplProjServise.add(emplProj2);
    }

    @After
    public void after() throws Exception {
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
            fail("Error: tables ADDRESS is not empty ");
        }
        if (employeeService.getAll().size() > 0) {
            fail("Error: tables EMPLOYEE is not empty ");

        }
        if (projectService.getAll().size() > 0) {
            fail("Error: tables PROJECT is not empty ");

        }
        if (emplProjServise.getAll().size() > 0) {
            fail("Error: tables EMPL_PROJ is not empty ");

        }
    }
}