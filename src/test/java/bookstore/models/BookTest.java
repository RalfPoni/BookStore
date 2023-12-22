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
            Book testBook = new Book("Loooooooooo", "W132JEJEWJ", "9999999999",
                    "Whatever","KASOKKSOA", 1400, 20, 1000, LocalDate.now());
        });
        Assertions.assertDoesNotThrow(() -> {
            Book testBook = new Book("Jamie", "Jonie", "1234567890", "Scifi"
                    , "AJA", 12, 20, 20, LocalDate.parse("2023-02-05"));
        });
        Assertions.assertDoesNotThrow(() -> {
            Book testBook = new Book("U", "K-Dot", "1-954-23345-X",
                    "Conscious", "DefJam", 55, 30, 9999999, LocalDate.parse("1312-01-12"));

        });
    }
    @ParameterizedTest
    @CsvSource({
            "Fling, Sting, 1130123, Breath, Breathe, 12, 23, 123, 1992-12-08",
            "F;p, Knack, 1234567890, Freeze, Flash, 11, 15, 222222, 1991-15-15"
    })
    public void test_bookConstructor_wrongInputs(String name, String author, String ISBN,
                                                 String category, String supplier, double originalPrice,
                                                 double sellingPrice, int stock, String date) {
        Assertions.assertThrows(Exception.class, () ->{
            Book newBook = new Book(name, author, ISBN, category, supplier, originalPrice, sellingPrice,
                    stock, LocalDate.parse(date));
        });
    }
}
