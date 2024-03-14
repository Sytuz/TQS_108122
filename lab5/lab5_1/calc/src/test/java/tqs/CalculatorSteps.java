package tqs;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {
    static final Logger log = getLogger(lookup().lookupClass());

    private Calculator calc;

    @Given("a calculator I just turned on")
    public void setup() {
        log.info("Setting up the calculator");
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        log.info("Adding {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I subtract {int} from {int}")
    public void subtract(int arg1, int arg2) {
        log.info("Subtracting {} from {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {int} and {int}")
    public void multiply(int arg1, int arg2) {
        log.info("Multiplying {} by {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {int} by {int}")
    public void divide(int arg1, int arg2) {
        log.info("Dividing {} by {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }

    @When("I perform an invalid operation")
    public void invalidOperation() {
        log.info("Performing an invalid operation");
        calc.push("invalid");
    }

    @Then("a failure occurs")
    public void failureOccurs() {
        log.info("Verifying a failure occurs");
        assertEquals(Double.NaN, calc.value());
    }

    @Then("the result is {double}")
    public void the_result_is(double expected) {
        log.info("Verifying the result is {}", expected);
        assertEquals(expected, calc.value());
    }
}
