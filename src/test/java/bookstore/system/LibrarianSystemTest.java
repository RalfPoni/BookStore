package bookstore.system;

import bookstore.controllers.BookController;
import bookstore.controllers.BillController;
import bookstore.controllers.UserController;
import bookstore.models.*;
import bookstore.view.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import bookstore.ViewTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianSystemTest extends ApplicationTest {


    private static File file;
    private UserController userController;
    @Override
    public void start(Stage stage) throws Exception {
        new ViewTest().start(stage);
    }

    @BeforeEach
    public void setUp() {
        file = new File("books.dat");
        BookController bookController = new BookController();
        BillController billController = new BillController();
        userController = new UserController();

        bookController.writeBook(new Book("Animal Farm", "George Orwell", "2291372790", "Thriller", "BetaSupplier", 4.00, 14.99, 13, LocalDate.now()));
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
                userController.writeUser(new Administrator("Bob", "Odenkirk", "password1","admin@yahoo.com", "+15667553908", 200420));
                userController.writeUser(new Librarian("Power", "Yea", "password1","power@gmail.com", "+15667553908", 200420));
                fileWriter.close();

                file.delete();
            }
            catch(IOException ignored)
            {

            }
        }
    }

    @Test
    public void testLibrarianCreateBookBill() {

        clickOn("#emailTF").write("power@gmail.com");
        clickOn("#passwordTF").write("password1");
        clickOn("#loginButton");

        assertFalse(lookup("#failedLabel").tryQuery().isPresent());

        WaitForAsyncUtils.waitForFxEvents();

        FxAssert.verifyThat(window("Book View"), WindowMatchers.isShowing());

        clickOn("#addToCartButton");

        clickOn("#ISBNTF").write("2291372790");
        clickOn("#quantityTF").write("13");
        clickOn("#checkout");

        WaitForAsyncUtils.waitForFxEvents();

    }

    @Test
    public void testLibrarianCreateBookBill_failISBN() {

        clickOn("#emailTF").write("power@gmail.com");
        clickOn("#passwordTF").write("password1");
        clickOn("#loginButton");

        assertFalse(lookup("#failedLabel").tryQuery().isPresent());

        WaitForAsyncUtils.waitForFxEvents();

        FxAssert.verifyThat(window("Book View"), WindowMatchers.isShowing());

        clickOn("#addToCartButton");

        clickOn("#ISBNTF").write("229372790");
        clickOn("#quantityTF").write("13");
        clickOn("#checkout");

        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("Cart"), WindowMatchers.isShowing());
    }

    @Test
    public void testLibrarianCreateBookBill_failStock() {

        clickOn("#emailTF").write("power@gmail.com");
        clickOn("#passwordTF").write("password1");
        clickOn("#loginButton");

        assertFalse(lookup("#failedLabel").tryQuery().isPresent());

        WaitForAsyncUtils.waitForFxEvents();

        FxAssert.verifyThat(window("Book View"), WindowMatchers.isShowing());

        clickOn("#addToCartButton");

        clickOn("#ISBNTF").write("2291372790");
        clickOn("#quantityTF").write("19");
        clickOn("#checkout");

        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("Cart"), WindowMatchers.isShowing());
    }
    @Test
    public void testLoginFailure() {

        clickOn("#emailTF").write("johndoe@yahoo.com");
        clickOn("#passwordTF").write("wrongpassword");
        clickOn("#loginButton");

        assertTrue(lookup("#failedLabel").tryQuery().isPresent());
    }


}
