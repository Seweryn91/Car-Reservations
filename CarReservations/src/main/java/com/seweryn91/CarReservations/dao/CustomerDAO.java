package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveCustomer(Customer customer) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Customer getCustomer(long customerId) {
        Transaction tx = null;
        Customer customer = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            customer = session.get(Customer.class,customerId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return customer;
    }

    @SuppressWarnings("unchecked")
    public List<Customer> findAllCustomers() {
        Transaction tx = null;
        List<Customer> allCustomers = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            allCustomers = (List<Customer>) session.createQuery( "from Customer customer").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return allCustomers;
    }

    public void deleteCustomer(long customerId) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Customer customerInDB = session.load(Customer.class, customerId);
            if (customerInDB != null) {
            session.delete(Customer.class);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCustomerFirstName(long customerId, String fname) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Customer customer = session.load(Customer.class, customerId);
            customer.setFirstName(fname);
            session.update(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

    }

    public void updateCustomerLastName(long customerId, String lname) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Customer customer = session.load(Customer.class, customerId);
            customer.setLastName(lname);
            session.update(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCustomerEmail(long customerId, String email) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Customer customer = session.load(Customer.class, customerId);
            customer.setEmail(email);
            session.update(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCustomerPhone(long customerId, String phone) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Customer customer = session.load(Customer.class, customerId);
            customer.setPhone(phone);
            session.update(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCustomerCountry() {
        //TODO: Implement method
    }

    public void updateCustomerCity() {
        //TODO: Implement method
    }

    public void updateCustomerZipcode() {
        //TODO: Implement method
    }

    public void updateCustomerAddress() {
        //TODO: Implement method
    }

}
