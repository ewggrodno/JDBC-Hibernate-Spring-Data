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

public class Domain {

    public static void main(String[] args) {
        Address address1 = new Address(
                1L, "Беларусь", "Гродно", "Фолюш", "230005");
        Address address2 = new Address(
                2L, "Беларусь", "Гродно", "Брикеля", "230025");
        Address address3 = new Address(
                3L, "Беларусь", "Гродно", "Дзержинского", "230005");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1983, Calendar.MARCH, 01);
        Employee employee1 = new Employee(
                1L, "Евгений", "Малышко", new Date(calendar.getTime().getTime()), 3L);

        calendar.set(1983, Calendar.NOVEMBER, 27);
        Employee employee2 = new Employee(
                2L, "Марина", "Малышко", new Date(calendar.getTime().getTime()), 2L);

        calendar.set(1983, Calendar.NOVEMBER, 27);
        Employee employee3 = new Employee(
                3L, "Алина", "Малышко", new Date(calendar.getTime().getTime()), 1L);

        Project project1 = new Project(employee2.getId(), "Статус КВО - руководитель подразделения");
        Project project2 = new Project(employee1.getId(), "Разработчик ПО");

        EmplProj emplProj1 = new EmplProj(employee1.getId(), project2.getId());
        EmplProj emplProj2 = new EmplProj(employee2.getId(), project1.getId());

        //Service
        System.out.println("Дабавляем: Address");
        AddressService addressService = new AddressService();
        addressService.add(address1);
        addressService.add(address2);
        addressService.add(address3);

        System.out.println("Дабавляем: Employee");
        EmployeeService employeeService = new EmployeeService();
        employeeService.add(employee1);
        employeeService.add(employee2);
        employeeService.add(employee3);

        System.out.println("Дабавляем: Project");
        ProjectService projectService = new ProjectService();
        projectService.add(project1);
        projectService.add(project2);
//
        System.out.println("Дабавляем: EmplProj");
        EmplProjServise emplProjServise = new EmplProjServise();
        emplProjServise.add(emplProj1);
        emplProjServise.add(emplProj2);

//        Util.deleteAllTables();
    }
}
