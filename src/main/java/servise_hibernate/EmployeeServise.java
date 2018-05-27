package servise_hibernate;

import bl.HibernateSessionUtil;
import dao.EmployeeDAO;
import entity.Employee;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeServise extends HibernateSessionUtil implements EmployeeDAO {
    @Override
    public void add(Employee employee) {
        openTransactionSession();

        session.save(employee);

        closeTransactionSession();
    }

    @Override
    public List<Employee> getAll() {
        openTransactionSession();

        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> employeeList = query.list();

        closeTransactionSession();

        return employeeList;
    }

    @Override
    public Employee getById(long id) {
        closeTransactionSession();

        Query<Employee> query = session.createQuery("from Employee E where E.id = :id", Employee.class);
        query.setParameter("id", id);

        Employee employee = query.getSingleResult();

        closeTransactionSession();

        return employee;
    }

    @Override
    public void update(Employee employee) {
        closeTransactionSession();

        session.update(employee);

        closeTransactionSession();
    }

    @Override
    public void remove(Employee employee) {
        closeTransactionSession();

        session.delete(employee);

        closeTransactionSession();
    }
}
