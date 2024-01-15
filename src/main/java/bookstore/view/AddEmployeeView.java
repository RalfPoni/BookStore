package bookstore.view;

import bookstore.controllers.UserController;
import bookstore.models.Administrator;
import bookstore.models.Librarian;
import bookstore.models.Manager;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class AddEmployeeView extends View{

    private final BorderPane fullPane = new BorderPane();

    private final GridPane gridPane = new GridPane();
    private final StackPane stackPane = new StackPane();

    private final UserController users = new UserController();

    //public User(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {


    private final TextField nameTF = new TextField();
    private final TextField lastNameTF = new TextField();
    private final PasswordField passwordTF = new PasswordField();
    private final TextField emailTF = new TextField();
    private final TextField phoneNumberTF = new TextField();
    private final TextField salaryTF = new TextField();
    private final TextField accessLevelTF = new TextField();

    private final Label nameLabel = new Label("Name:");
    private final Label lastNameLabel = new Label("Last Name:");
    private final Label passwordLabel = new Label("Password:");
    private final Label emailLabel = new Label("Email:");
    private final Label phoneNumberLabel = new Label("Phone Number:");
    private final Label salaryLabel = new Label("Salary:");
    private final Label accessLevelLabel = new Label("Access Label:");

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

            addEmployeeButtonFunction(view, stage, accessLevelTF.getText(), nameTF.getText(), lastNameTF.getText(),
                    passwordTF.getText(), emailTF.getText(), phoneNumberLabel.getText(), Float.parseFloat(salaryTF.getText()));

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
    //users.writeUser(new Librarian(nameTF.getText(), lastNameTF.getText(),
    // passwordTF.getText(), emailTF.getText(), phoneNumberTF.getText(), Float.parseFloat(salaryTF.getText())));
    public boolean addEmployeeButtonFunction(ManageEmployeesView view, Stage stage, String accessLevel, String name, String lastName,
                                             String password, String email, String phoneNumber, Float salary) {

        return switch (accessLevel) {
            case "Administrator" ->
                    users.writeUser(new Administrator(name, lastName, password, email, phoneNumber, salary));
            case "Manager" ->
                    users.writeUser(new Manager(name, lastName, password, email, phoneNumber, salary));
            case "Librarian" ->
                    users.writeUser(new Librarian(name, lastName, password, email, phoneNumber, salary));
            default -> {
                System.out.println("Invalid");
                yield false;
            }
        };

    }
    }

}
