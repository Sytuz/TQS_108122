package tqs.homework.services;

import java.util.List;
import java.util.Date;

import tqs.homework.entities.Trip;

public interface TripService {
    Trip createTrip(Trip trip);

    Trip updateTrip(Trip trip);

    void deleteTrip(Long id);

    Trip getTripById(Long id);

    List<Trip> getAllTrips();

    List<Trip> getTripsByArrNameAndDepNameAndDate(String arrName, String depName, Date date);
}
