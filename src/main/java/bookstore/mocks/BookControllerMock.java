package bookstore.mocks;

import bookstore.controllers.BookController;
import bookstore.models.Book;

import java.util.ArrayList;

public class BookControllerMock extends BookController {
    //Mock readbooks and writebooks
    ArrayList<Book> expectedBooks;

    public BookControllerMock(){
        super.setBooks(new ArrayList<>());
        expectedBooks = new ArrayList<>();
    }

    @Override
    public void writeBook(Book book){
        readBooks();
        super.addBook(book);
    }

    @Override
    public void readBooks(){

    }

    @Override
    public Book getBook(String ISBN){
        readBooks();
        for(Book book : super.getBooks())
            if (ISBN.equals(book.getISBN())) return book;

        return null;
    }

    public void expect(Book book) {
        expectedBooks.add(book);
    }

    public boolean verify(){
        return super.getBooks().equals(expectedBooks);
    }



}
