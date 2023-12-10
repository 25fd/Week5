package com.humber.j2ee.week5.repositoris;

import com.humber.j2ee.week5.models.LaunchYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchYearRepository extends CrudRepository<LaunchYear, Integer> {
}
