package bookstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {


    Bill bill;
    Bill bill2;
    Librarian testUser1;
    Librarian testUser2;

    @BeforeEach
    public void setUp() {
        testUser1 = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        testUser2 = new Librarian("Bob", "Odenkirk", "password2","bobOD@gmail.com", "+15667553908", 200420);
        bill = new Bill("1234567890", 5, 10.0, testUser1, LocalDate.now());
        bill2 = new Bill("0987654321", 10, 15.0, testUser2, LocalDate.now());
    }

    @Test
    public void testGetISBN()
    {
        assertEquals("1234567890", bill.getISBN());
        assertEquals("0987654321", bill2.getISBN());

    }
    @Test
    public void testGetQuantity()
    {
        assertEquals(5, bill.getQuantity());
        assertEquals(10, bill2.getQuantity());
    }

    @Test
    public void testGetTotalPrice()
    {
        assertEquals(50.0, bill.getTotalPrice());
        assertEquals(150.0, bill2.getTotalPrice());

    }

    @Test
    public void testGetUser()
    {
        assertEquals(testUser1, bill.getUser());
        assertEquals(testUser2, bill2.getUser());

    }

    @Test
    public void testGetDate()
    {
        LocalDate today = LocalDate.now();
        Assertions.assertEquals(today, bill.getDate());
    }

    @Test
    public void testToString()
    {
        String expected = bill.getISBN() + '\n' + bill.getQuantity() + '\n' + bill.getTotalPrice() + '\n' + testUser1.getFirstName() + '\n' + testUser1.getLastName() + '\n' + bill.getDate().toString();
        assertEquals(expected, bill.toString());
        expected = bill2.getISBN() + '\n' + bill2.getQuantity() + '\n' + bill2.getTotalPrice() + '\n' + testUser2.getFirstName() + '\n' + testUser2.getLastName() + '\n' + bill2.getDate().toString();
        assertEquals(expected, bill2.toString());
    }
    @ParameterizedTest
    @CsvSource({
            "1000, 15, 21",
            "2001, 12, 32",
            "2022, 2, 30",
            "2023, 2, 29",
            "2024, 1, 0"
    })
    public void testSetDate_InvalidDates(int year, int month, int day)
    {
        assertThrows(DateTimeException.class, () -> bill.setDate(LocalDate.of(year, month, day)));

    }
    @ParameterizedTest
    @CsvSource({
            "2002, 10, 9",
            "500, 1, 1",
            "-100, 3, 10",
            "2020, 2, 29"
    })
    public void testSetDate(int year, int month, int day)
    {
        LocalDate newDate = LocalDate.of(year, month, day);
        bill.setDate(newDate);

        assertEquals(newDate, bill.getDate());

    }

    @ParameterizedTest
    @CsvSource({
            "0/596/52068/9",
            "123456789",
            "0-97252298-0-Z",
            "ISBN-11: 0-596-52068-9",
            "0-97252298-0-x"
    })
    public void testSetISBN_InvalidISBN(String ISBN)
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bill.setISBN(ISBN));
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
    public void testSetISBN(String ISBN)
    {
        bill.setISBN(ISBN);
        assertEquals(ISBN, bill.getISBN());
    }


    @ParameterizedTest
    @CsvSource({
        "10, 15.99, 159.9",
            "21, 21, 441",
            "0, 0.1, 0",
    })
    public void test_billSetCost(int quantity, double itemPrice, double expected) {

        Bill bill = new Bill("1234567890", quantity, itemPrice, testUser1, LocalDate.now());
        Assertions.assertEquals(expected, bill.getTotalPrice());

    }

    @ParameterizedTest
    @CsvSource({
            "-1, 1",
            "1, -1",
            "-1,-1"
    })
    public void test_billSetCostAndBillConstructor_negativeValues(int quantity, double itemPrice) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Bill bill = new Bill("1234567890", quantity, itemPrice, testUser1, LocalDate.now());
        });

    }

    @Test
    public void test_setQuantity(){

        Bill bill = new Bill("1234567890", 12, 15, testUser1, LocalDate.now());
        Assertions.assertDoesNotThrow(() -> bill.setQuantity(Integer.MAX_VALUE));
        Assertions.assertDoesNotThrow(() -> bill.setQuantity(0));
        Assertions.assertThrows(Exception.class, () -> bill.setQuantity(-1));
        Assertions.assertThrows(Exception.class, () -> bill.setQuantity(Integer.MIN_VALUE));

    }

    @Test
    public void testSetUser()
    {
        bill.setUser(testUser2);
        assertEquals(testUser2, bill.getUser());
        bill2.setUser(testUser1);
        assertEquals(testUser1, bill2.getUser());

    }

}