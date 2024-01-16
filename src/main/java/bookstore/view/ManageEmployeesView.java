package bookstore.view;

import bookstore.controllers.UserController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class ManageEmployeesView extends View{

    private final UserController users = new UserController();

    private final BorderPane fullPane = new BorderPane();
    private final VBox userPane = new VBox();
    private final HBox buttonsPane = new HBox();

    private final Button addEmployeeButton = new Button("Add Employee");
    private final Button editEmployeeButton = new Button("Edit Employee");
    private final Button removeEmployeeButton = new Button("Remove employee");
    private final Button getEmployeeButton = new Button("Get Employee Info");

    public ManageEmployeesView() {

        addEmployeeButton.setId("addEmployeeButton");
        editEmployeeButton.setId("editEmployeeButton");
        removeEmployeeButton.setId("removeEmployeeButton");
        getEmployeeButton.setId("getEmployeeButton");
        setView();

        addEmployeeButton.setOnAction(e->{
            Stage stage = new Stage();
            stage.setTitle("addEmployee");
            AddEmployeeView view = new AddEmployeeView(this, stage);

            Scene scene = new Scene(view.getView(), 200, 300);

            stage.setScene(scene);
            stage.show();

        });

        removeEmployeeButton.setOnAction(e->{
            Stage stage = new Stage();
            stage.setTitle("removeEmployee");
            RemoveEmployeeView view = new RemoveEmployeeView(this, stage);

            Scene scene = new Scene(view.getView());

            stage.setScene(scene);
            stage.show();
        });

        editEmployeeButton.setOnAction(e->{
            Stage stage = new Stage();
            stage.setTitle("editEmployee");
            EditEmployeeView view = new EditEmployeeView(this, stage);

            Scene scene = new Scene(view.getView());

            stage.setScene(scene);
            stage.show();
        });

        getEmployeeButton.setOnAction(e->{
            Stage stage = new Stage();
            stage.setTitle("getEmployee");
            GetEmployeeView view = new GetEmployeeView(stage);

            Scene scene = new Scene(view.getView());

            stage.setScene(scene);
            stage.show();
        });
    }

    public void setUserPane() {
        userPane.getChildren().clear();

        users.readUsers();

        for(int i = 0 ; i < users.getUsers().size(); i++) {
            userPane.getChildren().add(new Text("Employee: " + users.getUsers().get(i).getUserInfo()));
        }
    }

    public void setView() {
        buttonsPane.getChildren().add(addEmployeeButton);
        buttonsPane.getChildren().add(editEmployeeButton);
        buttonsPane.getChildren().add(removeEmployeeButton);
        buttonsPane.getChildren().add(getEmployeeButton);

        setUserPane();

        fullPane.setBottom(buttonsPane);
        fullPane.setTop(userPane);


    }

    public Button getAddEmployeeButton() {
        return addEmployeeButton;
    }

    public Button getEditEmployeeButton() {
        return editEmployeeButton;
    }

    public Button getRemoveEmployeeButton() {
        return removeEmployeeButton;
    }
    @Override
    public Parent getView() {
        // TODO Auto-generated method stub
        return fullPane;
    }



}
