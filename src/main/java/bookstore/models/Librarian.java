package bookstore.models;

public class Librarian extends User{

    private static final long serialVersionUID = 6805954880109411830L;

    public Librarian(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {
        super(firstName, lastName, password, email, phoneNumber, salary);

        super.setFilePath("librarians.dat");
        super.setAccessLevel("Librarian");
    }

}
