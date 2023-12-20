package bookstore.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements Serializable{

    private static final long serialVersionUID = -3810406382722211499L;
    private String name, author, ISBN, category, supplier;
    private double originalPrice, sellingPrice;
    private int stock;
    private LocalDate datePurchased;
    private double cost;

    public Book(String name, String author, String ISBN, String category, String supplier, double originalPrice, double sellingPrice, int stock, LocalDate datePurchased) {
        if(!(verifyISBN(ISBN))){
            throw new IllegalArgumentException();
        }

        setName(name);
        setAuthor(author);
        setISBN(ISBN);
        setCategory(category);
        setSupplier(supplier);
        setOriginalPrice(originalPrice);
        setSellingPrice(sellingPrice);
        setStock(stock);
        setDatePurchased(datePurchased);

        setCost(originalPrice * stock);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    @Override
    public String toString() {
        return "Book Title: " + name + " | Book Author: " + author + " | Book Category " + category + " | Book ISBN " + ISBN
                + " | Book Supplier: " + supplier + "\nBook Original Price: " + originalPrice + " | Book Selling Price: " + sellingPrice
                + "\nBook Stock: " + stock;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public static boolean verifyISBN(String isbn){
        //RegEx pattern taken from https://howtodoinjava.com/java/regex/java-regex-validate-international-standard-book-number-isbns/
        Pattern isbnPattern = Pattern.compile("^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$" );
        Matcher isbnMatcher = isbnPattern.matcher(isbn);

        if(isbnMatcher.matches()) return true;

        return false;
    }
}
