package controller.customer;

import model.Customer;

import java.util.List;

public interface CustomerService {

    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
    boolean updateCustomer(Customer customer);
    Customer searchCustomer(String id);
    List<Customer> getAll();

}
