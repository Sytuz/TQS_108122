package tqs.homework.services.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import tqs.homework.services.TripService;
import tqs.homework.repositories.TripRepository;
import tqs.homework.entities.Trip;

import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> getTripsByArrNameAndDepNameAndDate(String arrName, String depName, Date date) {
        return tripRepository.findByArrNameAndDepNameAndDate(arrName, depName, date);
    }

    public List<String> getAllCities() {
        List<Trip> trips = tripRepository.findAll();
        List<String> cities = new ArrayList<>();
        for (Trip trip : trips) {
            if (!cities.contains(trip.getArrName())) {
                cities.add(trip.getArrName());
            }
            if (!cities.contains(trip.getDepName())) {
                cities.add(trip.getDepName());
            }
        }
        return cities;
    }
    
}
