package bookstore.view;

import bookstore.InvalidBookException;
import bookstore.controllers.BookController;
import bookstore.models.Book;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddBookView extends View{

    BookController books = new BookController();

    BorderPane fullPane = new BorderPane();
    GridPane infoPane = new GridPane();
    HBox buttonPane = new HBox();


    TextField nameTF = new TextField();
    TextField authorTF = new TextField();
    TextField categoryTF = new TextField();
    TextField supplierTF = new TextField();
    TextField originalPriceTF = new TextField();
    TextField sellingPriceTF = new TextField();
    TextField stockTF = new TextField();

    Label nameLabel = new Label("Book title:");
    Label authorLabel = new Label("Author:");
    Label categoryLabel = new Label("Category:");
    Label supplierLabel = new Label("Supplier:");
    Label originalPriceLabel = new Label("Original price:");
    Label sellingPriceLabel = new Label("Selling price:");
    Label datePurchasedLabel = new Label("Date purchased:");
    Label stockLabel = new Label("Stock");

    DatePicker datePicker = new DatePicker();

    Button addButton = new Button("Add");

    public AddBookView() {
        setView();
    }

    public void setView() {

        infoPane.add(nameTF, 1, 0);
        infoPane.add(authorTF, 1, 1);
        infoPane.add(categoryTF, 1, 2);
        infoPane.add(supplierTF, 1, 3);
        infoPane.add(originalPriceTF, 1, 4);
        infoPane.add(sellingPriceTF, 1, 5);
        infoPane.add(stockTF, 1, 6);
        infoPane.add(datePicker, 1, 7);

        infoPane.add(nameLabel, 0, 0);
        infoPane.add(authorLabel, 0, 1);
        infoPane.add(categoryLabel, 0, 2);
        infoPane.add(supplierLabel, 0, 3);
        infoPane.add(originalPriceLabel, 0, 4);
        infoPane.add(sellingPriceLabel, 0, 5);
        infoPane.add(stockLabel, 0, 6);
        infoPane.add(datePurchasedLabel, 0, 7);

        buttonPane.getChildren().add(addButton);

        fullPane.setTop(infoPane);
        fullPane.setBottom(buttonPane);

        //public Book(String name, String author, String ISBN, String category, String supplier, double originalPrice, double sellingPrice, int stock)
    }



    public Book addBook() throws InvalidBookException{

        if(nameTF.getText() == null || authorTF.getText() == null || categoryTF.getText() == null || supplierTF.getText() == null
                || originalPriceTF.getText() == null || sellingPriceTF.getText() == null || stockTF.getText() == null || datePicker.getValue() == null) throw new InvalidBookException("");

        Book newBook = new Book(nameTF.getText(), authorTF.getText(), books.generateISBN(), categoryTF.getText(), supplierTF.getText(),
                Double.parseDouble(originalPriceTF.getText()), Double.parseDouble(sellingPriceTF.getText()), Integer.parseInt(stockTF.getText()), datePicker.getValue());

        books.writeBook(newBook);


        return newBook;
    }

    public Button getAddButton() {
        return addButton;
    }


    @Override
    public Parent getView() {
        // TODO Auto-generated method stub
        return fullPane;
    }

}
