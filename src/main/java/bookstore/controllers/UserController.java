package bookstore.controllers;

import bookstore.models.User;

import java.io.*;
import java.util.ArrayList;
public class UserController {

    private ArrayList<User> users;
    private String filename = "users.dat";
    private User currentUser;

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


    public void writeUser(User user) {

        try {

            readUsers();

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));

            for(int i = 0; i < getUsers().size(); i++)
                output.writeObject(getUsers().get(i));

            output.writeObject(user);

            addUser(user);

            output.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void readUsers() {
        try {
            File file = new File(filename);
            if(!file.createNewFile()) System.out.println("File already exists");

            users.clear();

            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));

            while (true)
                addUser((User)input.readObject());

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentUser(User user) {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("currentuser.dat"))){

            output.writeObject(user);
            currentUser = user;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public User getCurrentUser() {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("currentuser.dat"))){

            return (User)input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public void deleteUser(String name, String lastName) {

        readUsers();

        for(int i = 0; i < users.size(); i++) {
            if(name.equals(users.get(i).getFirstName()) && lastName.equals(users.get(i).getLastName())) {

                users.remove(i);

                System.out.println("Removed");

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
