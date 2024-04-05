package tqs.testcontainers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tqs.testcontainers.Book;
import tqs.testcontainers.BookRepository;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Testcontainers
@SpringBootTest
class TestcontainersApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
		.withUsername("tqs")
		.withPassword("password")
		.withDatabaseName("books");

	@Autowired
	private BookRepository bookRepository;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	void contextLoads() {
	}


	@Test
	@Order(1)
	void testCreateBook() {
		Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", Date.from(LocalDate.of(1925, Month.APRIL, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		bookRepository.save(book);
		Optional<Book> found = bookRepository.findById(book.getId());

		assertThat(found, notNullValue());
	}

	@Test
	@Order(2)
	void testGetBook() {
		List<Book> found = bookRepository.findByTitle("To Kill a Mockingbird");

		assertThat(found, not(empty()));
		assertThat(found, hasSize(1));

		Book foundBook = found.get(0);

		assertThat(foundBook.getAuthor(), is("Harper Lee"));
		assertThat(foundBook.getTitle(), is("To Kill a Mockingbird"));
	}

}
