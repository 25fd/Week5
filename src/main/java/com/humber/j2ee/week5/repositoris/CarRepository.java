package com.humber.j2ee.week5.repositoris;

import com.humber.j2ee.week5.models.Car;
import com.humber.j2ee.week5.models.LaunchYear;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car,String> {

    List<Car> findCarByLaunchYearLaunchYear(int launchYear);

    @Query("SELECT c FROM Car c WHERE " +
            "(:year <> 0 AND c.launchYear = :year) OR " +
            "(:make <> '' AND c.make = :make) OR " +
            "(:model <> '' AND c.model = :model) OR " +
            "(:transmission <> '' AND c.transmission = :transmission)")
    List<Car> findCarByAttributes(
            @Param("make") String make,
            @Param("model") String model,
            @Param("year") int year,
            @Param("transmission") String transmission
    );
}
