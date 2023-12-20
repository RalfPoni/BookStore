package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bookstore.models.Book;
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
            "0-9752298-0-X",
            "XXXXXXXXXX"
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
}
