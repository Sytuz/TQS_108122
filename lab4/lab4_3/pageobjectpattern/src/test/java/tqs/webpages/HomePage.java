package tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    
    By fromPort = By.name("fromPort");
    By toPort = By.name("toPort");

    public HomePage(WebDriver driver, int timeoutSec) {
        this(driver);
        setTimeoutSec(timeoutSec);
    }

    public HomePage(WebDriver driver) {
        super(driver);
        visit("https://blazedemo.com/");
    }

    public void selectFromPort(String fromPort) {
        selectOption(fromPort, this.fromPort);
    }

    public void selectToPort(String toPort) {
        selectOption(toPort, this.toPort);
    }

    public void submit() {
        click(By.cssSelector(".btn-primary"));
    }

    public void selectOption(String option, By locator) {
        click(locator);
        click(By.xpath("//option[. = '" + option + "']"));
    }

    public boolean success() {
        return driver.getTitle().equals("BlazeDemo - reserve");
    }
}
