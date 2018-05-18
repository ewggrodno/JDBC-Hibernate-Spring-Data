package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private long id;
    private String firstNme;
    private String lastName;
    private Date birthday;
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(firstNme, employee.firstNme) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(birthday.toString(), employee.birthday.toString()) &&
                Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstNme, lastName, birthday.toString(), address);
    }
}
