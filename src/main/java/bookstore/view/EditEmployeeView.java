package bookstore.view;

import bookstore.controllers.UserController;
import bookstore.models.Administrator;
import bookstore.models.Librarian;
import bookstore.models.Manager;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EditEmployeeView extends View{

    private final UserController users = new UserController();

    private final BorderPane fullPane = new BorderPane();
    private final GridPane gridPane = new GridPane();
    private final StackPane stackPane = new StackPane();

    private final TextField firstNameTF = new TextField();
    private final TextField lastNameTF = new TextField();

    private final Label firstNameLabel = new Label("First Name:");
    private final Label lastNameLabel = new Label("Last Name:");

    private final Button editEmployee = new Button("Edit employee");
    private final Button finalEditButton = new Button("Make changes");

    public EditEmployeeView(ManageEmployeesView view, Stage stage) {
        setView(view, stage);
    }

    public void setView(ManageEmployeesView view, Stage stage) {
        gridPane.add(firstNameTF, 1, 0);
        gridPane.add(lastNameTF, 1, 1);

        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(lastNameLabel, 0, 1);

        stackPane.getChildren().add(editEmployee);

        fullPane.setTop(gridPane);
        fullPane.setBottom(stackPane);

        editEmployee.setOnAction(e->{
            if(users.getUserIndex(firstNameTF.getText(), lastNameTF.getText()) == -1)
                System.out.println("Invalid");
            else {
                users.readUsers();
                Scene scene = new Scene(setEditView(view, users.getUserIndex(firstNameTF.getText(), lastNameTF.getText())));
                stage.setScene(scene);
                stage.show();
            }
        });

    }

    public BorderPane setEditView(ManageEmployeesView view, int listIndex) {
        BorderPane fullEditPane = new BorderPane();

        GridPane gridEditPane = new GridPane();
        StackPane stackEditPane = new StackPane();

        TextField nameTF = new TextField(users.getUsers().get(listIndex).getFirstName());
        TextField lastNameEditTF = new TextField(users.getUsers().get(listIndex).getLastName());
        TextField passwordTF = new TextField(users.getUsers().get(listIndex).getPassword());
        TextField emailTF = new TextField(users.getUsers().get(listIndex).getEmail());
        TextField phoneNumberTF = new TextField(users.getUsers().get(listIndex).getPhoneNumber());
        TextField salaryTF = new TextField(users.getUsers().get(listIndex).getSalary().toString());
        TextField accessLevelTF = new TextField(users.getUsers().get(listIndex).getAccessLevel());

        Label nameEditLabel = new Label("Name:");
        Label lastNameEditLabel = new Label("Last Name:");
        Label passwordLabel = new Label("Password:");
        Label emailLabel = new Label("Email:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label salaryLabel = new Label("Salary:");
        Label accessLevelLabel = new Label("Access Label:");

        gridEditPane.add(nameTF, 1, 0);
        gridEditPane.add(lastNameEditTF, 1, 1);
        gridEditPane.add(passwordTF, 1, 2);
        gridEditPane.add(emailTF, 1, 3);
        gridEditPane.add(phoneNumberTF, 1, 4);
        gridEditPane.add(salaryTF, 1, 5);
        gridEditPane.add(accessLevelTF, 1, 6);

        gridEditPane.add(nameEditLabel, 0, 0);
        gridEditPane.add(lastNameEditLabel, 0, 1);
        gridEditPane.add(passwordLabel, 0, 2);
        gridEditPane.add(emailLabel, 0, 3);
        gridEditPane.add(phoneNumberLabel, 0, 4);
        gridEditPane.add(salaryLabel, 0, 5);
        gridEditPane.add(accessLevelLabel, 0, 6);

        stackEditPane.getChildren().add(finalEditButton);

        finalEditButton.setOnAction(e->{

            if(accessLevelTF.getText().equals("Administrator"))
                users.getUsers().set(listIndex, new Administrator(nameTF.getText(), lastNameEditTF.getText(), passwordTF.getText(), emailTF.getText(), phoneNumberTF.getText(), Float.parseFloat(salaryTF.getText())));
            else if(accessLevelTF.getText().equals("Manager"))
                users.getUsers().set(listIndex, new Manager(nameTF.getText(), lastNameEditTF.getText(), passwordTF.getText(), emailTF.getText(), phoneNumberTF.getText(), Float.parseFloat(salaryTF.getText())));
            else if(accessLevelTF.getText().equals("Librarian"))
                users.getUsers().set(listIndex, new Librarian(nameTF.getText(), lastNameEditTF.getText(), passwordTF.getText(), emailTF.getText(), phoneNumberTF.getText(), Float.parseFloat(salaryTF.getText())));

            else System.out.println("Invalid");

            users.listToFile();
            view.setUserPane();

        });

        fullEditPane.setTop(gridEditPane);
        fullEditPane.setBottom(stackEditPane);

        return fullEditPane;
    }

    @Override
    public Parent getView() {
        // TODO Auto-generated method stub
        return fullPane;
    }

}
