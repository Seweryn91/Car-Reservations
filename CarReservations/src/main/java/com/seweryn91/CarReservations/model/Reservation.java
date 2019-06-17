package com.seweryn91.CarReservations.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Reservation")
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long reservationId;

    @Column(name = "car_id")
    private long carId;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "reservation_start")
    private Date startDate;

    @Column(name = "reservation_end")
    private Date endDate;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return carId == that.carId &&
                customerId == that.customerId &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, customerId, startDate, endDate);
    }
}