package bookstore.view;

import bookstore.controllers.UserController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RemoveEmployeeView extends View{

    private UserController users = new UserController();

    private BorderPane fullPane = new BorderPane();
    private GridPane gridPane = new GridPane();
    private StackPane stackPane = new StackPane();

    private TextField firstNameTF = new TextField();
    private TextField lastNameTF = new TextField();

    private Label firstNameLabel = new Label("First Name:");
    private Label lastNameLabel = new Label("Last Name:");

    Button removeButton = new Button("Remove Employee");

    public RemoveEmployeeView(ManageEmployeesView view, Stage stage) {
        setView(view, stage);
    }

    public void setView(ManageEmployeesView view, Stage stage) {
        gridPane.add(firstNameTF, 1, 0);
        gridPane.add(lastNameTF, 1, 1);

        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(lastNameLabel, 0, 1);

        stackPane.getChildren().add(removeButton);

        fullPane.setTop(gridPane);
        fullPane.setBottom(stackPane);

        removeButton.setOnAction(e-> {
            users.deleteUser(firstNameTF.getText(), lastNameTF.getText());
            view.setUserPane();

            stage.close();
        });
    }

    @Override
    public Parent getView() {
        // TODO Auto-generated method stub
        return fullPane;
    }
}
