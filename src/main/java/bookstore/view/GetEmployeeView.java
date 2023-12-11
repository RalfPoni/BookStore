package bookstore.view;

import bookstore.controllers.BillController;
import bookstore.controllers.UserController;
import bookstore.models.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GetEmployeeView extends View{

    UserController users = new UserController();
    BillController bills = new BillController();

    BorderPane fullPane = new BorderPane();

    GridPane gridPane = new GridPane();

    StackPane stackPane = new StackPane();
    VBox userPane = new VBox();

    TextField firstNameTF = new TextField();
    TextField lastNameTF = new TextField();

    Label firstNameLabel = new Label("First Name:");
    Label lastNameLabel = new Label("Last Name:");

    Button getUserButton = new Button("Get User");

    public GetEmployeeView(Stage stage) {
        setView(stage);
    }

    public void setView(Stage stage) {

        double stats = 0;
        Text statText = new Text("");

        bills.readBill();

        gridPane.add(firstNameTF, 1, 0);
        gridPane.add(lastNameTF, 1, 1);
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(lastNameLabel, 0, 1);

        stackPane.getChildren().add(getUserButton);

        fullPane.setTop(gridPane);
        fullPane.setBottom(stackPane);

        getUserButton.setOnAction(e->{
            userPane.getChildren().add(new Text(users.getUser(firstNameTF.getText(), lastNameTF.getText()).toString()));

            bills.readBill();

            System.out.println(bills.getBills().get(0).getUser());

            //statText.setText(getTotalSold(users.getUser(firstNameTF.getText(), lastNameTF.getText()).toString());

            statText.setText(String.valueOf(getTotalSold(users.getUser(firstNameTF.getText(), lastNameTF.getText()))));

            userPane.getChildren().add(statText);

            stage.setScene(new Scene(userPane));


        });
    }

    public double getTotalSold(User user) {
        bills.readBill();

        double sum = 0;

        System.out.println(bills.getBills().size());

        for(int i = 0; i < bills.getBills().size(); i++){

            if(bills.getBills().get(i).getUser().equals(user)) {

                System.out.println("Found");

                sum+=sum+bills.getBills().get(i).getTotalPrice();
            }
        }

        return sum;
    }

    @Override
    public Parent getView() {
        // TODO Auto-generated method stub
        return fullPane;
    }
}
