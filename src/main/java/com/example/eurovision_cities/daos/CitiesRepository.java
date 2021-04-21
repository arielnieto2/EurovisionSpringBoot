package com.example.eurovision_cities.daos;

import com.example.eurovision_cities.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CitiesRepository extends JpaRepository<City,Long> {
}
