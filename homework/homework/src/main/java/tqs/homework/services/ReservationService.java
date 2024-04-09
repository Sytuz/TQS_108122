package tqs.homework.services;

import tqs.homework.entities.Reservation;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(Reservation reservation);

    void deleteReservation(Long id);

    Reservation getReservationById(Long id);

    List<Reservation> getAllReservations();
}