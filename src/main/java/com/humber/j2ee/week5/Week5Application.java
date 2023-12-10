package com.humber.j2ee.week5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.humber.j2ee.week5.services.CarService;


@SpringBootApplication
public class Week5Application implements CommandLineRunner {

    @Autowired
    private CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(Week5Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       carService.addCars();
    }

}
