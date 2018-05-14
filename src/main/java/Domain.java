import bl.Util;
import entity.Address;
import entity.EmplProj;
import entity.Employee;
import entity.Project;
import service.AddressService;
import service.EmplProjServise;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class Domain {

    private static AddressService addressService = new AddressService();
    private static EmployeeService employeeService = new EmployeeService();
    private static ProjectService projectService = new ProjectService();
    private static EmplProjServise emplProjServise = new EmplProjServise();

    public static void main(String[] args) {
        Util.clearAllTables();
        //test Address
        Address address1 = new Address(
                1, "Беларусь", "Гродно", "Фолюш", "230005");
        Address address2 = new Address(
                2, "Беларусь", "Гродно", "Брикеля", "230025");
        Address address3 = new Address(
                3, "Беларусь", "Гродно", "Дзержинского", "230005");

        //test Employee
        Calendar calendar = Calendar.getInstance();
        calendar.set(1983, Calendar.MARCH, 01);
        Employee employee1 = new Employee(
                1, "Евгений", "Малышко", new Date(calendar.getTime().getTime()), 3);

        calendar.set(1983, Calendar.NOVEMBER, 27);
        Employee employee2 = new Employee(
                2, "Марина", "Малышко", new Date(calendar.getTime().getTime()), 2);

        calendar.set(1983, Calendar.NOVEMBER, 27);
        Employee employee3 = new Employee(
                3, "Алина", "Малышко", new Date(calendar.getTime().getTime()), 1);

        //test Project
        Project project1 = new Project(employee2.getId(), "Статус КВО - региональный руководитель");
        Project project2 = new Project(employee1.getId(), "Разработчик ПО");

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

        System.out.println(addressService.getById(1));

        printAllData();
    }

    public static void printAllData(){
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
