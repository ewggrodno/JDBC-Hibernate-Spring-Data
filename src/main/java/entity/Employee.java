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
    private long addressId;

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
