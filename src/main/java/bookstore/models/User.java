package bookstore.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public abstract class User implements Serializable{

    @Serial
    private static final long serialVersionUID = -8988107778041280990L;
    private String firstName, lastName, password, email, accessLevel, phoneNumber, filePath;
    private float salary;

    public User(String firstName, String lastName, String password, String email, String phoneNumber, float salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setSalary(salary);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(!verifyName(firstName)) throw new IllegalArgumentException();
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(!verifyName(lastName)) throw new IllegalArgumentException();
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        System.out.println(phoneNumber);
        if(!verifyPhoneNumber(phoneNumber)) throw new IllegalArgumentException();
        this.phoneNumber = phoneNumber;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!verifyEmail(email)) throw new IllegalArgumentException();
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getUserInfo() {
        return firstName + " " + lastName + " " + accessLevel;
    }

    public static boolean verifyName(String name) {
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    }

    public static boolean verifyEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);

        return emailMatcher.matches();
    }

    public static boolean verifyPhoneNumber(String phoneNumber) {
        Pattern phonePattern = Pattern.compile("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$");
        Matcher matcher = phonePattern.matcher(phoneNumber);

        return matcher.matches();
    }

    @Override
    public String toString() {
        return "Employee Name: " + getFirstName() + " " + getLastName() + '\n'
                + "Email: " + getEmail() + '\n' + "Phone number: " + getPhoneNumber()
                + '\n' + "Salary: " + getSalary().toString() + "\nAccess Level: " + getAccessLevel();
    }



}

