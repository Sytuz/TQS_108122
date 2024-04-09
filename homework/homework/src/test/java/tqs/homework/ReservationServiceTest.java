package tqs.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.homework.entities.Reservation;
import tqs.homework.entities.Trip;
import tqs.homework.repositories.ReservationRepository;
import tqs.homework.services.impl.ReservationServiceImpl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    protected static final Logger logger = LogManager.getLogger(ReservationServiceTest.class);

    @Mock( lenient = true)
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up ReservationServiceTest");

        Trip someTrip = new Trip("Porto", "Lisboa"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(15,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(18,10))
                                , 999);
        someTrip.setId(1L);

        Reservation reservation1 = new Reservation("Alex", "alex@alex.alex", 30, someTrip, 999);
        reservation1.setId(1L);

        Reservation reservation2 = new Reservation("Lily", "lily@lily.lily", 40, someTrip, 999);
        reservation2.setId(2L);

        Reservation reservation3 = new Reservation("John", "john@john.john", 2, someTrip, 1399);
        reservation3.setId(3L);

        List<Reservation> reservations = Arrays.asList(reservation1, reservation2, reservation3);

        Mockito.when(reservationRepository.findById(reservation1.getId())).thenReturn(Optional.of(reservation1));
        Mockito.when(reservationRepository.findById(reservation2.getId())).thenReturn(Optional.of(reservation2));
        Mockito.when(reservationRepository.findById(-99L)).thenReturn(Optional.empty());

        Mockito.when(reservationRepository.findAll()).thenReturn(reservations);

        Mockito.when(reservationRepository.save(reservation1)).thenReturn(reservation1);

        Mockito.when(reservationRepository.findByTripId(someTrip.getId())).thenReturn(reservations);
    }

    @Test
    void whenValidIdthenReservationShouldBeFound() {
        logger.info("Testing if reservation is found when valid id is given");
        Reservation found = reservationService.getReservationById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenValidTripIdthenReservationsShouldBeFound() {
        logger.info("Testing if reservations are found when valid trip id is given");
        List<Reservation> found = reservationService.getReservationsByTripId(1L);
        assertThat(found).hasSize(3);
    }

    @Test
    void whenInvalidIdthenReservationShouldNotBeFound() {
        logger.info("Testing if reservation is not found when invalid id is given");
        Reservation found = reservationService.getReservationById(-99L);
        assertThat(found).isNull();
    }

    @Test
    void given3ReservationswhengetAllthenReturn3Records() {
        logger.info("Testing if all reservations are returned");
        List<Reservation> reservations = reservationService.getAllReservations();
        assertThat(reservations).hasSize(3);
    }
}
