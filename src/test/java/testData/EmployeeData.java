package testData;

import entity.Employee;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static testData.AddressData.*;

public class EmployeeData {

    public static final Employee EMPLOYEE_1 = new Employee(1, "firstName 1", "lastName 1", new Date(5000), ADDRESS_1);
    public static final Employee EMPLOYEE_2 = new Employee(2, "firstName 2", "lastName 2", new Date(10000), ADDRESS_2);
    public static final Employee EMPLOYEE_3 = new Employee(3, "firstName 3", "lastName 3", new Date(20000), ADDRESS_3);

    public static Set<Employee> SET_EMPLOYEE_COUNT_0;
    public static Set<Employee> SET_EMPLOYEE_COUNT_1;
    public static Set<Employee> SET_EMPLOYEE_COUNT_2;

    static {
        SET_EMPLOYEE_COUNT_0 = new HashSet<>();

        SET_EMPLOYEE_COUNT_1 = new HashSet<>();
        SET_EMPLOYEE_COUNT_1.add(EMPLOYEE_1);

        SET_EMPLOYEE_COUNT_2 = new HashSet<>();
        SET_EMPLOYEE_COUNT_2.add(EMPLOYEE_2);
        SET_EMPLOYEE_COUNT_2.add(EMPLOYEE_3);
    }

}
