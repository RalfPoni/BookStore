package bookstore.controllers;

import bookstore.models.User;

import java.io.*;
import java.util.ArrayList;
public class UserController {

    private ArrayList<User> users;
    private String filename = "users.dat";
    protected User currentUser;

    public UserController() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setFilename(String filename) { this.filename = filename; }


    public boolean writeUser(User user) {
        readUsers();

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (User u : getUsers()) {
                output.writeObject(u);
            }
            output.writeObject(user);

            addUser(user);

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void readUsers() {
        try {
            File file = new File(filename);
            if(!file.createNewFile()) System.out.println("Failed");;

            users.clear();

            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));

            while (true)
                addUser((User)input.readObject());

        } catch(IOException | ClassNotFoundException ignored) {
        }
    }

    public void setCurrentUser(User user) {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("currentuser.dat"))){

            output.writeObject(user);
            currentUser = user;

        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    public User getCurrentUser() {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("currentuser.dat"))){

            return (User)input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
        }

        return null;
    }

    public void deleteUser(String name, String lastName) {

        readUsers();

        for(int i = 0; i < users.size(); i++) {
            if(name.equals(users.get(i).getFirstName()) && lastName.equals(users.get(i).getLastName())) {

                users.remove(i);


                break;
            }
        }

        listToFile();

    }

    public void editUser(User userCompare, User user) {
        for(int i = 0; i < users.size(); i++) {
            if(userCompare.equals(users.get(i)))
                users.set(i, user);
        }

        listToFile();
    }

    public int getUserIndex(String name, String lastName) {

        readUsers();

        for(int i = 0; i < users.size(); i++) {
            if(name.equals(users.get(i).getFirstName()) && lastName.equals(users.get(i).getLastName())) {
                return i;
            }
        }

        return -1;
    }

    public User getUser(String name, String lastName) {
        readUsers();

        for (User user : users) {
            if (name.equals(user.getFirstName()) && lastName.equals(user.getLastName())) {
                return user;
            }
        }

        return null;
    }

    public boolean verifyUser(String email, String password) {

        for(int i = 0; i < getUsers().size(); i++) {
            if(email.equals(getUsers().get(i).getEmail()) && password.equals(getUsers().get(i).getPassword())) {
                setCurrentUser(getUsers().get(i));
                return true;
            }
        }

        return false;

    }

    public void listToFile() {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))){

            for (User user : users) {
                output.writeObject(user);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch
        }
    }
}
