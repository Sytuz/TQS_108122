package tqs;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class BookSearchSteps {
    static final Logger log = getLogger(lookup().lookupClass());

    private Library library = new Library();
    private List<Book> result = new ArrayList<>();

    @ParameterType(".*")
    public Date date(String date) throws ParseException {
        // The date can be just the year, or the year and month, or the full date
        int len = date.split(" ").length;
        if (len == 1) {
            return new SimpleDateFormat("yyyy").parse(date);
        } else if (len == 2) {
            return new SimpleDateFormat("yyyy MMMMM").parse(date);
        } else {
            return new SimpleDateFormat("yyyy MMMMM dd").parse(date);
        }
    }

    @DataTableType
    public Book book(Map<String, String> entry) {
        try {
            Book book = new Book(entry.get("Title"), entry.get("Author"), date(entry.get("Published")));
            log.info("Created book: {}", book);
            return book;
        } catch (ParseException e) {
            // Handle the exception here
            e.printStackTrace();
            return null; // or throw a custom exception
        }
    }

    @Given("a library with the following books:")
    public void setupLibraryWithBooks(List<Book> books) {
        log.info("Setting up the library with books");
        books.forEach(library::addBook);
    }

    @When("the customer searches for books published between {date} and {date}")
    public void searchBooksByDate(Date from, Date to) {
        log.info("Searching for books published between {} and {}", from, to);
        library.findBooks(from, to).forEach(result::add);
    }

    @When("the customer searches for books written by {string}")
    public void searchBooksByAuthor(String author) {
        log.info("Searching for books written by {}", author);
        library.findBooksByAuthor(author).forEach(result::add);
    }

    @When("the customer searches for books with the title {string}")
    public void searchBooksByTitle(String title) {
        log.info("Searching for books with the title {}", title);
        library.findBooksByTitle(title).forEach(result::add);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(int amount) {
        log.info("Verifying the amount of books found");
        assertEquals(amount, result.size());
    }

    @Then("Book {int} should have been written by {string}")
    public void verifyBookAuthor(int index, String author) {
        log.info("Verifying the author of book {}", index);
        assertEquals(author, result.get(index - 1).getAuthor());
    }

    @Then("Book {int} should have been published in {date}")
    public void verifyBookPublished(int index, Date published) {
        log.info("Verifying the publication date of book {}", index);
        assertEquals(published, result.get(index - 1).getPublished());
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookTitle(int index, String title) {
        log.info("Verifying the title of book {}", index);
        assertEquals(title, result.get(index - 1).getTitle());
    }
}
