package bookstore.models;

public class Manager extends User{

    private static final long serialVersionUID = 276862822650424602L;

    public Manager(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {
        super(firstName, lastName, password, email, phoneNumber, salary);

        super.setFilePath("managers.dat");
        super.setAccessLevel("Manager");
    }

}
