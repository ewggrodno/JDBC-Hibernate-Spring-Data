package testData;

import entity.Employee;

import java.sql.Date;

public class EmployeeData {

    public static final Employee EMPLOYEE_1 = new Employee(1, "firstName 1", "lastName 1", new Date(5000), 3);
    public static final Employee EMPLOYEE_2 = new Employee(2, "firstName 2", "lastName 3", new Date(10000), 2);
    public static final Employee EMPLOYEE_3 = new Employee(3, "firstName 3", "lastName 3", new Date(20000), 1);

}
