package tqs.homework.services.impl;

import java.util.List;

import tqs.homework.services.ReservationService;
import tqs.homework.repositories.ReservationRepository;
import tqs.homework.entities.Reservation;

import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository tripRepository;

    public ReservationServiceImpl(ReservationRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Reservation createReservation(Reservation trip) {
        return tripRepository.save(trip);
    }

    public Reservation updateReservation(Reservation trip) {
        return tripRepository.save(trip);
    }

    public void deleteReservation(Long id) {
        tripRepository.deleteById(id);
    }

    public Reservation getReservationById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    public List<Reservation> getAllReservations() {
        return tripRepository.findAll();
    }

    public List<Reservation> getReservationsByTripId(Long tripId) {
        return tripRepository.findByTripId(tripId);
    }
}
