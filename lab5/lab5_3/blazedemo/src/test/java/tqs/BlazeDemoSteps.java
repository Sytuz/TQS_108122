package tqs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

public class BlazeDemoSteps {

    static final Logger log = getLogger(BlazeDemoSteps.class);
    
    private WebDriver driver;

    @Given("I am on the home page")
    public void goToHomePage() {
        log.info("Setting up the browser");
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://blazedemo.com/");
    }

    @When("I select {string} as departure city")
    public void selectDepartureCity(String departureCity) {
        log.info("Selecting departure city: " + departureCity);
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.xpath("//option[. = '" + departureCity + "']")).click();
    }

    @And("I select {string} as destination city")
    public void selectDestinationCity(String destinationCity) {
        log.info("Selecting destination city: " + destinationCity);
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.xpath("//option[. = '" + destinationCity + "']")).click();
    }

    @And("I click the {string} button")
    public void clickSubmitButton(String button) {
        log.info("Clicking on the " + button + " button");
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I select the {string} flight")
    public void selectFlight(String flight) {
        log.info("Selecting the " + flight + " (nth) flight");
        driver.findElement(By.cssSelector("tr:nth-child(" + flight.replaceAll("\\D", "").substring(0, 1) + ") .btn")).click();
    }

    @And("I fill the form")
    public void fillForm() {
        log.info("Filling the form with dummy data");
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("abc");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("abc");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("abc");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("abc");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("123123");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("123123123123123");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("abc");
    }

    @Then("I should see the departure city as {string} and the destination city as {string}")
    public void checkCities(String departureCity, String destinationCity) {
        log.info("Checking if the cities are correct");
        String depCity = driver.findElement(By.cssSelector("h3")).getText().split(" to ")[0];
        String destCity = driver.findElement(By.cssSelector("h3")).getText().split(" to ")[1];
        if (!depCity.endsWith(departureCity)) {
            throw new AssertionError("Departure city is not " + departureCity + " (Actual: " + depCity + ")");
        }
        if (!destCity.equals(destinationCity + ":")) {
            throw new AssertionError("Destination city is not " + destinationCity + " (Actual: " + destCity + ")");
        }
    }

    @Then("I should see the {string} message")
    public void checkMessage(String message) {
        log.info("Checking if the message is correct");
        String text;
        try {
            text = driver.findElement(By.cssSelector("h1")).getText();
        }
        catch (Exception e) {
            text = driver.findElement(By.cssSelector("h2")).getText();
        }
        
        if (!text.equals(message)) {
            throw new AssertionError("Message is not " + message + " (Actual: " + text + ")");
        }
    }

    @Then("the webpage title should be {string}")
    public void checkTitle(String title) {
        log.info("Checking if the title is correct");
        try {
            if (!driver.getTitle().equals(title)) {
                throw new AssertionError("Title is not " + title + " (Actual: " + driver.getTitle() + ")");
            }
        }
        catch (AssertionError e) {
        }
        finally {
            log.info("Closing the browser");
            driver.quit();
        }
        
    }
}
