package com.example.service;

import com.example.dao.AirportRepository;
import com.example.entity.Airport;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AirportService {
    @Inject
    AirportRepository airportRepository;

    /**
     * @return
     */
    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    /**
     *
     */
    public Airport getAirportByCode(String airportCode) {
        return airportRepository.findByAirportCode(airportCode);
    }

    /**
     * @param airport
     * @return
     */
    public Airport saveOrupdateAirport(Airport airport) {
        Airport airportSaved = null;
        if (airport.getId() != null && airportRepository.existsById(airport.getId())) {
            airportSaved = airportRepository.update(airport);
        } else {
            airportSaved = airportRepository.save(airport);
        }
        return airportSaved;
    }

    /**
     * @param airportId
     */
    public void deleteAirport(Long airportId) {
        airportRepository.deleteById(airportId);
    }
}