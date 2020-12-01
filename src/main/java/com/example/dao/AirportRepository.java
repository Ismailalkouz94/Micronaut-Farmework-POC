package com.example.dao;

import com.example.entity.Airport;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
@Repository
public interface AirportRepository extends CrudRepository<Airport, Long>{
 
 @Executable
 Airport findByAirportCode(String airportCode);
 
}