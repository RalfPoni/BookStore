package bookstore.system;


import bookstore.ViewTest;
import bookstore.controllers.BookController;
import bookstore.controllers.UserController;
import bookstore.models.Administrator;
import bookstore.models.Book;
import bookstore.models.Librarian;
import bookstore.models.User;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminSystemTest extends ApplicationTest {
    private static File file;

    private UserController userController;
    @Override
    public void start(Stage stage) throws Exception {
        new ViewTest().start(stage);
    }


    @BeforeEach
    public void setUp() {
        file = new File("users.dat");

        userController = new UserController();
        clickOn("#emailTF").write("admin@yahoo.com");
        clickOn("#passwordTF").write("password1");
        clickOn("#loginButton");

        WaitForAsyncUtils.waitForFxEvents();
    }
    @AfterEach
    public void tearDown()
    {
        if (file != null && file.exists())
        {
            try
            {
                FileWriter fileWriter = new FileWriter("users.dat");
                fileWriter.write("");
                fileWriter.close();

                userController.writeUser(new Administrator("Bob", "Odenkirk", "password1","admin@yahoo.com", "+15667553908", 200420));
                userController.writeUser(new Librarian("Power", "Yea", "password1","power@gmail.com", "+15667553908", 200420));
                file.delete();
            }
            catch(IOException ignored)
            {

            }
        }

    }

    @Test
    public void testAddEmployeeSuccessfully()
    {
        Librarian testUser = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#addEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("addEmployee"), WindowMatchers.isShowing());

        clickOn("#nameTF").write("John");
        clickOn("#lastNameTF").write("Doe");
        clickOn("#passwordTF").write("password1");
        clickOn("#emailTF").write("johndoe@yahoo.com");
        clickOn("#phoneNumberTF").write("+12514968166");
        clickOn("#salaryTF").write("230920.0");
        clickOn("#accessLevelTF").write("Librarian");

        clickOn("#addEmployeeButton2");

        userController.readUsers();

        System.out.print(userController.getUsers());

        assertEquals(3, userController.getUsers().size());
        User addedUser = userController.getUsers().get(userController.getUsers().size() - 1);

        assertAll(
                () -> assertEquals(testUser.getFirstName(), addedUser.getFirstName()),
                () -> assertEquals(testUser.getLastName(), addedUser.getLastName()),
                () -> assertEquals(testUser.getPassword(), addedUser.getPassword()),
                () -> assertEquals(testUser.getEmail(), addedUser.getEmail()),
                () -> assertEquals(testUser.getPhoneNumber(), addedUser.getPhoneNumber()),
                () -> assertEquals(testUser.getSalary(), addedUser.getSalary()),
                () -> assertEquals(testUser.getAccessLevel(), addedUser.getAccessLevel())
        );




    }
    @Test
    public void testAddEmployeeFail()
    {
        Librarian testUser = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#addEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("addEmployee"), WindowMatchers.isShowing());

        //Invalid access level
        clickOn("#nameTF").write("John");
        clickOn("#lastNameTF").write("Doe");
        clickOn("#passwordTF").write("password1");
        clickOn("#emailTF").write("johndoe@yahoo.com");
        clickOn("#phoneNumberTF").write("+12514968166");
        clickOn("#salaryTF").write("230920.0");
        clickOn("#accessLevelTF").write("Invalid");

        clickOn("#addEmployeeButton2");

        userController.readUsers();

        assertEquals(2, userController.getUsers().size());




    }

    @Test
    public void testEditEmployeeSuccessfully()
    {
        Librarian testUser = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        userController.writeUser(testUser);

        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#editEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("editEmployee"), WindowMatchers.isShowing());

        clickOn("#firstNameTF").write("John");
        clickOn("#lastNameTF").write("Doe");
        clickOn("#editEmployee");

        WaitForAsyncUtils.waitForFxEvents();

        clickOn("#nameTF").write("Jane");
        clickOn("#lastNameEditTF").write("Doe");
        clickOn("#passwordTF").write("betterPassword");
        clickOn("#emailTF").write("johndoe@gmail.com");
        clickOn("#phoneNumberTF").write("+12514968150");
        clickOn("#salaryTF").write("230920.0");
        clickOn("#accessLevelTF").write("Librarian");

        clickOn("#editButton");

        userController.readUsers();

        assertEquals(3, userController.getUsers().size());
        User editedUser = userController.getUsers().get(userController.getUsers().size() - 1);

        assertAll(
                () -> assertEquals("Jane", editedUser.getFirstName()),
                () -> assertEquals(testUser.getLastName(), editedUser.getLastName()),
                () -> assertEquals("betterPassword", editedUser.getPassword()),
                () -> assertEquals("johndoe@gmail.com", editedUser.getEmail()),
                () -> assertEquals("+12514968150", editedUser.getPhoneNumber()),
                () -> assertEquals(testUser.getSalary(), editedUser.getSalary()),
                () -> assertEquals(testUser.getAccessLevel(), editedUser.getAccessLevel())
        );




    }
    @Test
    public void testEditEmployeeFail()
    {
        Librarian testUser = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        userController.writeUser(testUser);

        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#editEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("editEmployee"), WindowMatchers.isShowing());

        clickOn("#firstNameTF").write("John");
        clickOn("#lastNameTF").write("Doe");
        clickOn("#editEmployee");

        WaitForAsyncUtils.waitForFxEvents();

        clickOn("#nameTF").write("Jane");
        clickOn("#lastNameEditTF").write("Doe");
        clickOn("#passwordTF").write("betterPassword");
        clickOn("#emailTF").write("johndoe@gmail.com");
        clickOn("#phoneNumberTF").write("+12514968150");
        clickOn("#salaryTF").write("230920.0");
        //invalid access level
        clickOn("#accessLevelTF").write("Invalid");

        clickOn("#editButton");

        userController.readUsers();

        assertEquals(3, userController.getUsers().size());
        User editedUser = userController.getUsers().get(userController.getUsers().size() - 1);

        assertAll(
                () -> assertEquals(testUser.getFirstName(), editedUser.getFirstName()),
                () -> assertEquals(testUser.getLastName(), editedUser.getLastName()),
                () -> assertEquals(testUser.getPassword(), editedUser.getPassword()),
                () -> assertEquals(testUser.getEmail(), editedUser.getEmail()),
                () -> assertEquals(testUser.getPhoneNumber(), editedUser.getPhoneNumber()),
                () -> assertEquals(testUser.getSalary(), editedUser.getSalary()),
                () -> assertEquals(testUser.getAccessLevel(), editedUser.getAccessLevel())
        );


    }

    @Test
    public void testRemoveEmployeeSuccessfully()
    {
        Librarian testUser = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        userController.writeUser(testUser);

        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#removeEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("removeEmployee"), WindowMatchers.isShowing());

        //Invalid first name
        clickOn("#firstNameTF").write("Justin");
        clickOn("#lastNameTF").write("Doe");


        clickOn("#removeButton");

        userController.readUsers();

        System.out.print(userController.getUsers());

        assertEquals(3, userController.getUsers().size());
        assertNotNull(userController.getUser("John","Doe"));

    }
    @Test
    public void testRemoveEmployeeFail()
    {
        Librarian testUser = new Librarian("John", "Doe", "password1", "johndoe@yahoo.com", "+12514968166",230920);
        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#addEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("addEmployee"), WindowMatchers.isShowing());

        clickOn("#nameTF").write("John");
        clickOn("#lastNameTF").write("Doe");
        clickOn("#passwordTF").write("password1");
        clickOn("#emailTF").write("johndoe@yahoo.com");
        clickOn("#phoneNumberTF").write("+12514968166");
        clickOn("#salaryTF").write("230920.0");
        clickOn("#accessLevelTF").write("Librarian");

        clickOn("#addEmployeeButton");

        userController.readUsers();

        assertEquals(2, userController.getUsers().size());




    }
    @Test
    public void testGetEmployeeSuccessfully()
    {

        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#getEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("getEmployee"), WindowMatchers.isShowing());

        clickOn("#firstNameTF").write("Power");
        clickOn("#lastNameTF").write("Yea");

        clickOn("#getUserButton");

        String displayedUserText = lookup("#userInformationText").queryText().getText();

        assertEquals(displayedUserText, "0.0");



    }

    @Test
    public void testGetEmployeeFail()
    {

        assertTrue(lookup("#manageEmployeesButton").tryQuery().isPresent());
        clickOn("#manageEmployeesButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("ManageEmployees"), WindowMatchers.isShowing());

        clickOn("#getEmployeeButton");
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(window("getEmployee"), WindowMatchers.isShowing());

        //Invalid Credentials
        clickOn("#firstNameTF").write("John");
        clickOn("#lastNameTF").write("Doe");

        clickOn("#getUserButton");

        assertNull(userController.getUser("John", "Doe"));

    }


}
