package bookstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @ParameterizedTest
    @CsvSource({
        "10, 15.99, 159.9",
            "21, 21, 441",
            "0, 0.1, 0",
    })
    public void test_billSetCost(int quantity, double itemPrice, double expected) {
        Librarian testUser1 = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        Bill bill = new Bill("1234567890", quantity, itemPrice, testUser1, LocalDate.now());
        Assertions.assertEquals(expected, bill.getTotalPrice());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 1",
            "1, -1",
    })
    public void test_billSetCostAndBillConstructor_negativeValues(int quantity, double itemPrice) {
        Librarian testUser1 = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Bill bill = new Bill("1234567890", quantity, itemPrice, testUser1, LocalDate.now());
        });
    }

    @Test
    public void test_setQuantity(){
        Librarian testUser1 = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        Bill bill = new Bill("1234567890", 12, 15, testUser1, LocalDate.now());
        Assertions.assertDoesNotThrow(() -> bill.setQuantity(Integer.MAX_VALUE));
        Assertions.assertDoesNotThrow(() -> bill.setQuantity(0));
        Assertions.assertThrows(Exception.class, () -> bill.setQuantity(-1));
        Assertions.assertThrows(Exception.class, () -> bill.setQuantity(Integer.MIN_VALUE));
    }

}