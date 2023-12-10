package com.humber.j2ee.week5.services;

import com.humber.j2ee.week5.models.*;
import com.humber.j2ee.week5.repositoris.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;


@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository =carRepository;
    }
    public List<Car> getAllCars() {
        Car car1 = Car.builder()
                .vin(Vin.builder()
                        .vinNumber("1234").build())
                .model(CarModel.builder().name("Rubicon").build())
                .make(Make.builder().name("Jeep").build())
                .launchYear(LaunchYear.builder()
                        .launchYear(2021).build())
                .transmission("AUTOMATIC")
                .imageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Jeep_Wrangler_Rubicon_4xe.jpg/220px-Jeep_Wrangler_Rubicon_4xe.jpg")
                .build();

        Car car2 = Car.builder()
                .model(CarModel.builder().name("Civic").build())
                .make(Make.builder().name("Honda").build())
                .launchYear(LaunchYear.builder()
                        .launchYear(2021).build())
                .transmission("AUTOMATIC")
                .vin(Vin.builder()
                        .vinNumber("2345").build())
                .imageUrl("https://upload.wikimedia.org/wikipedia/commons/f/f6/Honda_Civic_1.0_VTEC_Turbo_Executive_Sport-Line_%28X%29_%E2%80%93_f_25072020.jpg")
                .build();

        Car car3 = Car.builder()
                .model(CarModel.builder().name("Elentra").build())
                .make(Make.builder().name("Hyundai").build())
                .launchYear(LaunchYear.builder()
                        .launchYear(2021).build())
                .transmission("AUTOMATIC")
                .vin(Vin.builder()
                        .vinNumber("4567").build())
                .imageUrl("https://upload.wikimedia.org/wikipedia/commons/a/ac/0_Hyundai_Avante_%28CN7%29_FL_2.jpg")
                .build();

        Car car4 = Car.builder()
                .model(CarModel.builder().name("Model X").build())
                .make(Make.builder().name("Tesla").build())
                .launchYear(LaunchYear.builder()
                        .launchYear(2021).build())
                .transmission("AUTOMATIC")
                .vin(Vin.builder()
                        .vinNumber("7890").build())
                .imageUrl("https://upload.wikimedia.org/wikipedia/commons/9/92/2017_Tesla_Model_X_100D_Front.jpg")
                .build();

        return Arrays.asList(car1, car2, car3, car4);
    }

    public List<Car> getCars(){
        return (List<Car>) this.carRepository.findAll();
    }

    public void addCars(){
        this.carRepository.saveAll(this.getAllCars());
    }

    public List<Car> getCarByYear(int year) {
        return this.carRepository.findCarByLaunchYearLaunchYear(year);
    }

    public List<Car> getCarByAttributes(String make, String model, int year, String transmission) {
        System.out.println(make);
        System.out.println(model);
        System.out.println(year);
        System.out.println(transmission);
        return this.carRepository.findCarByAttributes(make, model, year, transmission);
    }

    public void deleteCarById(String id) {
        carRepository.deleteById(id);
    }
    public void addCar(Car car){
        this.carRepository.save(car);

    }
}