package bookstore.system;

import bookstore.mocks.BookControllerMock;
import bookstore.mocks.UserControllerMock;
import bookstore.models.Book;
import bookstore.models.Librarian;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LibrarianCarriesOutOrder {

    private Librarian user;
    private Book book;
    private BookControllerMock bookControllerMock;

    private UserControllerMock userControllerMock;
    private Button loginButton;

    @BeforeAll
    void setUp()
    {
        user = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166", 230920);
        book = new Book("Name", "Author", "1234567890", "Book", "Supplier", 15, 25, 10, LocalDate.parse("2003-01-01"));
        bookControllerMock = new BookControllerMock();
        bookControllerMock.writeBook(book);

        userControllerMock = new UserControllerMock();
        userControllerMock.writeUser(user);

        loginButton = lookup("#loginButton").queryAs(Button.class);
    }

    @Override
    public void start(Stage stage)
    {
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getView(), 300, 200);
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void LibrarianCreatesBillForCustomer()
    {
        clickOn("#emailTF").write("johndoe@yahoo.com");
        clickOn("#passwordTF").write("password1");

        clickOn("#loginButton");


    }
}
