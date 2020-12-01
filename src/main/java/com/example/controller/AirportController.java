package com.example.controller;

import com.example.entity.Airport;
import com.example.service.AirportService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;

@Controller("/airport")
public class AirportController {

    /**
     *
     */
    @Inject
    AirportService airportService;

    /**
     * @return
     */
    @Get("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Airport> getAll() {
        return airportService.getAllAirports();
    }

    /**
     * @param airportCode
     * @return
     */
    @Get("/find/{airportCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Airport getAirportByCode(String airportCode) {
        return airportService.getAirportByCode(airportCode);
    }

    /**
     * @param airport
     * @return
     */
    @Post("/saveAirport")
    @Produces(MediaType.APPLICATION_JSON)
    public Airport saveOrUpdate(@Body Airport airport) {
        return airportService.saveOrupdateAirport(airport);

    }

    /**
     * @param airportId
     * @return
     */
    @Delete("delete/{airportId}")
    public HttpResponse delete(Long airportId) {
        airportService.deleteAirport(airportId);
        return HttpResponse.ok();
    }

}