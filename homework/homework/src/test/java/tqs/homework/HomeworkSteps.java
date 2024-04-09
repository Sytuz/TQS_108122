package tqs.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.slf4j.Logger;

public class HomeworkSteps {
    static final Logger log = getLogger(HomeworkSteps.class);
    
    private WebDriver driver;

    private String strVar;
    private int intVar;
    private double doubleVar;

    @Before
    public void setUp() {
        log.info("Setting up the browser");
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
    }

    @Given("I am on the homepage")
    public void goToHomePage() {
        log.info("Going to the home page");
        driver.get("http://localhost:8080/home");
    }

    @Given("I made a reservation")
    public void madeReservation() {
        log.info("Making a reservation - Not implemented in this test");
    }

    @Given("I have selected a trip")
    public void goToTripPage() {
        log.info("Going to a trip page");
        WebElement element = driver.findElements(By.name("tripCode")).get(0);
        // Scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Click on the element
        element.click();
    }

    @When("I input {string} as the reservation code")
    public void inputReservationCode(String resCode) {
        log.info("Inputting reservation code: " + resCode);
        driver.findElement(By.name("resCode")).click();
        driver.findElement(By.name("resCode")).sendKeys(resCode);
    }

    @When("I input {string} as the alteration code")
    public void inputAlterationCode(String altCode) {
        log.info("Inputting alteration code: " + altCode);
        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("altCode")));
        driver.findElement(By.id("altCode")).click();
        driver.findElement(By.id("altCode")).sendKeys(altCode);
    }

    @When("I choose a different seat")
    public void chooseSeat() {
        log.info("Choosing a different seat");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("selected")));
        strVar = driver.findElement(By.className("selected")).getAttribute("id");
        driver.findElements(By.className("bus-seat")).get(5).click();
    }

    @When("I choose a more expensive seat")
    public void chooseExpensiveSeat() {
        log.info("Choosing a more expensive seat");
        driver.findElements(By.className("bus-seat")).get(30).click();
        doubleVar = Double.valueOf(driver.findElement(By.id("price")).getAttribute("value"));
        driver.findElements(By.className("bus-seat")).get(5).click();
    }

    @When("I try to choose a seat that is already taken")
    public void chooseTakenSeat() {
        log.info("Choosing a seat that is already taken");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("selected")));
        strVar = driver.findElement(By.className("selected")).getAttribute("id");
        driver.findElements(By.className("reserved")).get(0).click();
    }

    @When("I enter valid payment information")
    public void enterPaymentInfo() {
        log.info("Entering valid payment information");
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("abc@abc.abc");
        driver.findElement(By.id("nif")).click();
        driver.findElement(By.id("nif")).sendKeys("123456789");
        driver.findElement(By.id("card")).click();
        driver.findElement(By.id("card")).sendKeys("1234 5678 9012 3456");
        driver.findElement(By.id("exp")).click();
        driver.findElement(By.id("exp")).sendKeys("12/24");
        driver.findElement(By.id("cvv")).click();
        driver.findElement(By.id("cvv")).sendKeys("123");
    }

    @When("I click the {string} button")
    public void clickButton(String button) {
        log.info("Clicking on the " + button + " button");
        driver.findElement(By.id(button)).click();
    }

    @Then("the reservation information should be displayed")
    public void seeReservationInfo() {
        log.info("Checking if reservation info is displayed");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reservationInfo")));
        assert(driver.findElements(By.id("reservationInfo")).size() > 0);
    }

    @Then("an alert should be displayed with the message {string}")
    public void seeAlert(String message) {
        log.info("Checking if alert is displayed with message: " + message);
        assert(driver.findElements(By.className("alert-success")).size() == 1);

        String text = driver.findElement(By.className("alert-success")).getText();
        assert(text.contains(message));
    }

    @Then("the selected seat should change")
    public void checkSeatChange() {
        log.info("Checking if the selected seat has changed");
        assert(strVar != driver.findElement(By.cssSelector("selected")).getAttribute("id"));
    }

    @Then("the total price should increase")
    public void checkPriceIncrease() {
        log.info("Checking if the total price has increased");
        assert(doubleVar < Double.valueOf(driver.findElement(By.id("price")).getText()));
    }

    @Then("the selected seat should not change")
    public void checkSeatNotChange() {
        log.info("Checking if the selected seat has not changed");
        assert(strVar.equals(driver.findElement(By.className("selected")).getAttribute("id")));
    }
}
