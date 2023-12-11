package bookstore.models;

import java.io.Serializable;

public abstract class User implements Serializable{

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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    @Override
    public String toString() {
        return "Employee Name: " + getFirstName() + " " + getLastName() + '\n'
                + "Email: " + getEmail() + '\n' + "Phone number: " + getPhoneNumber()
                + '\n' + "Salary: " + getSalary().toString() + "\nAccess Level: " + getAccessLevel();
    }



}

