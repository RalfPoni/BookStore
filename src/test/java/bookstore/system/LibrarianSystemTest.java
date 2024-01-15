package bookstore.system;

import bookstore.controllers.BookController;
import bookstore.controllers.BillController;
import bookstore.controllers.UserController;
import bookstore.models.Book;
import bookstore.models.Bill;
import bookstore.models.Librarian;
import bookstore.models.User;
import bookstore.view.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import bookstore.ViewTest;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianSystemTest extends ApplicationTest {



    @BeforeAll
    public static void setUp() {
        Librarian librarian = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166", 230920);
        BookController bookController = new BookController();
        BillController billController = new BillController();
        UserController userController = new UserController();

        userController.writeUser(librarian);

        userController.readUsers();

        boolean verify = userController.verifyUser("johndoe@yahoo.com", "password1");
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ViewTest().start(stage);
    }

    @Test
    public void testLoginSuccess() {

        clickOn("#emailTF").write("johndoe@yahoo.com");
        clickOn("#passwordTF").write("password1");
        clickOn("#loginButton");

        assertFalse(lookup("#failedLabel").tryQuery().isPresent());
    }

    @Test
    public void testLoginFailure() {

        clickOn("#emailTF").write("johndoe@yahoo.com");
        clickOn("#passwordTF").write("wrongpassword");
        clickOn("#loginButton");

        assertTrue(lookup("#failedLabel").tryQuery().isPresent());
    }
}
