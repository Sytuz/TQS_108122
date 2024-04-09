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

import tqs.homework.controllers.TripRestController;
import tqs.homework.entities.Trip;
import tqs.homework.services.TripService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@WebMvcTest(TripRestController.class)
@ContextConfiguration(classes = { TripRestController.class })
class TripRestControllerTest {

    // Variables
    Trip someTrip = new Trip("Porto", "Lisboa"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(15,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(18,10))
                                , 999);

    Trip otherTrip = new Trip("Lisboa", "Porto"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(9,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(12,20))
                                , 999);

    Trip thirdTrip = new Trip("Viseu", "Aveiro"
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(10,0))
                                , java.sql.Timestamp.valueOf(LocalDate.of(2024, Month.APRIL, 10).atTime(11,10))
                                , 699);

    List<Trip> trips = Arrays.asList(someTrip, otherTrip, thirdTrip);
    @MockBean
    private TripService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenPostTripThenCreateTrip() throws Exception {
        when(service.createTrip(Mockito.any())).thenReturn(someTrip);

        RestAssuredMockMvc
            .given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(someTrip)
            .when()
                .post("/api/trip")
            .then()
                .statusCode(201)
                .body("depName", is("Porto"))
                .body("arrName", is("Lisboa"))
                .body("price", is(999));
    }

    @Test
    void givenTripsWhenGetTripsThenReturnJsonArray() throws Exception {
        when(service.getAllTrips()).thenReturn(trips);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/trip")
            .then()
                .statusCode(200)
                .body("size()", is(3));
    }

    @Test
    void givenTripsWhenGetTripsByArrNameAndDepNameThenReturnJsonArray() throws Exception {
        when(service.getTripsByArrNameAndDepNameAndDate("Lisboa", "Porto", new SimpleDateFormat("yyyy-mm-dd").parse("2024-04-10"))).thenReturn(Arrays.asList(someTrip));

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/trip/search?arrName=Lisboa&depName=Porto&date=2024-04-10")
            .then()
                .statusCode(200)
                .body("size()", is(1));
    }
}
