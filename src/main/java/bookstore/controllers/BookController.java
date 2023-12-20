package bookstore.controllers;

import bookstore.models.Book;

import java.io.*;
import java.util.ArrayList;

public class BookController {

    private ArrayList<Book> books;

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

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("books.dat"));

            for(int i = 0; i < getBooks().size(); i++)
                output.writeObject((Book)(getBooks().get(i)));

            output.writeObject((Book)book);

            addBook(book);

            output.close();

        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public void readBooks() {
        try {
            books.clear();

            File file = new File("books.dat");
            file.createNewFile();

            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));

            while (true)
                addBook((Book)input.readObject());

        } catch(IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public Book getBook(String ISBN) {

        readBooks();

        for(int i = 0; i < books.size(); i++) {
            if(ISBN.equals(books.get(i).getISBN())) {
                return books.get(i);
            }
        }

        return null;
    }

    public void clear() {
        books.clear();
    }
}
