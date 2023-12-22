package bookstore.mocks;

import bookstore.models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BookControllerMockTest {
    BookControllerMock bookControllerMock;

    @BeforeEach
    public void setUp(){
        bookControllerMock = new BookControllerMock();
    }

    @Test
    public void test_writeBook(){
        Book book = new Book("Name", "Author", "1234567890", "Book",
                "Supplier", 15, 25, 10, LocalDate.parse("2003-01-01"));
        bookControllerMock.writeBook(book);
        bookControllerMock.expect(book);
        Assertions.assertTrue(bookControllerMock.verify());
    }

    @Test
    public void test_getBook(){
        Book book = new Book("Name", "Author", "1234567890", "Book",
                "Supplier", 15, 25, 10, LocalDate.parse("2003-01-01"));
        bookControllerMock.writeBook(book);
        System.out.println(bookControllerMock.getBooks());
        Assertions.assertTrue(bookControllerMock.getBooks().contains(bookControllerMock.getBook("1234567890")));
    }
}