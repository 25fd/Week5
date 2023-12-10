package com.humber.j2ee.week5;

import com.humber.j2ee.week5.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.humber.j2ee.week5.repositoris.CarRepository;
import com.humber.j2ee.week5.services.CarService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CarServiceTest {
    CarService carService;

    @Mock
    CarRepository carRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetCarByAttributes() {
         carService = new CarService(carRepository);
         Car car = Car.builder()
                 .model(CarModel.builder().name("Model X").build())
                 .make(Make.builder().name("Tesla").build())
                 .launchYear(LaunchYear.builder()
                         .launchYear(2021).build())
                 .transmission("AUTOMATIC")
                 .vin(Vin.builder()
                         .vinNumber("7890").build())
                 .imageUrl("https://upload.wikimedia.org/wikipedia/commons/9/92/2017_Tesla_Model_X_100D_Front.jpg")
                 .build();;
        List<Car> cars = new ArrayList<>();
        cars.add(car);

         when(carRepository.findCarByAttributes("Honda", "", 0, "")).thenReturn(cars);

         List<Car> result = carService.getCarByAttributes("Honda", "", 0, "");

        assertEquals(cars, result);
    }
}
