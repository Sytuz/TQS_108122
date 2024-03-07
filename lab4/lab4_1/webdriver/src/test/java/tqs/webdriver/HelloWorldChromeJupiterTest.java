package tqs.webdriver;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class HelloWorldChromeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    @Test
    void test(ChromeDriver driver) {
        // Exercise
        String sutUrl = "https://www.ua.pt/";
        driver.get(sutUrl); 
        String title = driver.getTitle(); 
        log.debug("The title of {} is {}", sutUrl, title); 

        // Verify
        
        // Há um breve momento em que o título é "Universidade de Aveiro" e depois muda para "Página Inicial - Universidade de Aveiro"
        // Isto quer dizer que o teste pode falhar
        // Devido a esta inconsistência, o teste foi alterado para verificar se o título é igual a um dos dois possíveis

        //assertThat(title).isEqualTo("Página Inicial - Universidade de Aveiro");

        assertThat(title).isIn("Universidade de Aveiro", "Página Inicial - Universidade de Aveiro");
    }

}