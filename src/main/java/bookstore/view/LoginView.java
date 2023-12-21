package bookstore.view;


import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

@SuppressWarnings("FieldMayBeFinal")
public class LoginView extends View{



    private BorderPane borderPane = new BorderPane();
    private StackPane stackPane = new StackPane();
    private GridPane gridPane = new GridPane();

    private TextField emailTF = new TextField();
    private PasswordField passwordTF = new PasswordField();

    private Label emailLabel = new Label("Email");
    private Label passwordLabel = new Label("Password");

    private Button loginButton = new Button("Log in");

    public LoginView() {
        setView();
    }

    public TextField getEmailTF() {
        return emailTF;
    }

    public PasswordField getPasswordTF() {
        return passwordTF;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setView() {
        gridPane.add(emailTF, 1, 0);
        gridPane.add(passwordTF, 1, 1);
        gridPane.add(emailLabel, 0, 0);
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
