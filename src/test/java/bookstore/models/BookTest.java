package bookstore.models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bookstore.models.Book;

import java.time.LocalDate;

public class BookTest {
    @ParameterizedTest
    @CsvSource({
            "0-596-52068-9",
            "0 512 52068 9",
            "ISBN-10 0-596-52068-9",
            "ISBN-10: 0-596-52068-9",
            "1-84356-028-3",
            "85-359-0277-5",
            "0512520689",
            "0-9752298-0-X"
    })
    public void test_verifyISBN_rightISBN(String isbn){
        Assertions.assertTrue(Book.verifyISBN(isbn));
    }

    @ParameterizedTest
    @CsvSource({
            "0/596/52068/9",
            "123456789",
            "0-97252298-0-Z",
            "ISBN-11: 0-596-52068-9",
            "0-97252298-0-x"
    })
    public void test_verifyISBN_wrongISBN(String isbn){
        Assertions.assertFalse(Book.verifyISBN(isbn));
    }

    //String name, String author, String ISBN, String category,
    // String supplier, double originalPrice, double sellingPrice, int stock, LocalDate datePurchased
    @Test
    public void test_bookConstructor_allGood(){

        Assertions.assertDoesNotThrow(() -> {
            Book testBook = new Book("Jamie", "Jonie", "1234567890", "Scifi"
                    , "AJA", 12, 20, 20, LocalDate.parse("2023-02-05"));
        });
    }
}
