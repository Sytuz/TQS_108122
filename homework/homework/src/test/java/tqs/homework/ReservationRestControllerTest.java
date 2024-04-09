package tqs.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import tqs.homework.controllers.ReservationRestController;
import tqs.homework.services.ReservationService;
import tqs.homework.entities.Reservation;
import tqs.homework.entities.Trip;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ReservationRestController.class)
@ContextConfiguration(classes = { ReservationRestController.class })
class ReservationRestControllerTest {

    /* Variables */ 
    Trip someTrip = new Trip("Porto", "Lisboa"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(15,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(18,10))
                                , 999);

    Reservation someReservation = new Reservation("Alex", "alex@alex.alex", 30, someTrip, 999);

    Reservation otherReservation = new Reservation("Lily", "lily@lily.lily", 40, someTrip, 999);

    Reservation thirdReservation = new Reservation("John", "john@john.john", 2, someTrip, 1399);

    List<Reservation> reservations = Arrays.asList(someReservation, otherReservation, thirdReservation);
    /* ----- */

    @MockBean
    private ReservationService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenPostReservationThenCreateReservation() throws Exception {

        when(service.createReservation(Mockito.any())).thenReturn(someReservation);

        RestAssuredMockMvc
            .given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(someReservation)
            .when()
                .post("/api/reservation")
            .then()
                .statusCode(201)
                .body("name", is("Alex"))
                .body("email", is("alex@alex.alex"))
                .body("seat", is(30));
    }

    @Test
    void givenReservationsWhenGetReservationsThenReturnJsonArray() throws Exception {
        when(service.getAllReservations()).thenReturn(reservations);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/reservation")
            .then()
                .statusCode(200)
                .body("size()", is(3));
    }

    @Test
    void givenTripIdWhenGetReservationsByTripIdThenReturnJsonArray() throws Exception {
        when(service.getReservationsByTripId(1L)).thenReturn(reservations);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/reservation/trip/1")
            .then()
                .statusCode(200)
                .body("size()", is(3));
    }
}