package servise_hibernate;

import bl.HibernateSessionUtil;
import dao.AddressDAO;
import entity.Address;
import org.hibernate.query.Query;

import java.util.List;

public class AddressService extends HibernateSessionUtil implements AddressDAO {

    @Override
    public void add(Address address) {
        openTransactionSession();

        session.save(address);

        closeTransactionSession();
    }

    @Override
    public List<Address> getAll() {
        openTransactionSession();

        Query<Address> query = session.createQuery("from Address", Address.class);
        List<Address> addressList = query.list();

        closeTransactionSession();

        return addressList;
    }

    @Override
    public Address getById(long id) {
        openTransactionSession();

        Query<Address> query = session.createQuery("from Address A where A.id = :id", Address.class);
        query.setParameter("id", id);

        Address address = query.getSingleResult();

        closeTransactionSession();

        return address;
    }

    @Override
    public void update(Address address) {
        openTransactionSession();

        session.refresh(address);

        closeTransactionSession();
    }

    @Override
    public void remove(Address address) {
        openTransactionSession();

        session.delete(address);

        closeTransactionSession();
    }
}
