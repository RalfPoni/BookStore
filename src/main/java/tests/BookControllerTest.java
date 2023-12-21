package tests;

import bookstore.models.Book;
import bookstore.controllers.BookController;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

import java.io.*;


public class BookControllerTest {

    private BookController bookController;
    private Book testBook;
    private Book testBook2;
    private String isbn2;

    private final String filename = "testBooks.dat";
    private static File file;

    @BeforeEach
    public void setUp() {
        file = new File(filename);
        bookController = new BookController();
        String isbn1 = bookController.generateISBN();
        isbn2 = bookController.generateISBN();
        testBook = new Book("1984", "George Orwell", isbn1, "Comedy", "AlphaSupplier", 9.00, 19.99, 4, LocalDate.now());
        testBook2 = new Book("Animal Farm", "George Orwell", isbn2, "Thriller", "BetaSupplier", 4.00, 14.99, 2, LocalDate.now());

        bookController.setFilename(filename);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @AfterEach
    public void tearDown()
    {
        if (file != null && file.exists())
        {
            try
            {
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.write("");
                fileWriter.close();

                file.delete();
            }
            catch(IOException ignored)
            {

            }
        }
    }
    @Test
    public void testAddAndGetBooks() {
        bookController.addBook(testBook);
        ArrayList<Book> books = bookController.getBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertTrue(books.contains(testBook));
    }


    @Test
    public void testWriteAndReadBook()
    {
        bookController.writeBook(testBook);

        Book readBook = bookController.getBook(testBook.getISBN());

        assertNotNull(readBook);
        assertAll(
                ()-> assertEquals(testBook.getISBN(), readBook.getISBN()),
                ()-> assertEquals(testBook.getName(), readBook.getName()),
                ()-> assertEquals(testBook.getAuthor(), readBook.getAuthor()),
                ()-> assertEquals(testBook.getCategory(), readBook.getCategory()),
                ()-> assertEquals(testBook.getSellingPrice(), readBook.getSellingPrice()),
                ()-> assertEquals(testBook.getOriginalPrice(), readBook.getOriginalPrice()),
                ()-> assertEquals(testBook.getSupplier(), readBook.getSupplier()),
                ()-> assertEquals(testBook.getStock(), readBook.getStock()),
                ()-> assertEquals(testBook.getDatePurchased(), readBook.getDatePurchased())
        );
        assertNull(bookController.getBook("123456789"));
        assertNull(bookController.getBook(isbn2));

        bookController.writeBook(testBook2);
        bookController.readBooks();

        ArrayList<Book> books = bookController.getBooks();

        assertEquals(2, books.size());
        assertEquals(testBook.getISBN(), books.get(0).getISBN());
        assertEquals(testBook2.getISBN(), books.get(1).getISBN());

    }


    @Test
    public void testClearBooks()
    {
        bookController.addBook(testBook);
        bookController.clear();
        ArrayList<Book> books = bookController.getBooks();

        assertNotNull(books);
        assertEquals(0, books.size());

    }
}