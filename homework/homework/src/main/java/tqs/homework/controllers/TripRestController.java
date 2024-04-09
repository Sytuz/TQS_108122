package tqs.homework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import tqs.homework.entities.Trip;
import tqs.homework.services.TripService;

import java.util.List;
import java.text.SimpleDateFormat;

import java.text.ParseException;

@RestController
@RequestMapping("/api/trip")
public class TripRestController {
    
    private final TripService tripService;

    public TripRestController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    @Operation(summary = "Create a new trip")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        return new ResponseEntity<>(tripService.createTrip(trip), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing trip")
    public ResponseEntity<Trip> updateTrip(@Parameter(description = "Trip ID") @PathVariable("id") Long id, @RequestBody Trip trip) {
        trip.setId(id);
        return new ResponseEntity<>(tripService.updateTrip(trip), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing trip")
    public ResponseEntity<Trip> deleteTrip(@Parameter(description = "Trip ID") @PathVariable("id") Long id) {
        tripService.deleteTrip(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an existing trip by id")
    public ResponseEntity<Trip> getTripById(@Parameter(description = "Trip ID") @PathVariable("id") Long id) {
        Trip trip = tripService.getTripById(id);
        if (trip != null) {
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Get trips by arrival and departure names, and departure date")
    public ResponseEntity<List<Trip>> getTripsByArrNameAndDepNameAndDate(@Parameter(description = "Arrival Name") @RequestParam("arrName") String arrName, @Parameter(description = "Departure Name") @RequestParam("depName") String depName, @Parameter(description = "Date") @RequestParam("date") String date) throws ParseException{
        List<Trip> trips = tripService.getTripsByArrNameAndDepNameAndDate(arrName, depName, new SimpleDateFormat("yyyy-mm-dd").parse(date));
        if (trips.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(trips, HttpStatus.OK);
        }
    }

    @GetMapping("/cities")
    @Operation(summary = "Get all cities")
    public ResponseEntity<List<String>> getAllCities() {
        List<String> cities = tripService.getAllCities();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }
    }

    @GetMapping
    @Operation(summary = "Get all Trips")
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        if (trips.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(trips, HttpStatus.OK);
        }
    }
}
