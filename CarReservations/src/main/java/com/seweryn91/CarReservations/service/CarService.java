package com.seweryn91.CarReservations.service;

import com.seweryn91.CarReservations.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Car> getAllCarsAvailableToday() {
        Transaction tx = null;
        List<Car> carsAvailableToday = null;
        String query = "select c from Car c left join Reservation r on c.carId = r.carId where (current_date not between" +
                " r.startDate and r.endDate) or (r.startDate is null) or (r.endDate is null) order by c.carId";
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            carsAvailableToday = (List<Car>) session.createQuery(query).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return carsAvailableToday;
    }
}
