package bookstore.models;

import java.io.Serial;

public class Manager extends User{

    @Serial
    private static final long serialVersionUID = 276862822650424602L;

    public Manager(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {
        super(firstName, lastName, password, email, phoneNumber, salary);
        super.setAccessLevel("Manager");
    }

}
