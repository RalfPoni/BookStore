package bookstore.view;

import bookstore.controllers.UserController;
import bookstore.models.Administrator;
import bookstore.models.Librarian;
import bookstore.models.Manager;
import bookstore.models.User;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddEmployeeView extends View{

    private BorderPane fullPane = new BorderPane();

    private GridPane gridPane = new GridPane();
    private StackPane stackPane = new StackPane();

    private UserController users = new UserController();

    //public User(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {


    private TextField nameTF = new TextField();
    private TextField lastNameTF = new TextField();
    private PasswordField passwordTF = new PasswordField();
    private TextField emailTF = new TextField();
    private TextField phoneNumberTF = new TextField();
    private TextField salaryTF = new TextField();
    private TextField accessLevelTF = new TextField();

    private Label nameLabel = new Label("Name:");
    private Label lastNameLabel = new Label("Last Name:");
    private Label passwordLabel = new Label("Password:");
    private Label emailLabel = new Label("Email:");
    private Label phoneNumberLabel = new Label("Phone Number:");
    private Label salaryLabel = new Label("Salary:");
    private Label accessLevelLabel = new Label("Access Label:");

    Button addEmployeeButton = new Button("Add Employee");

    public AddEmployeeView(ManageEmployeesView view, Stage stage) {
        setView(view, stage);
    }

    public void setView(ManageEmployeesView view, Stage stage) {
        gridPane.add(nameTF, 1, 0);
        gridPane.add(lastNameTF, 1, 1);
        gridPane.add(passwordTF, 1, 2);
        gridPane.add(emailTF, 1, 3);
        gridPane.add(phoneNumberTF, 1, 4);
        gridPane.add(salaryTF, 1, 5);
        gridPane.add(accessLevelTF, 1, 6);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(emailLabel, 0, 3);
        gridPane.add(phoneNumberLabel, 0, 4);
        gridPane.add(salaryLabel, 0, 5);
        gridPane.add(accessLevelLabel, 0, 6);

        stackPane.getChildren().add(addEmployeeButton);

        fullPane.setTop(gridPane);
        fullPane.setBottom(stackPane);

        addEmployeeButton.setOnAction(e->{

            switch(accessLevelTF.getText()) {
                case "Administrator":
                    users.writeUser(new Administrator(nameTF.getText(), lastNameTF.getText(), passwordTF.getText(), emailTF.getText(), phoneNumberTF.getText(), Float.parseFloat(salaryTF.getText())));
                    break;
                case "Manager":
                    users.writeUser(new Manager(nameTF.getText(), lastNameTF.getText(), passwordTF.getText(), emailTF.getText(), phoneNumberTF.getText(), Float.parseFloat(salaryTF.getText())));
                    break;
                case "Librarian":
                    users.writeUser(new Librarian(nameTF.getText(), lastNameTF.getText(), passwordTF.getText(), emailTF.getText(), phoneNumberTF.getText(), Float.parseFloat(salaryTF.getText())));
                    break;
                default:
                    System.out.println("Invalid");
                    break;
            }

            view.setUserPane();
            stage.close();
        });
    }


    public Button getAddEmployeeButton() {
        return addEmployeeButton;
    }

    @Override
    public Parent getView() {
        // TODO Auto-generated method stub
        return fullPane;
    }

}
