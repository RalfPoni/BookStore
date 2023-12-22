package bookstore.models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bookstore.models.Book;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

public class BookTest {

    Book book1;
    Book book2;
    @BeforeEach
    public void setUp(){
        book1 = new Book("The Art of War", "Sun Tzu", "1234567890", "Philosophy",
                "BookSupplier", 15, 20, 50, LocalDate.parse("2001-12-12"));
        book2 = new Book("lt4;", "Leso", "9512345678", "skdsdi", "lss",
                        40, 1000, 4, LocalDate.parse("1100-01-01"));
    }
    @Test
    public void test_bookNameGetter(){
        Assertions.assertEquals("The Art of War", book1.getName());
        Assertions.assertEquals("lt4;", book2.getName());
    }

    @Test
    public void test_bookAuthorGetter(){
        Assertions.assertEquals("Sun Tzu", book1.getAuthor());
        Assertions.assertEquals("Leso", book2.getAuthor());
    }

    @Test
    public void test_bookISBNGetter(){
        Assertions.assertEquals("1234567890", book1.getISBN());
        Assertions.assertEquals("9512345678", book2.getISBN());
    }

    @Test
    public void test_bookCategoryGetter(){
        Assertions.assertEquals("Philosophy", book1.getCategory());
        Assertions.assertEquals("skdsdi", book2.getCategory());
    }

    @Test
    public void test_bookSupplierGetter(){
        Assertions.assertEquals("BookSupplier", book1.getSupplier());
        Assertions.assertEquals("lss", book2.getSupplier());
    }

    @Test
    public void test_bookOriginalPriceGetter(){
        Assertions.assertEquals(15, book1.getOriginalPrice());
        Assertions.assertEquals(40, book2.getOriginalPrice());
    }

    @Test
    public void test_bookSellingPriceGetter(){
        Assertions.assertEquals(20, book1.getSellingPrice());
        Assertions.assertEquals(1000, book2.getSellingPrice());
    }

    @Test
    public void test_bookStockGetter(){
        Assertions.assertEquals(50, book1.getStock());
        Assertions.assertEquals(4, book2.getStock());
    }

    @Test
    public void test_bookDateGetter(){
        Assertions.assertEquals(LocalDate.parse("2001-12-12"), book1.getDatePurchased());
        Assertions.assertEquals(LocalDate.parse("1100-01-01"), book2.getDatePurchased());
    }

    @Test
    public void test_bookCostGetter(){
        Assertions.assertEquals(750, book1.getCost());
        Assertions.assertEquals(160, book2.getCost());
    }

    @Test
    public void test_toString()
    {
        String expected = "Book Title: " + book1.getName() + " | Book Author: " + book1.getAuthor() + " | Book Category " + book1.getCategory() + " | Book ISBN " + book1.getISBN()
                + " | Book Supplier: " + book1.getSupplier() + "\nBook Original Price: " + book1.getOriginalPrice() + " | Book Selling Price: " + book1.getSellingPrice()
                + "\nBook Stock: " + book1.getStock();

        Assertions.assertEquals(expected, book1.toString());

        expected = "Book Title: " + book2.getName() + " | Book Author: " + book2.getAuthor() + " | Book Category " + book2.getCategory() + " | Book ISBN " + book2.getISBN()
                + " | Book Supplier: " + book2.getSupplier() + "\nBook Original Price: " + book2.getOriginalPrice() + " | Book Selling Price: " + book2.getSellingPrice()
                + "\nBook Stock: " + book2.getStock();

        Assertions.assertEquals(expected, book2.toString());

    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, 100, Double.MAX_VALUE-1, Double.MAX_VALUE})
    public void test_originalPriceSetter_correctValues(double numbers){
        book1.setOriginalPrice(numbers);
        Assertions.assertEquals(numbers, book1.getOriginalPrice());
    }
    @ParameterizedTest
    @ValueSource(doubles = {-0.1, -1, -100, (-1)*Double.MAX_VALUE+1, (-1)*Double.MAX_VALUE})
    public void test_originalPriceSetter_wrongValues(double numbers){
        Assertions.assertThrows(Exception.class, () -> book1.setOriginalPrice(numbers));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, 100, Double.MAX_VALUE-1, Double.MAX_VALUE})
    public void test_sellingPriceSetter_correctValues(double numbers){
        book1.setSellingPrice(numbers);
        Assertions.assertEquals(numbers, book1.getSellingPrice());
    }
    @ParameterizedTest
    @ValueSource(doubles = {-0.1, -1, -100, (-1)*Double.MAX_VALUE+1, (-1)*Double.MAX_VALUE})
    public void test_sellingPriceSetter_wrongValues(double numbers){
        Assertions.assertThrows(Exception.class, () -> book1.setSellingPrice(numbers));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100, Integer.MAX_VALUE-1, Integer.MAX_VALUE})
    public void test_stockSetter_correctValues(int numbers){
        book1.setStock(numbers);
        Assertions.assertEquals(numbers, book1.getStock());
    }
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -100, Integer.MIN_VALUE+1, Integer.MIN_VALUE})
    public void test_stockSetter_wrongValues(int numbers){
        Assertions.assertThrows(Exception.class, () -> book1.setStock(numbers));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "100, 100",
            "1, 0",
            "0, 1",
    })
    public void test_bookCostSetter_rightValues(double first, int second){
        book1.setCost(first, second);
        Assertions.assertEquals(first*(double)second, book1.getCost());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, -1",
            "-1, 1",
            "1, -1",
    })
    public void test_bookCostSetter_wrongValues(double first, int second){
        Assertions.assertThrows(Exception.class, () -> book1.setCost(first, second));
    }

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
