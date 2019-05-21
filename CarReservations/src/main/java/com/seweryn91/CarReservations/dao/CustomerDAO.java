package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDAO {

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

}
