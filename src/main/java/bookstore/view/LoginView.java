package bookstore.view;


import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class LoginView extends View{



    private BorderPane borderPane = new BorderPane();
    private StackPane stackPane = new StackPane();
    private GridPane gridPane = new GridPane();

    private TextField firstNameTF = new TextField();
    private TextField lastNameTF = new TextField();
    private PasswordField passwordTF = new PasswordField();

    private Label firstNameLabel = new Label("First Name");
    private Label lastNameLabel = new Label("Last Name");
    private Label passwordLabel = new Label("Password");

    private Button loginButton = new Button("Log in");

    public LoginView() {
        setView();
    }

    public TextField getFirstNameTF() {
        return firstNameTF;
    }

    public TextField getLastNameTF() {
        return lastNameTF;
    }

    public PasswordField getPasswordTF() {
        return passwordTF;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setView() {
        gridPane.add(firstNameTF, 1, 0);
        gridPane.add(lastNameTF, 1, 1);
        gridPane.add(passwordTF, 1, 2);
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(passwordLabel, 0, 2);

        stackPane.getChildren().add(loginButton);

        borderPane.setTop(gridPane);
        borderPane.setBottom(stackPane);


    }

    @Override
    public Parent getView() {
        return borderPane;
    }


}
