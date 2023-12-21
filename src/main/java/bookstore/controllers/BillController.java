package bookstore.controllers;

import bookstore.models.Bill;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings({"CallToPrintStackTrace", "InfiniteLoopStatement"})
public class BillController {

    private final UserController users = new UserController();
    private final ArrayList<Bill> bills;
    private Scanner scan;
    private static int counter;
    private String filename = "bills.dat";
    private String counterFilename = "billNumber.dat";
    public void setFileNames(String filename, String counterFilename) {this.filename = filename; this.counterFilename = counterFilename;}
    public BillController() {
        bills = new ArrayList<>();
        setCounter(getBillNo());
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void writeBill(Bill bill) {
        try{

            readBill();

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));

            for (Bill value : bills) {
                output.writeObject(value);
            }


            output.writeObject(bill);

            bills.add(bill);

            output.close();

            createBill(bill);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readBill() {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))){

            bills.clear();

            while (true)
                bills.add((Bill)input.readObject());

        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter1) {
        counter = counter1;
    }

    public void writeBillNo(int counter) {

        try(FileOutputStream output = new FileOutputStream(counterFilename)) {

            try {
                output.write(counter);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getBillNo() {
        try(FileInputStream input = new FileInputStream(counterFilename)) { return input.read();}
        catch(IOException e) { e.printStackTrace(); }

        return 0;
    }

    public void createBill(Bill bill) {
        File newFile = new File("BillNo" + counter + ".txt");
        try {
            if(!newFile.createNewFile()) System.out.println("Bill already exists!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(newFile);

            fileWriter.write(bill.toString());

            writeBillNo(getCounter()+1);

            fileWriter.close();

            setCounter(getCounter()+1);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
