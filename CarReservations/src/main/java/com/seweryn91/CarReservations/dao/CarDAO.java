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
            carToFind = session.byId(Car.class).load(carId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return carToFind;
    }

    public void updateCarPrice(long carId, double newPrice) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setPrice(newPrice);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarBrand(long carId, String brand) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setBrand(brand);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarModel(long carId, String model) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setModel(model);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarSeats(long carId, int seats) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setSeats(seats);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarYear(long carId, int year) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setYear(year);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarCategory(long carId, String category) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setCategory(category);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarDoors(long carId, int doors) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setDoors(doors);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarGearbox(long carId, boolean isAutomatic) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setAutomaticGearbox(isAutomatic);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateCarAutomaticAC(long carId, boolean isAutomaticAC) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = getCarById(carId);
            car.setAutomaticAC(isAutomaticAC);
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public List<Car> getAllCarsAvailableToday() {
        Transaction tx = null;
        List<Car> carsAvailableToday = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            carsAvailableToday = (List<Car>) session.createQuery(" select c from Car c left join Reservation r on c.carId = r.carId where (current_date not between r.startDate and r.endDate) or (r.startDate is null) or (r.endDate is null) order by c.carId").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return carsAvailableToday;
    }
}