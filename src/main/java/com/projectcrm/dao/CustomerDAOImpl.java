package com.projectcrm.dao;

import com.projectcrm.entity.Customer;
import java.util.List;
import java.util.Objects;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Customer order by lastName", Customer.class);
        var customers = query.getResultList();
        return customers;
    }
//Objects.isNull(session.find(Customer.class, customer.getId()))
    @Override
    public void saveCustomer(Customer customer) {
        Customer temp = null;
        System.out.println("Printing tempCustomer ...." + customer.getId() + " <====");
        var session = sessionFactory.getCurrentSession();
        temp = session.find(Customer.class, customer.getId());
        System.out.println("temp customer gotten, status = " + 
                (Objects.isNull(temp) ? 00000 : 111111));
        if (temp == null) {
            System.out.println("Customer is nullll....----");
            session.persist(customer);
        } else {
            System.out.println("Customer is not");
            session.merge(customer);
        }
    }

    @Override
    public Customer getCustomer(int id) {
        var session = sessionFactory.getCurrentSession();
        var customer= session.find(Customer.class, id);
        return customer;
    }

}
