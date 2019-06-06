package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Customer;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class CustomerDAOTest {

    @Autowired
    CustomerDAO customerDAO;
    SessionFactory sessionFactory;

    @Test
    void testSaveCustomer() {
        List<Customer> allCustomers = customerDAO.findAllCustomers();
        int previousLength = allCustomers.size();
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        List<Customer> afterInsert = customerDAO.findAllCustomers();
        int currentSize = afterInsert.size();
        Assertions.assertEquals(previousLength + 1, currentSize);
        customerDAO.deleteCustomer(customer.getCustomerId());
    }

    @Test
    void testGetCustomer() {
        Customer customer = customerDAO.getCustomer(1);
        Assertions.assertEquals("Aegon II", customer.getFirstName());
        Assertions.assertEquals("Targaryen", customer.getLastName());
        Assertions.assertEquals("Seven Kingdoms", customer.getCountry());

    }

    @Test
    void testFindAllCustomers() {
        List<Customer> customers = customerDAO.findAllCustomers();
        Customer firstCustomer = customerDAO.getCustomer(1);
        Assertions.assertEquals("Kings Landing", firstCustomer.getCity());
        Assertions.assertEquals(customers.size(), 3);
    }

    @Test
    void testDeleteCustomer() {

    }

    @Test
    void testUpdateCustomerFirstName() {
    }

    @Test
    void testUpdateCustomerLastName() {
    }

    @Test
    void testUpdateCustomerEmail() {
    }

    @Test
    void testUpdateCustomerPhone() {
    }

    @Test
    void testUpdateCustomerCountry() {
    }

    @Test
    void testUpdateCustomerCity() {
    }

    @Test
    void testUpdateCustomerZipcode() {
    }

    @Test
    void testUpdateCustomerAddress() {
    }

    Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Dough");
        customer.setEmail("jennie@fakemail.com");
        customer.setPhone("0123456");
        customer.setCountry("Neveland");
        customer.setCity("Village Town City");
        customer.setZipcode("11-231");
        customer.setAddress("00 Seven St.");
        return customer;
    }
}