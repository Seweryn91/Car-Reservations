package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public Car getCar(long carId) {
        Transaction tx = null;
        Car car = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            car = session.get(Car.class,carId);
            tx.commit();
            return car;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return car;
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
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return allCars;
    }

    @SuppressWarnings("unchecked")
    public List<Car> getCarsOfCategory(String category) {
        Transaction tx = null;
        List<Car> carsOfCategory = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            carsOfCategory = (List<Car>) session.createQuery("from Car c where c.category= :category")
                    .setParameter("category", category).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return carsOfCategory;
    }


    public void deleteCar(long carId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car carToDelete = session.get(Car.class, carId);
            if (carToDelete != null) {
                session.delete(carToDelete);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Car getCarById(long carId) {
        Transaction tx = null;
        Car carToFind = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            carToFind = session.byId(Car.class).getReference(carId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return carToFind;
    }

    public void updateCar(long carId) {
        Transaction tx = null;
        Car car = getCarById(carId);
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