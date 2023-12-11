module com.example.bookstore2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookstore2 to javafx.fxml;
    exports com.example.bookstore2;
}