package bookstore.controllers;

import bookstore.models.Book;
import bookstore.models.User;

import java.io.*;
import java.util.ArrayList;

public class BookController {

    private ArrayList<Book> books;
    private String filename = "books.dat";

    public BookController() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setFilename(String filename) {this.filename = filename;}

    public String generateISBN() {
        char[] randomISBN = new char[10];

        for(int i = 0; i < 10; i++) {
            int randomValue = (int)Math.floor(Math.random() * (57 - 48 + 1) + 48);

            randomISBN[i] = (char)randomValue;
        }

        String finalVal = String.valueOf(randomISBN);

        System.out.println(finalVal);

        return finalVal;
    }

    public void writeBook(Book book) {
        try {

            readBooks();

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));

            for(int i = 0; i < getBooks().size(); i++)
                output.writeObject(getBooks().get(i));

            output.writeObject(book);

            addBook(book);

            output.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void readBooks() {
        try {
            books.clear();

            File file = new File(filename);
            if(!file.createNewFile()){
                System.out.println("File already exists");
            }

            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));

            while (true)
                addBook((Book)input.readObject());

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Book getBook(String ISBN) {


        readBooks();

        for (Book book : books) {
            System.out.print("ISBN: " + book.getISBN() + "ISBN OTHER: " + ISBN);
            if (ISBN.equals(book.getISBN())) {
                System.out.print("in loop");
                return book;
            }
        }

        return null;
    }

    public void listToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {

            for (Book book : books) {
                output.writeObject(book);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clear() {
        books.clear();
    }
}
