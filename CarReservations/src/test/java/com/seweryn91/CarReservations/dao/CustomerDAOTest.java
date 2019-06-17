package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Customer;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerDAOTest {

    @Autowired
    private CustomerDAO customerDAO;
    private SessionFactory sessionFactory;
    private long testCustomerId;

    @BeforeAll
    void before() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Dough");
        customer.setEmail("jennie@fakemail.com");
        customer.setPhone("0123456");
        customer.setCountry("Neveland");
        customer.setCity("Village Town City");
        customer.setZipcode("11-231");
        customer.setAddress("00 Seven St.");
        customerDAO.saveCustomer(customer);
        testCustomerId = customer.getCustomerId();
    }

    @AfterAll
    void after() {
        customerDAO.deleteCustomer(testCustomerId);
    }

    @Test
    @DisplayName("Test save customer in DB")
    void testSaveCustomer() {
        List<Customer> allCustomers = customerDAO.findAllCustomers();
        int previousLength = allCustomers.size();
        Customer customer = new Customer();
        customer.setFirstName("FakeOne");
        customer.setLastName("DummyTwo");
        customer.setPhone("01010101");
        customer.setAddress("2 Test Street");
        customer.setEmail("email@test.ema.il");
        customer.setZipcode("0000");
        customer.setCity("Test");
        customer.setCountry("Test");
        customerDAO.saveCustomer(customer);
        List<Customer> afterInsert = customerDAO.findAllCustomers();
        int currentSize = afterInsert.size();
        Assertions.assertEquals(previousLength + 1, currentSize);
        customerDAO.deleteCustomer(customer.getCustomerId());
    }

    @Test
    @DisplayName("Test get customer form DB")
    void testGetCustomer() {
        Customer customer = customerDAO.getCustomer(1);
        Customer toTest = new Customer();
        toTest.setFirstName("Aegon II");
        toTest.setLastName("Targaryen");
        toTest.setPhone("33443344");
        toTest.setEmail("jonsnuuh@iknownothi.ng");
        toTest.setCountry("Seven Kingdoms");
        toTest.setCity("Kings Landing");
        toTest.setZipcode("00-001");
        toTest.setAddress("Red Keep 1/1");
        Assertions.assertEquals(toTest, customer);
    }

    @Test
    @DisplayName("Test get all customers")
    void testFindAllCustomers() {
        List<Customer> customers = customerDAO.findAllCustomers();
        Assertions.assertEquals(6, customers.size());
    }

    @Test
    @DisplayName("Test delete customer")
    void testDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Fake");
        customer.setLastName("Dummy");
        customer.setPhone("034373");
        customer.setAddress("1 Test Street");
        customer.setEmail("email@testema.il");
        customer.setZipcode("0000");
        customer.setCity("Test");
        customer.setCountry("Test");
        customerDAO.saveCustomer(customer);
        List<Customer> customers = customerDAO.findAllCustomers();
        int prevNOCustomers = customers.size();
        customerDAO.deleteCustomer(customer.getCustomerId());
        List<Customer> customersAfterDelete = customerDAO.findAllCustomers();
        int nextNOCustomers = customersAfterDelete.size();
        Assertions.assertEquals(nextNOCustomers, prevNOCustomers-1);
    }

    @Test
    @DisplayName("Test update firstname")
    void testUpdateCustomerFirstName() {
        long customerId = testCustomerId;
        String name = "Jennie";
        customerDAO.updateCustomerFirstName(customerId, name);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(name, customerAfterUpdate.getFirstName());
    }


    @Test
    @DisplayName("Test update surname")
    void testUpdateCustomerLastName() {
        long customerId = testCustomerId;
        String surname = "Assert";
        customerDAO.updateCustomerLastName(customerId, surname);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(surname, customerAfterUpdate.getLastName());
    }

    @Test
    @DisplayName("Test update email")
    void testUpdateCustomerEmail() {
        long customerId = testCustomerId;
        String email = "testm4il@jun.it";
        customerDAO.updateCustomerEmail(customerId, email);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(email, customerAfterUpdate.getEmail());
    }

    @Test
    @DisplayName("Test update phone")
    void testUpdateCustomerPhone() {
        long customerId = testCustomerId;
        String phone = "221122331100";
        customerDAO.updateCustomerPhone(customerId, phone);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(phone, customerAfterUpdate.getPhone());
    }

    @Test
    @DisplayName("Test update country")
    void testUpdateCustomerCountry() {
        long customerId = testCustomerId;
        String country = "Krakhozia";
        customerDAO.updateCustomerCountry(customerId, country);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(country, customerAfterUpdate.getCountry());
    }

    @Test
    @DisplayName("Test update city")
    void testUpdateCustomerCity() {
        long customerId = testCustomerId;
        String city = "Waldenburg";
        customerDAO.updateCustomerCity(customerId, city);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(city, customerAfterUpdate.getCity());
    }

    @Test
    @DisplayName("Test update zipcode")
    void testUpdateCustomerZipcode() {
        long customerId = testCustomerId;
        String zipcode = "P05TC0D3";
        customerDAO.updateCustomerZipcode(customerId, zipcode);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(zipcode, customerAfterUpdate.getZipcode());
    }

    @Test
    @DisplayName("Test update address")
    void testUpdateCustomerAddress() {
        long customerId = testCustomerId;
        String address = "007 Bond St.";
        customerDAO.updateCustomerAddress(customerId, address);
        Customer customerAfterUpdate = customerDAO.getCustomer(customerId);
        Assertions.assertEquals(address, customerAfterUpdate.getAddress());
    }


}