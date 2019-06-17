package com.seweryn91.CarReservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
@PropertySource("classpath:application.properties")
public class CarReservationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarReservationsApplication.class, args);
    }

}
