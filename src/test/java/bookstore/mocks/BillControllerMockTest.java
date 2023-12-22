package bookstore.mocks;

import bookstore.models.Bill;
import bookstore.models.Librarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillControllerMockTest {

    private BillControllerMock billControllerMock;
    private Bill bill;
    private Bill bill2;
    private final Librarian user = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);

    private final Librarian user2 = new Librarian("Bob", "Odenkirk", "password2","bobOD@gmail.com", "+15667553908", 200420);
    @BeforeEach
    public void setUp()
    {
        billControllerMock = new BillControllerMock();
        bill = new Bill("1234567890", 2, 14.99, user, LocalDate.now());
        bill2 = new Bill("0987654321", 10, 15.0, user2, LocalDate.now());
    }

    @Test
    public void testWriteBill()
    {

        billControllerMock.writeBill(bill);
        ArrayList<Bill> bills = billControllerMock.getBills();
        assertEquals(1, bills.size());
        assertEquals(bill, bills.get(0));
    }

    @Test
    public void testWriteBillNo()
    {
        billControllerMock.writeBillNo(2);
        assertEquals(2, billControllerMock.getBillNo());
        billControllerMock.writeBillNo(21);
        assertEquals(21, billControllerMock.getBillNo());
    }

    @Test
    public void testGetBills()
    {

        billControllerMock.writeBill(bill);
        billControllerMock.writeBill(bill2);
        ArrayList<Bill> bills = billControllerMock.getBills();
        assertEquals(2, bills.size());
        assertEquals(bill, bills.get(0));
        assertEquals(bill2, bills.get(1));
    }

    @Test
    public void testCreateBill()
    {
        int initialCounter = billControllerMock.getBillNo();
        billControllerMock.createBill(bill);
        assertEquals(initialCounter + 1, billControllerMock.getBillNo());
    }
}