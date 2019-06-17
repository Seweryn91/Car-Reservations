package com.seweryn91.CarReservations.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Car")
@Table(name = "car")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long carId;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private double price;

    @Column(name = "category")
    private String category;

    @Column(name = "seats")
    private int seats;

    @Column(name = "doors")
    private int doors;

    @Column(name = "automaticGearbox")
    private boolean automaticGearbox;

    @Column(name = "automaticAC")
    private boolean automaticAC;

    @Column(name = "imagesource")
    private String imageSource;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getDoors() { return doors; }

    public void setDoors(int doors) { this.doors = doors; }

    public boolean isAutomaticGearbox() {
        return automaticGearbox;
    }

    public void setAutomaticGearbox(boolean automaticGearbox) {
        this.automaticGearbox = automaticGearbox;
    }

    public boolean isAutomaticAC() {
        return automaticAC;
    }

    public void setAutomaticAC(boolean automaticAC) {
        this.automaticAC = automaticAC;
    }

    public String getImageSource() { return imageSource;}

    public void setImageSource(String imageSource) { this.imageSource = imageSource; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Brand: ").append(getBrand()).append("\n");
        sb.append("Model: ").append(getModel()).append("\n");
        sb.append("Category: ").append(getCategory()).append("\n");
        sb.append("Price: ").append(getPrice()).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year &&
                Double.compare(car.price, price) == 0 &&
                seats == car.seats &&
                doors == car.doors &&
                automaticGearbox == car.automaticGearbox &&
                automaticAC == car.automaticAC &&
                brand.equals(car.brand) &&
                model.equals(car.model) &&
                category.equals(car.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, price, category, seats, doors, automaticGearbox, automaticAC);
    }
}