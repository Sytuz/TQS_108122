package tqs.testcontainers;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tqs.testcontainers.Book;

import java.util.List;
import java.util.Date;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByTitle(String maker);

    public List<Book> findByAuthor(String author);

    public List<Book> findByPublished(Date published);
}
