package bookstore.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.DateTimeException;
public class Bill implements Serializable{


    @Serial
    private static final long serialVersionUID = -6259039226126655541L;
    private String ISBN;
    private int quantity;
    private double totalPrice;
   //private static final int counter = 0;
    private LocalDate date;
    User user;

    //File billNumber = new File("billNumber.txt");

    public Bill(String ISBN, int quantity, double itemPrice, User user, LocalDate date) {



        setISBN(ISBN);
        setQuantity(quantity);
        setTotalPrice(quantity, itemPrice);

        setDate(date);
        this.user = user;


    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0) throw new IllegalArgumentException();
        this.quantity = quantity;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        if(!(Book.verifyISBN(iSBN))){
            throw new IllegalArgumentException();
        }
        ISBN = iSBN;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int quantity, double itemPrice) {
        if(itemPrice < 0 || quantity < 0) throw new IllegalArgumentException();

        this.totalPrice = (double)quantity*itemPrice;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




    @Override
    public String toString() {
        return getISBN() + '\n' + getQuantity() + '\n' + getTotalPrice() + '\n' + user.getFirstName() + '\n' + user.getLastName() + '\n' + getDate().toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        try {
            LocalDate.parse(date.toString()); // Attempt to parse the date to check validity
            if (date.isAfter(LocalDate.now())) {
                throw new DateTimeException("Date cannot be in the future");
            } else {
                this.date = date;
            }
        } catch (DateTimeException e) {
            throw new DateTimeException("Invalid date format");
        }

    }
}
