import entity.Address;
import entity.EmplProj;
import entity.Employee;
import entity.Project;
import service.AddressService;
import service.EmplProjServise;
import service.EmployeeService;
import service.ProjectService;

import java.util.List;

public class TestH2myTest {

    public static AddressService addressService = new AddressService();
    public static EmployeeService employeeService = new EmployeeService();
    public static ProjectService projectService = new ProjectService();
    public static EmplProjServise emplProjServise = new EmplProjServise();

    public static void main(String[] args) {
//        Address address = addressService.getById(1L);
//        System.out.println(address);
//
//        Employee employee = employeeService.getById(1L);
//        System.out.println(employee);
//
//        Project project = projectService.getById(1L);
//        System.out.println(project);
//
//        EmplProj emplProj = emplProjServise.getByEmployeeIdAndProjectId(1L, 1L);
//        System.out.println(emplProj);
//
//        emplProjServise.remove(emplProj);
//        employeeService.remove(employee);
//        printAllH2();
    }

    public static void printAllH2(){
        List<Address> addressList = addressService.getAll();
        addressList.forEach(System.out::println);
        System.out.println("=============================");

        List<Employee> employeeList = employeeService.getAll();
        employeeList.forEach(System.out::println);
        System.out.println("=============================");

        List<Project> projectList = projectService.getAll();
        projectList.forEach(System.out::println);
        System.out.println("=============================");

        List<EmplProj> emplProjList = emplProjServise.getAll();
        emplProjList.forEach(System.out::println);
        System.out.println("=============================");
    }
}
