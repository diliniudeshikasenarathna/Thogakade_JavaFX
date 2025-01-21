package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService{
    @Override
    public boolean addCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String customerId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from customerdetails where customerId=" + "'" + customerId + "'");
            resultSet.next();
            Customer customer = new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
           return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  List<Customer> getAll() {

        List<Customer> customerArrayList=new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customerdetails;");


            while(resultSet.next()){
                Customer customer=  new Customer( resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
                customerArrayList.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerArrayList;
    }

    public ObservableList<String> getCustomerId(){
        List<Customer> all = getAll();
        ObservableList<String> customerIdList = FXCollections.observableArrayList();

        all.forEach(customer -> {
            customerIdList.add(customer.getId());
        });


        return customerIdList;
    }
}
