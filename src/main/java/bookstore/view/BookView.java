package bookstore.view;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

import bookstore.InvalidBookException;
import bookstore.controllers.BookController;
import bookstore.controllers.UserController;
import bookstore.models.Book;
import bookstore.models.User;
import bookstore.view.ManageEmployeesView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

//selling price, original price, stock

public class BookView extends BorderPane{

    AddBookView addBook = new AddBookView();

    BookController books = new BookController();

    User user = new UserController().getCurrentUser();

    private final TableView<Book> tableView = new TableView<>();
    private final TableColumn<Book, String> nameColumn = new TableColumn<>("Title");
    private final TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
    private final TableColumn<Book, String> ISBNColumn = new TableColumn<>("ISBN");
    private final TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
    private final TableColumn<Book, String> supplierColumn = new TableColumn<>("Supplier");
    private final TableColumn<Book, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
    private final TableColumn<Book, Double> originalPriceColumn = new TableColumn<>("Original Price");
    private final TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
    private final TableColumn<Book, LocalDate> datePurchasedColumn = new TableColumn<>("Date Purchased");

    private Button addToCartButton = new Button("Add to cart");
    private Button restockButton = new Button("Restock");
    private Button addBookButton = new Button("Add book");
    private Button manageEmployeesButton = new Button("Manage employees");

    public BookView() {

        setView();
		/*addBookButton.setOnAction(e->{
			Stage stage = new Stage();
			Scene scene = new Scene(new AddBookView().getView());

			stage.setScene(scene);
			stage.show();
		}); */
    }
    public void setView() {


        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        authorColumn.setMinWidth(150);
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        ISBNColumn.setMinWidth(150);
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        ISBNColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        categoryColumn.setMinWidth(150);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        supplierColumn.setMinWidth(150);
        supplierColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("supplier"));
        supplierColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        sellingPriceColumn.setMinWidth(150);
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<Book, Double>("sellingPrice"));

        originalPriceColumn.setMinWidth(150);
        originalPriceColumn.setCellValueFactory(new PropertyValueFactory<Book, Double>("originalPrice"));

        stockColumn.setMinWidth(150);
        stockColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("stock"));

        datePurchasedColumn.setMinWidth(200);
        datePurchasedColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("datePurchased"));

        tableView.getColumns().addAll(nameColumn, authorColumn, ISBNColumn, categoryColumn, supplierColumn, sellingPriceColumn, originalPriceColumn, stockColumn, datePurchasedColumn);

        books.readBooks();

        //ObservableList<User> data = FXCollections.observableArrayList(newUserController.getUsers());

        ObservableList<Book> data = FXCollections.observableArrayList(books.getBooks());

        tableView.setItems(data);

        this.setCenter(tableView);

        FlowPane buttonsPane = new FlowPane();


        if(user.getAccessLevel().equals("Administrator") || user.getAccessLevel().equals("Librarian"))buttonsPane.getChildren().add(addToCartButton);
        if(user.getAccessLevel().equals("Manager") || user.getAccessLevel().equals("Administrator")) buttonsPane.getChildren().add(addBookButton);
        if(user.getAccessLevel().equals("Administrator")) buttonsPane.getChildren().add(manageEmployeesButton);



        this.setBottom(buttonsPane);

        getAddBookButton().setOnAction(e-> {

            Stage otherStage = new Stage();

            AddBookView thisView = new AddBookView();

            otherStage.setScene(new Scene(thisView.getView()));
            otherStage.show();

            thisView.getAddButton().setOnAction(t ->{

                try {
                    tableView.getItems().add(thisView.addBook());
                    otherStage.close();

                } catch(NumberFormatException c) {
                    System.out.println("Invalid");
                } catch(DateTimeParseException c) {
                    System.out.println("Invalid");
                } catch(InvalidBookException c) {
                    System.out.println("Invalid");
                }

                for(int i = 0; i < books.getBooks().size(); i++)
                    System.out.println(books.getBooks().get(i));


            });
        });

        getManageEmployeesButton().setOnAction(e->{
            Stage otherStage = new Stage();

            ManageEmployeesView view = new ManageEmployeesView();

            otherStage.setScene(new Scene(view.getView()));
            otherStage.show();



        });

        addToCartButton.setOnAction(e->{
            Stage otherStage = new Stage();

            AddToCartView view = new AddToCartView(otherStage);

            otherStage.setScene(new Scene(view.getView()));
            otherStage.show();
        });


    }



    public void goToAddBookScene(Stage stage, Scene scene) {
        addBookButton.setOnAction(e->{
            stage.setScene(scene);
        });
    }

    public Button getAddToCartButton() {
        return addToCartButton;
    }


    public void setAddToCartButton(Button addToCartButton) {
        this.addToCartButton = addToCartButton;
    }


    public Button getRestockButton() {
        return restockButton;
    }


    public void setRestockButton(Button restockButton) {
        this.restockButton = restockButton;
    }


    public Button getAddBookButton() {
        return addBookButton;
    }


    public void setAddBookButton(Button addBook) {
        this.addBookButton = addBook;
    }

    public Button getManageEmployeesButton() {
        return manageEmployeesButton;
    }
}
