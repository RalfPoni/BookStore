package tests;
import static org.junit.jupiter.api.Assertions.*;

import bookstore.controllers.BillController;
import bookstore.models.Bill;
import bookstore.models.Librarian;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

public class BillControllerTest {

    private BillController billController;
    private Bill testBill;
    private final String filename = "testBills.dat";
    private final String counterFilename = "testBillNumbers.dat";
    private Librarian user = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
    private static File file1;
    private static File file2;

    @BeforeEach
    public void setUp()
    {
        billController = new BillController();
        billController.setFileNames(filename, counterFilename);
        testBill = new Bill("12345678", 2, 14.99, user, LocalDate.now());
    }

    @AfterEach
    public void tearDown()
    {
        if (file1 != null && file1.exists() && (file2 != null && file2.exists()))
        {
            try
            {
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.write("");
                fileWriter.close();

                file1.delete();

                fileWriter = new FileWriter(counterFilename);
                fileWriter.write("");
                fileWriter.close();

                file2.delete();
            }
            catch(IOException e)
            {

            }
        }
    }

    @Test
    public void testCreateBill()
    {
        BillController.setCounter(21);

        billController.createBill(testBill);

        assertFalse(new File("BillNumber" + 21 + ".txt").isFile());
        assertFalse(new File("BillNo" + 20 + ".txt").isFile());
        assertTrue(new File("BillNo" + 21 + ".txt").isFile());
        assertFalse(new File("BillNo" + 21 + ".dat").isFile());

        try
        {
            FileWriter fileWriter = new FileWriter("BillNo" + 21 + ".txt");
            fileWriter.write("");
            fileWriter.close();

            new File("BillNo" + 21 + ".txt").delete();
        }
        catch(IOException e)
        {

        }

    }


    @Test
    public void testWriteBillAndReadBill()
    {

        billController.writeBill(testBill);
        billController.readBill();

        ArrayList<Bill> bills = billController.getBills();

        assertAll(
                ()->assertEquals(testBill.getISBN(), bills.get(0).getISBN()),
                ()->assertEquals(testBill.getTotalPrice(), bills.get(0).getTotalPrice()),
                ()->assertEquals(testBill.getQuantity(), bills.get(0).getQuantity()),
                ()->assertEquals(testBill.getDate(), bills.get(0).getDate())
        );

        try
        {
            FileWriter fileWriter = new FileWriter("BillNo0.txt");
            fileWriter.write("");
            fileWriter.close();

            new File("BillNo0.txt").delete();
        }
        catch(IOException e)
        {

        }

    }

    @Test
    public void testGetCounterAndSetCounter()
    {

        int initialCounter = BillController.getCounter();
        BillController.setCounter(initialCounter + 1);

        int newCounter = BillController.getCounter();

        assertEquals(initialCounter + 1, newCounter);
    }

}
