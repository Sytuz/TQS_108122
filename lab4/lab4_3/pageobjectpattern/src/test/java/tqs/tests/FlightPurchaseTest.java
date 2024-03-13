// Generated by Selenium IDE
/* import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
*/
//import static org.assertj.core.api.Assertions.assertThat;

package tqs.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import tqs.webpages.HomePage;
import tqs.webpages.PurchasePage;
import tqs.webpages.ReservePage;

@ExtendWith(SeleniumJupiter.class)
public class FlightPurchaseTest {

  @Test
  public void flightPurchase(ChromeDriver driver) {
    HomePage homePage = new HomePage(driver);

    homePage.selectFromPort("Boston");
    homePage.selectToPort("New York");
    homePage.submit();

    assertThat(homePage.success(), is(true));

    ReservePage reservePage = new ReservePage(driver);

    reservePage.selectFlight(3);

    assertThat(reservePage.success(), is(true));

    PurchasePage purchasePage = new PurchasePage(driver);

    purchasePage.fillForm("abc", "abc", "abc", "abc", "123123", "123123123123123", "abc");
    purchasePage.submit();

    assertThat(purchasePage.success(), is(true));
  }
}
