package bookstore.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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
        setTotalPrice((double)quantity * itemPrice);

        setDate(date);
        this.user = user;


    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        if(!(Book.verifyISBN(ISBN))){
            throw new IllegalArgumentException();
        }
        ISBN = iSBN;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
        this.date = date;
    }
}
