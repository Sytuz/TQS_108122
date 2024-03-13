package tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class PurchasePage extends BasePage{
    public PurchasePage(WebDriver driver, int timeoutSec) {
        this(driver);
        setTimeoutSec(timeoutSec);
    }

    public PurchasePage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String name, String address, String city, String state, String zipCode, String creditCardNumber, String nameOnCard) {
        type(By.id("inputName"), name);
        type(By.id("address"), address);
        type(By.id("city"), city);
        type(By.id("state"), state);
        type(By.id("zipCode"), zipCode);
        type(By.id("creditCardNumber"), creditCardNumber);
        type(By.id("nameOnCard"), nameOnCard);
    }

    public void submit() {
        click(By.cssSelector(".btn-primary"));
    }

    public boolean success() {
        return driver.getTitle().equals("BlazeDemo Confirmation");
    }
}
