package bookstore.models;

import java.io.Serial;

public class Administrator extends User{


    @Serial
    private static final long serialVersionUID = 2260639389319881851L;


    public Administrator(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {
        super(firstName, lastName, password, email, phoneNumber, salary);
        super.setAccessLevel("Administrator");
    }

}
