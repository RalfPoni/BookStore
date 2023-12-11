package bookstore;

import bookstore.controllers.UserController;
import bookstore.view.BookView;
import bookstore.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewTest extends Application{

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserController users = new UserController();

        users.readUsers();

        LoginView loginView = new LoginView();



        Scene thisScene = new Scene(loginView.getView());



        primaryStage.setTitle("Test");
        primaryStage.setScene(thisScene);

        primaryStage.show();

        loginView.getLoginButton().setOnAction(e-> {

            boolean verify = users.verifyUser(loginView.getFirstNameTF().getText(), loginView.getLastNameTF().getText(), loginView.getPasswordTF().getText());

            if(verify){

                BookView bookView = new BookView();

                Scene otherScene = new Scene(bookView);

                System.out.println(users.getCurrentUser());
                System.out.println("Success");
                primaryStage.setScene(otherScene);

            }
            else System.out.println("Fail");

        });
    }
}
