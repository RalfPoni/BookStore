package bookstore.view;

import java.time.LocalDate;

import bookstore.controllers.BillController;
import bookstore.controllers.BookController;
import bookstore.controllers.UserController;
import bookstore.models.Bill;
import bookstore.models.User;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddToCartView extends View{

    UserController users = new UserController();
    BillController bills = new BillController();
    BookController books = new BookController();

    BorderPane fullPane = new BorderPane();
    GridPane gridPane = new GridPane();
    StackPane stackPane = new StackPane();

    Button addToCartButton = new Button("Add");

    //	public Bill(String ISBN, int quantity, double itemPrice, User user, LocalDate date) {

    TextField ISBNTF = new TextField();
    TextField quantityTF = new TextField();

    Label ISBNLabel = new Label("ISBN");
    Label quantityLabel = new Label("Quantity");

    public AddToCartView(Stage stage) {
        setView(stage);
    }

    public void setView(Stage stage) {
        gridPane.add(ISBNTF, 1, 0);
        gridPane.add(quantityTF, 1, 1);
        gridPane.add(ISBNLabel, 0, 0);
        gridPane.add(quantityLabel, 0, 1);

        stackPane.getChildren().add(addToCartButton);

        fullPane.setTop(gridPane);
        fullPane.setBottom(stackPane);

        addToCartButton.setOnAction(e->{

            if(Integer.parseInt(quantityTF.getText()) > books.getBook(ISBNTF.getText()).getStock()) {
                System.out.println("Fail");
            }else {

                bills.writeBill(new Bill(ISBNTF.getText(), Integer.parseInt(quantityTF.getText()),
                        books.getBook(ISBNTF.getText()).getSellingPrice(), users.getCurrentUser(), LocalDate.now()));
                stage.close();
            }
        });
    }



    @Override
    public Parent getView() {
        // TODO Auto-generated method stub
        return fullPane;
    }
}