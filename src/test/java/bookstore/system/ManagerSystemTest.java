package bookstore.system;

import bookstore.view.AddBookView;
import bookstore.controllers.BookController;
import bookstore.controllers.BillController;
import bookstore.controllers.UserController;
import bookstore.models.Administrator;
import bookstore.models.Book;
import bookstore.models.Librarian;
import bookstore.view.AddBookView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import bookstore.ViewTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ManagerSystemTest extends ApplicationTest{


    private Book testBook;
    private BookController bookController;
    private static File file;
    @Override
    public void start(Stage stage) throws Exception {
        new ViewTest().start(stage);
    }

    @BeforeEach
    public void setUp() {

        bookController = new BookController();
        testBook = new Book("1984", "George Orwell", bookController.generateISBN(), "Comedy", "AlphaSupplier", 9.00, 19.99, 4, LocalDate.now());

        ArrayList<Book> books = bookController.getBooks();
        clickOn("#emailTF").write("admin@yahoo.com");
        clickOn("#passwordTF").write("password1");
        clickOn("#loginButton");

        WaitForAsyncUtils.waitForFxEvents();
    }
    @AfterEach
    public void tearDown()
    {
        if (file != null && file.exists())
        {
            try
            {
                FileWriter fileWriter = new FileWriter("books.dat");
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
    public void testManagerAddBookSuccessfully() {

        assertTrue(lookup("#addBookButton").tryQuery().isPresent());
        clickOn("#addBookButton");

        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("AddBook"), WindowMatchers.isShowing());

        clickOn("#nameTF").write(testBook.getName());
        clickOn("#authorTF").write(testBook.getAuthor());
        clickOn("#categoryTF").write(testBook.getCategory());
        clickOn("#supplierTF").write(testBook.getSupplier());
        clickOn("#originalPriceTF").write(Double.toString(testBook.getOriginalPrice()));
        clickOn("#sellingPriceTF").write(Double.toString(testBook.getSellingPrice()));
        clickOn("#stockTF").write(Integer.toString(testBook.getStock()));
        clickOn("#datePicker").write("1/16/2024").type(KeyCode.ENTER);

        clickOn("#addButton");

        bookController.readBooks();
        assertEquals(1, bookController.getBooks().size());

        Book addedBook = bookController.getBooks().get(0);

        assertAll(
                () -> assertEquals(testBook.getName(), addedBook.getName()),
                () -> assertEquals(testBook.getAuthor(), addedBook.getAuthor()),
                () -> assertEquals(testBook.getCategory(), addedBook.getCategory()),
                () -> assertEquals(testBook.getSupplier(), addedBook.getSupplier()),
                () -> assertEquals(testBook.getOriginalPrice(), addedBook.getOriginalPrice()),
                () -> assertEquals(testBook.getSellingPrice(), addedBook.getSellingPrice()),
                () -> assertEquals(testBook.getStock(), addedBook.getStock()),
                () -> assertEquals(testBook.getDatePurchased(), addedBook.getDatePurchased())
        );


    }

}
