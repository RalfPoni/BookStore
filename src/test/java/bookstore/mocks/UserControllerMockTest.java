package bookstore.mocks;
import bookstore.models.Librarian;
import bookstore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerMockTest {

    private UserControllerMock userControllerMock;
    private Librarian testUser1;
    private Librarian testUser2;
    @BeforeEach
    public void setUp()
    {
        userControllerMock = new UserControllerMock();
        testUser1 = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        testUser2 = new Librarian("Bob", "Odenkirk", "password2","bobOD@gmail.com", "+15667553908", 200420);
    }

    @Test
    public void testAddUser()
    {
        userControllerMock.addUser(testUser1);

        assertEquals(1, userControllerMock.getUsers().size());
        assertTrue(userControllerMock.getUsers().contains(testUser1));
    }

    @Test
    public void testWriteUser()
    {
        userControllerMock.writeUser(testUser2);

        assertEquals(1, userControllerMock.getUsers().size());
        assertTrue(userControllerMock.getUsers().contains(testUser2));
    }

    @Test
    public void testDeleteUser()
    {
        userControllerMock.addUser(testUser1);

        assertEquals(1, userControllerMock.getUsers().size());

        userControllerMock.deleteUser("John", "Doe");

        assertEquals(0, userControllerMock.getUsers().size());
    }

    @Test
    public void testVerifyUser()
    {

        userControllerMock.addUser(testUser1);
        userControllerMock.addUser(testUser2);

        assertTrue(userControllerMock.verifyUser("johndoe@yahoo.com", "password1"));
        assertFalse(userControllerMock.verifyUser("invalid@gmail.com", "invalidpassword"));
    }

    @Test
    public void testEditUser()
    {
        userControllerMock.addUser(testUser1);

        assertEquals("password1", userControllerMock.getUsers().get(0).getPassword());

        Librarian editedUser = new Librarian("John", "Doe", "newpassword", "johndoe@yahoo.com", "+12514968166",230920);
        userControllerMock.editUser(testUser1, editedUser);

        assertEquals("newpassword", userControllerMock.getUsers().get(0).getPassword());
    }

    @Test
    public void testGetUserIndex()
    {
        userControllerMock.addUser(testUser1);
        userControllerMock.addUser(testUser2);

        assertEquals(1, userControllerMock.getUserIndex("Bob", "Odenkirk"));
        assertEquals(-1, userControllerMock.getUserIndex("Patrick", "Death"));
    }

    @Test
    public void testGetUser()
    {
        userControllerMock.addUser(testUser1);
        userControllerMock.addUser(testUser2);

        assertEquals(testUser1, userControllerMock.getUser("John", "Doe"));
        assertNull(userControllerMock.getUser("Spongebob", "Killer"));
    }

    @Test
    public void testListToFile()
    {

        assertDoesNotThrow(() -> userControllerMock.listToFile());
    }
}