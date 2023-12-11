package bookstore.models;

public class Administrator extends User{


    private static final long serialVersionUID = 2260639389319881851L;


    public Administrator(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {
        super(firstName, lastName, password, email, phoneNumber, salary);

        super.setFilePath("admins.dat");
        super.setAccessLevel("Administrator");
    }

}
