package tqs;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class JsonPlaceholderTest {

    @Test
    void theEndpointToListAllToDosIsAvailable() {
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
    }

    @Test
    void queryForSpecificTodoShouldReturnWithTitleX() {
        get("https://jsonplaceholder.typicode.com/todos/4").then().statusCode(200)
        .body("title", equalTo("et porro tempora"));
    }

    @Test
    void listAllToDosShouldHaveCertainIDs() {
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200)
        .body("id", hasItems(198, 199));
    }

    @Test
    void queryShouldTakeLessThanCertainAmmountOfTime() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/todos");
        long timeInS = response.timeIn(TimeUnit.SECONDS);
        
        assertThat(timeInS, lessThan(2L));
    }
}
