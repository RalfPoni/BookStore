package bookstore.controllers;

import java.io.*;
import java.util.ArrayList;

import bookstore.models.Librarian;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bookstore.controllers.UserController;
import bookstore.models.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    private UserController userController;
    private Librarian testUser1;
    private Librarian testUser2;
    private String filename = "testUsers.dat";
    private static File file;

    @BeforeEach
    public void setUp()
    {
        file = new File(filename);

        userController = new UserController();
        testUser1 = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        testUser2 = new Librarian("Bob", "Odenkirk", "password2","bobOD@gmail.com", "+15667553908", 200420);

        userController.setFilename(filename);
    }

    @AfterEach
    public void tearDown()
    {
        if (file != null && file.exists())
        {
            try
            {
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.write("");
                fileWriter.close();

                file.delete();
            }
            catch(IOException e)
            {

            }
        }
    }

    @Test
    public void testAddAndGetUsers()
    {
        userController.addUser(testUser1);
        userController.addUser(testUser2);

        ArrayList<User> users = userController.getUsers();

        assertTrue(users.contains(testUser1));
        assertTrue(users.contains(testUser2));
    }

    @Test
    public void testSetUsers()
    {
        ArrayList<User> users = new ArrayList<User>();

        users.add(testUser1);
        users.add(testUser2);

        userController.setUsers(users);

        assertEquals(users,userController.getUsers());
    }

    @Test
    public void testWriteAndReadUsers()
    {

        userController.addUser(testUser1);
        userController.addUser(testUser2);

        ArrayList<User> initialUsers = userController.getUsers();

        userController.writeUser(testUser2);
        userController.writeUser(testUser1);

        userController = new UserController();
        userController.setFilename(filename);

        userController.readUsers();

        ArrayList<User> readUsers = userController.getUsers();

        assertAll(
                () -> assertEquals(initialUsers.get(0).getUserInfo(), readUsers.get(0).getUserInfo()),
                () -> assertEquals(initialUsers.get(1).getUserInfo(), readUsers.get(1).getUserInfo())

        );
    }

    @Test
    public void testGetSpecificUser()
    {
        userController.writeUser(testUser2);
        userController.writeUser(testUser1);

        User user1 = userController.getUser("John", "Doe");

        assertAll(
                () -> assertEquals(testUser1.getUserInfo(), user1.getUserInfo()),
                () -> assertEquals(testUser1.getEmail(), user1.getEmail()),
                () -> assertEquals(testUser1.getPassword(), user1.getPassword()),
                () -> assertEquals(testUser1.getPhoneNumber(), user1.getPhoneNumber()),
                () -> assertEquals(testUser1.getSalary(), user1.getSalary())
        );
    }

    @Test
    public void testGetUserIndex()
    {
        userController.writeUser(testUser1);
        userController.writeUser(testUser2);


        assertAll(
                () -> assertEquals(0, userController.getUserIndex("John", "Doe")),
                () -> assertEquals(1, userController.getUserIndex("Bob", "Odenkirk")),
                () -> assertEquals(-1, userController.getUserIndex("Enes", "Brucaj"))
        );
    }

    @Test
    public void testDeleteUser()
    {
        userController.addUser(testUser1);
        userController.addUser(testUser2);

        userController.deleteUser("John", "Doe");

        assertNull(userController.getUser("John", "Doe"));
    }

    @Test
    public void testEditUser() {
        userController.addUser(testUser1);

        User updatedUser = new Librarian("Jane", "Doe", "difficultPassword", "janeDoe@gmail.com", "+17405060134",180000);

        userController.editUser(testUser1, updatedUser);

        assertNull(userController.getUser("John", "Doe"));
        assertEquals("janeDoe@gmail.com", userController.getUser("Jane", "Doe").getEmail());
        assertEquals("difficultPassword", userController.getUser("Jane", "Doe").getPassword());
    }

    @Test
    public void testVerifyUser()
    {
        userController.addUser(testUser1);
        userController.addUser(testUser2);

        //both true
        assertTrue(userController.verifyUser("johndoe@yahoo.com", "password1"));
        //email true, password false
        assertFalse(userController.verifyUser("johndoe@yahoo.com", "CuteAndFunny"));
        //email false, password true
        assertFalse(userController.verifyUser("lebronJames@yahoo.com", "password1"));
        //different user, both true
        assertTrue(userController.verifyUser("bobOD@gmail.com", "password2"));
        //not matching email and password
        assertFalse(userController.verifyUser("bobOD@gmail.com", "password1"));
    }

    @Test
    public void testSetAndGetCurrentUser()
    {
        userController.setCurrentUser(testUser1);
        User user = userController.getCurrentUser();

        assertAll(
                () -> assertEquals(testUser1.getUserInfo(), user.getUserInfo()),
                () -> assertEquals(testUser1.getEmail(), user.getEmail()),
                () -> assertEquals(testUser1.getPassword(), user.getPassword()),
                () -> assertEquals(testUser1.getPhoneNumber(), user.getPhoneNumber()),
                () -> assertEquals(testUser1.getSalary(), user.getSalary())
        );

        try
        {
            FileWriter fileWriter = new FileWriter("currentuser.dat");
            fileWriter.write("");
            fileWriter.close();

            new File("currentuser.dat").delete();
        }
        catch(IOException e)
        {

        }
    }


}
