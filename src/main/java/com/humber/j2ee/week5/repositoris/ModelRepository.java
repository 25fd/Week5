package com.humber.j2ee.week5.repositoris;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository<com.humber.j2ee.week5.models.CarModel, Integer> {
}
