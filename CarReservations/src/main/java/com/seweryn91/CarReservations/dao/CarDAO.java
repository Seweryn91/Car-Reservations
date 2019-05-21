package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.dao.interfaces.CarDAOInterface;
import com.seweryn91.CarReservations.database.HibernateUtil;
import com.seweryn91.CarReservations.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CarDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public void getCar(long carId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = session.get(Car.class,carId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void saveCar(Car car) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Car> findAllCars() {
        Transaction tx = null;
        List<Car> allCars = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            allCars = (List<Car>) session.createQuery( "from Car car").list();

            tx.commit();
            if (allCars.isEmpty()) System.out.println("empty");
            else {
                for (Car car : allCars) {
                    System.out.println(car.getBrand() + car.getModel());
                }}
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return allCars;
    }

    public void deleteCar(long carId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car carToDelete = session.get(Car.class, carId);
            if (carToDelete != null) {
            session.delete(carToDelete);}
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void getCarById(long carId) {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tx = session.beginTransaction();
            Car carToFind = session.byId(Car.class).getReference(carId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCar(Car car) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }


}