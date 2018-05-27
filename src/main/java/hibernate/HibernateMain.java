package hibernate;

import hibernate.bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class HibernateMain {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address();
        address.setCountry("Country 1");
        address.setCity("City 1");
        address.setStreet("Street 1");
        address.setPostCode("00001");

        Employee employee_1 = new Employee();
        employee_1.setFirstName("FirsName 1");
        employee_1.setLastName("LastName 1");
        employee_1.setBirthday(new Date(10_000));
        employee_1.setAddress(address);

        Employee employee_2 = new Employee();
        employee_2.setFirstName("FirsName 2");
        employee_2.setLastName("LastName 2");
        employee_2.setBirthday(new Date(20_000));
        employee_2.setAddress(address);

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee_1);
        employeeSet.add(employee_2);

        Project project = new Project();
        project.setId(1);
        project.setTitle("Title 1");
        project.setEmployees(employeeSet);

        session.save(address);
        session.save(employee_1);
        session.save(employee_2);
        session.save(project);

        session.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
