package bookstore.models;

import bookstore.models.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.BeforeEach;

import bookstore.models.User;
@SuppressWarnings("CodeBlock2Expr")
public class UserTest {

    private Librarian user;
    private Administrator user2;

    @BeforeEach
    public void setUp() {
        user = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        user2 = new Administrator("Bob", "Odenkirk", "password2","bobOD@gmail.com", "+15667553908", 200420);
    }

    @Test
    public void testGetFirstName() {
        Assertions.assertEquals("John", user.getFirstName());
        Assertions.assertEquals("Bob", user2.getFirstName());
    }

    @Test
    public void testGetLastName() {
        Assertions.assertEquals("Doe", user.getLastName());
        Assertions.assertEquals("Odenkirk", user2.getLastName());
    }

    @Test
    public void testGetPhoneNumber() {
        Assertions.assertEquals("+12514968166", user.getPhoneNumber());
        Assertions.assertEquals("+15667553908", user2.getPhoneNumber());
    }

    @Test
    public void testGetEmail() {
        Assertions.assertEquals("johndoe@yahoo.com", user.getEmail());
        Assertions.assertEquals("bobOD@gmail.com", user2.getEmail());
    }

    @Test
    public void testGetAccessLevel() {
        Assertions.assertEquals("Librarian", user.getAccessLevel());
        Assertions.assertEquals("Administrator", user2.getAccessLevel());
    }

    @Test
    public void testGetSalary() {
        Assertions.assertEquals(230920, user.getSalary());
        Assertions.assertEquals(200420, user2.getSalary());
    }

    @Test
    public void testToString()
    {
        String expected = "Employee Name: John Doe\n" +
                "Email: johndoe@yahoo.com\n" +
                "Phone number: +12514968166\n" +
                "Salary: 230920.0\n" +
                "Access Level: Librarian";
        Assertions.assertEquals(expected, user.toString());

        expected = "Employee Name: Bob Odenkirk\n" +
                "Email: bobOD@gmail.com\n" +
                "Phone number: +15667553908\n" +
                "Salary: 200420.0\n" +
                "Access Level: Administrator";
        Assertions.assertEquals(expected, user2.toString());
    }

    @Test
    public void testGetSetFilepath()
    {
        String filepath = "filepath.txt";
        user.setFilePath(filepath);
        Assertions.assertEquals(filepath, user.getFilePath());
    }

    @Test
    public void testGetPassword()
    {
        Assertions.assertEquals("password1", user.getPassword());
        Assertions.assertEquals("password2", user2.getPassword());

    }

    @Test
    public void testGetUserInfo()
    {
        String expected = "John Doe Librarian";
        Assertions.assertEquals(expected, user.getUserInfo());
        expected = "Bob Odenkirk Administrator";
        Assertions.assertEquals(expected, user2.getUserInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "M",
            "Joey",
            "Andy Won",
            "MacArthur",
            "jay",
            "ABCTOPLKNILKPONTRKJWEQW"
    })
    public void test_verifyName_rightName(String name){
        Assertions.assertTrue(User.verifyName(name));
    }

    @ParameterizedTest
    @CsvSource({
            "Ke;",
            "sl1ck",
            "ndtkpgstndndtkpgstnnndtkoooop"
    })
    public void test_verifyName_wrongName(String name) {
        Assertions.assertFalse(User.verifyName(name));
    }

    @ParameterizedTest
    @CsvSource({
            "joe@gmail.com",
            "patrick@yahoo.com",
            "29od@z93.19.ww",
            "vaux@epoka.edu.al",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaa.aaaaaa.aaa",
            "H@P.GA"
    })
    public void test_verifyEmail_rightEmail(String email){
        Assertions.assertTrue(User.verifyEmail(email));
    }

    @ParameterizedTest
    @CsvSource({
            "@gmail.com",
            "google.com",
            "bradley",
            "walrus@place.o",
            "caligula@horse",
            "michael@.com",
            "tony@montana.ww3",
            "Mat/ty@gmail.com"
    })
    public void test_verifyEmail_wrongEmail(String email) {
        Assertions.assertFalse(User.verifyEmail(email));
    }

    @ParameterizedTest
    @CsvSource({
            "0691234567",
            "+355-123-4567",
            "355 123 4567",
            "+(355) 123 4567",
            "+355 691234567"
    })
    public void test_verifyPhoneNumber_rightNumber(String phone){
        Assertions.assertTrue(User.verifyPhoneNumber(phone));
    }

    @ParameterizedTest
    @CsvSource({
            "+(355) (123) 4567",
            "069123456",
            "355 1b3 4567",
            "+355-55-51234",
            "=355 123 4567",
            "+355 0691234567"
    })
    public void test_verifyPhoneNumber_wrongNumber(String phone) {
        Assertions.assertFalse(User.verifyPhoneNumber(phone));
    }

    //  public User(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {
    @ParameterizedTest
    @CsvSource({
            "Swen, Nater, swensworld, swennater@gmail.com, 069 123 4567, 200.43",
            "Danny, Granger, Dagger, dg@workplace.thing.org, +(355) 421 9999, 1939939",
            "george mikan, Miken, lesstalk, gmm@gmail.com, 069 123 2345, 12",
            "Patty, Mills, wash wash, patty@gmail.com, 123456789100, 55"
    })
    public void test_userConstructor_allGood(String firstName, String lastName, String password, String email, String phoneNumber, float salary){
        Assertions.assertDoesNotThrow(() -> {
            new Librarian(firstName, lastName, password, email, phoneNumber, salary);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "1, Nater, swensworld, swennater@gmail.com, 069 123 4567, 200.43",
            "Danny, Gran/ger, Dagger, dg@workplace.thing.org, +(355) 421 9999, 1939939",
            "george mikan, Miken, lesstalk, gmm@gmail.m, 069 123 2345, 12",
            "Patty, Mills, wash wash, patty@gmail.com, 1234b456789, 55"
    })
    public void test_userConstructor_wrongParameters(String firstName, String lastName, String password, String email, String phoneNumber, float salary){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Librarian(firstName, lastName, password, email, phoneNumber, salary);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "Swen, Nater, swensworld, swennater@gmail.com, 069 123 4567, 200.43",
            "Danny, Granger, Dagger, dg@workplace.thing.org, +(355) 421 9999, 1939939",
            "george mikan, Miken, lesstalk, gmm@gmail.com, 069 123 2345, 12",
            "Patty, Mills, wash wash, patty@gmail.com, 123456789100, 55"
    })
    public void test_managerConstructor_allGood(String firstName, String lastName, String password, String email, String phoneNumber, float salary){
        Assertions.assertDoesNotThrow(() -> {
            new Manager(firstName, lastName, password, email, phoneNumber, salary);
        });
    }

}
