package com.projectcrm.dao;

import com.projectcrm.entity.Customer;
import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers(int sortKey);
    public void saveCustomer(Customer customer);
    public Customer getCustomer(int id);

    public void deleteCustomer(int id);
}
