package bookstore.mocks;

import bookstore.controllers.UserController;
import bookstore.models.User;

import java.util.ArrayList;

public class UserControllerMock extends UserController {

    private final ArrayList<User> mockedUsers;

    public UserControllerMock() {
        mockedUsers = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        mockedUsers.add(user);
    }

    @Override
    public ArrayList<User> getUsers() {
        return mockedUsers;
    }

    @Override
    public boolean writeUser(User user) {
        return mockedUsers.add(user);
    }

    @Override
    public void deleteUser(String name, String lastName) {
        mockedUsers.removeIf(user -> name.equals(user.getFirstName()) && lastName.equals(user.getLastName()));
    }

    @Override
    public void editUser(User userCompare, User user) {
        int index = mockedUsers.indexOf(userCompare);
        if (index != -1) {
            mockedUsers.set(index, user);
        }
    }

    @Override
    public int getUserIndex(String name, String lastName) {
        for (int i = 0; i < mockedUsers.size(); i++) {
            if (name.equals(mockedUsers.get(i).getFirstName()) && lastName.equals(mockedUsers.get(i).getLastName())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public User getUser(String name, String lastName) {
        for (User user : mockedUsers) {
            if (name.equals(user.getFirstName()) && lastName.equals(user.getLastName())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean verifyUser(String email, String password) {
        return mockedUsers.stream()
                .anyMatch(user -> email.equals(user.getEmail()) && password.equals(user.getPassword()));
    }

    @Override
    public void setCurrentUser(User user){
        currentUser = user;
    }

    @Override
    public User getCurrentUser(){
        return currentUser;
    }

}
