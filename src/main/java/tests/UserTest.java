package tests;

import bookstore.models.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bookstore.models.User;
public class UserTest {

    @ParameterizedTest
    @CsvSource({
            "M",
            "Joey",
            "Andy Won",
            "MacArthur",
            "ABCTOPLKNILKPONTRKJWEQW"
    })
    public void test_verifyName_rightName(String name){
        Assertions.assertTrue(User.verifyName(name));
    }

    @ParameterizedTest
    @CsvSource({
            "1",
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
            "1",
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
}
