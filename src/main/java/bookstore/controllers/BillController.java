package bookstore.controllers;

import bookstore.models.Bill;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BillController {

    private UserController users = new UserController();
    private ArrayList<Bill> bills;
    private Scanner scan;
    private static int counter;


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

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("bills.dat"));

            for(int i = 0; i < bills.size(); i++) {
                output.writeObject(bills.get(i));
            }


            output.writeObject(bill);

            bills.add(bill);

            output.close();

            createBill(bill);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readBill() {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("bills.dat"))){

            bills.clear();

            while (true)
                bills.add((Bill)input.readObject());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

        try {
            FileOutputStream output = new FileOutputStream("billNumber.dat");
            try {
                output.write(counter);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getBillNo() {
        try {
            FileInputStream input = new FileInputStream("billNumber.dat");

            return input.read();
        } catch(IOException e) {
            System.out.println(e);
        }

        return 0;
    }

    public void createBill(Bill bill) {
        File newFile = new File("BillNo" + counter + ".txt");
        try {
            newFile.createNewFile();
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
