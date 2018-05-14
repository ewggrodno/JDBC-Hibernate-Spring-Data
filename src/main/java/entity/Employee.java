package entity;

import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
public class Employee {

    private long id;
    private String firstNme;
    private String lastName;
    private Date birthday;
    private long addressId;

    public Employee() {
    }

    public Employee(long id, String firstNme, String lastName, Date birthday, long addressId) {
        this.id = id;
        this.firstNme = firstNme;
        this.lastName = lastName;
        this.birthday = birthday;
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                addressId == employee.addressId &&
                Objects.equals(firstNme, employee.firstNme) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(birthday.toString(), employee.birthday.toString());
    }

}
