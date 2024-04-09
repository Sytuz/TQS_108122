package tqs.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.homework.entities.Trip;
import tqs.homework.repositories.TripRepository;
import tqs.homework.services.impl.TripServiceImpl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {
    protected static final Logger logger = LogManager.getLogger(TripServiceTest.class);

    @Mock( lenient = true)
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripService;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up TripServiceTest");
        Trip trip1 = new Trip("Porto", "Lisboa"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(15,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(18,10))
                                , 999);
        trip1.setId(1L);

        Trip trip2 = new Trip("Lisboa", "Porto"
                                    , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(9,0))
                                    , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(12,20))
                                    , 999);
        trip2.setId(2L);

        Trip trip3 = new Trip("Viseu", "Aveiro"
                                    , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(10,0))
                                    , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(11,10))
                                    , 699);
        trip3.setId(3L);

        List<Trip> allTrips = Arrays.asList(trip1, trip2, trip3);

        Mockito.when(tripRepository.findById(trip1.getId())).thenReturn(Optional.of(trip1));
        Mockito.when(tripRepository.findById(trip2.getId())).thenReturn(Optional.of(trip2));
        Mockito.when(tripRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(tripRepository.findAll()).thenReturn(allTrips);

        Mockito.when(tripRepository.save(trip1)).thenReturn(trip1);

        Mockito.when(tripRepository.findByArrNameAndDepNameAndDate(trip1.getArrName(), trip1.getDepName(), trip1.getDate())).thenReturn(Arrays.asList(trip1));

    }

    @Test
    void whenValidIdthenTripShouldBeFound() {
        logger.info("Testing if trip is found when valid id is given");
        Trip found = tripService.getTripById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenTripShouldNotBeFound() {
        logger.info("Testing if trip is not found when invalid id is given");
        Trip found = tripService.getTripById(-99L);
        assertThat(found).isNull();
    }

    @Test
    void given3TripswhengetAllthenReturn3Records() {
        logger.info("Testing if all trips are returned");
        Trip trip1 = new Trip("Porto", "Lisboa"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(15,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(18,10))
                                , 999);
        trip1.setId(1L);

        Trip trip2 = new Trip("Lisboa", "Porto"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(9,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(12,20))
                                , 999);
        trip2.setId(2L);

        Trip trip3 = new Trip("Viseu", "Aveiro"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(10,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(11,10))
                                , 699);
        trip3.setId(3L);

        List<Trip> found = tripService.getAllTrips();
        assertThat(found).hasSize(3).extracting(Trip::getId).contains(trip1.getId(), trip2.getId(), trip3.getId());
    }

    @Test
    void whenSaveTripthenTripShouldBeReturned() {
        logger.info("Testing if trip is saved and returned");
        Trip trip = new Trip("Porto", "Lisboa"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(15,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(18,10))
                                , 999);
        trip.setId(1L);
        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Trip savedTrip = tripService.createTrip(trip);
        assertThat(savedTrip).isNotNull();
        assertThat(savedTrip.getArrName()).isEqualTo("Lisboa");
    }

    @Test
    void filterTripsByArrNameAndDepNameAndDate() {
        logger.info("Testing if trips are filtered by arrival name, departure name and date");
        Trip trip = new Trip("Porto", "Lisboa"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(15,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(18,10))
                                , 999);
        trip.setId(1L);
        List<Trip> found = tripService.getTripsByArrNameAndDepNameAndDate(trip.getArrName(), trip.getDepName(), trip.getDate());
        assertThat(found).hasSize(1).extracting(Trip::getId).contains(trip.getId());
    }
}   
