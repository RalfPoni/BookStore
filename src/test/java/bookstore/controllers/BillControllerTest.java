package bookstore.controllers;
import static org.junit.jupiter.api.Assertions.*;

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
    private Bill testBill2;
    private final String filename = "testBills.dat";
    private static final String counterFilename = "testBillNumbers.dat";
    private Librarian user = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
    private static File file1;
    private static File file2;

    @BeforeEach
    public void setUp()
    {
        billController = new BillController();
        billController.setFileNames(filename, counterFilename);
        file2 = new File(counterFilename);
        file1 = new File(filename);
        testBill = new Bill("1234567890", 2, 14.99, user, LocalDate.now());
        testBill2 = new Bill("1234567891", 2, 17.99, user, LocalDate.now());
    }

    @AfterEach
    public void tearDown()
    {
        if (file1 != null && file1.exists() && (file2 != null && file2.exists()))
        {
            try
            {
                System.out.println("Hee");
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
        } else {
            System.out.println("JE");
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
        billController.writeBill(testBill2);
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
        catch(IOException ignore)
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
