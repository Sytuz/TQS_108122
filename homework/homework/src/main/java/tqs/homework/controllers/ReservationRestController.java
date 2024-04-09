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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import tqs.homework.entities.Reservation;
import tqs.homework.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {
    
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @Operation(summary = "Create a new reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.createReservation(reservation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing reservation")
    public ResponseEntity<Reservation> updateReservation(@Parameter(description = "Reservation ID") @PathVariable("id") Long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return new ResponseEntity<>(reservationService.updateReservation(reservation), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing reservation")
    public ResponseEntity<Reservation> deleteReservation(@Parameter(description = "Reservation ID") @PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an existing reservation by id")
    public ResponseEntity<Reservation> getReservationById(@Parameter(description = "Reservation ID") @PathVariable("id") Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "Get all Reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
    }

    @GetMapping("/trip/{tripId}")
    @Operation(summary = "Get all Reservations by Trip ID")
    public ResponseEntity<List<Reservation>> getReservationsByTripId(@Parameter(description = "Trip ID") @PathVariable("tripId") Long tripId) {
        List<Reservation> reservations = reservationService.getReservationsByTripId(tripId);
        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
    }
}
