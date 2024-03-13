package tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ReservePage extends BasePage{
    
    public ReservePage(WebDriver driver, int timeoutSec) {
        this(driver);
        setTimeoutSec(timeoutSec);
    }

    public ReservePage(WebDriver driver) {
        super(driver);
    }

    public void selectFlight(int optionNumber) {
        click(By.cssSelector("tr:nth-child(" + optionNumber + ") .btn"));
    }

    public boolean success() {
        return driver.getTitle().equals("BlazeDemo Purchase");
    }
}
