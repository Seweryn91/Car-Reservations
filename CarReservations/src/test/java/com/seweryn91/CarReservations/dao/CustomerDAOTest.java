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
        Customer firstCustomer = customers.get(0);
        Assertions.assertEquals("Kings Landing", firstCustomer.getCity());
        for(Customer c : customers) System.out.println(c.getFirstName());
        Assertions.assertEquals(3, customers.size());
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        List<Customer> customers = customerDAO.findAllCustomers();
        int prevNOCustomers = customers.size();
        customerDAO.deleteCustomer(customer.getCustomerId());
        List<Customer> customersAfterDelete = customerDAO.findAllCustomers();
        int nextNOCustomers = customersAfterDelete.size();
        Assertions.assertEquals(nextNOCustomers, prevNOCustomers-1);
    }

    @Test
    void testUpdateCustomerFirstName() {
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        long customerId = customer.getCustomerId();
        String name = "Jennie";
        customerDAO.updateCustomerFirstName(customerId, name);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(name, customerAfterUpdate.getFirstName());
        customerDAO.deleteCustomer(customerId);
    }


    @Test
    void testUpdateCustomerLastName() {
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        long customerId = customer.getCustomerId();
        String surname = "Assert";
        customerDAO.updateCustomerLastName(customerId, surname);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(surname, customerAfterUpdate.getLastName());
        customerDAO.deleteCustomer(customerId);
    }

    @Test
    void testUpdateCustomerEmail() {
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        long customerId = customer.getCustomerId();
        String email = "testm4il@jun.it";
        customerDAO.updateCustomerEmail(customerId, email);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(email, customerAfterUpdate.getEmail());
        customerDAO.deleteCustomer(customerId);
    }

    @Test
    void testUpdateCustomerPhone() {
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        long customerId = customer.getCustomerId();
        String phone = "221122331100";
        customerDAO.updateCustomerPhone(customerId, phone);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(phone, customerAfterUpdate.getPhone());
        customerDAO.deleteCustomer(customerId);
    }

    @Test
    void testUpdateCustomerCountry() {
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        long customerId = customer.getCustomerId();
        String country = "Krakhozia";
        customerDAO.updateCustomerCountry(customerId, country);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(country, customerAfterUpdate.getCountry());
        customerDAO.deleteCustomer(customerId);
    }

    @Test
    void testUpdateCustomerCity() {
        Customer customer = createCustomer();
        customerDAO.saveCustomer(customer);
        long customerId = customer.getCustomerId();
        String city = "Waldenburg";
        customerDAO.updateCustomerCity(customerId, city);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(city, customerAfterUpdate.getCity());
        customerDAO.deleteCustomer(customerId);
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