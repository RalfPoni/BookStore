package bookstore.mocks;

import bookstore.controllers.BillController;
import bookstore.models.Bill;
import java.util.ArrayList;

public class BillControllerMock extends BillController{

    private final ArrayList<Bill> mockedBills;
    private int mockedCounter = 0;

    public BillControllerMock() {
        mockedBills = new ArrayList<>();
        setCounter(mockedCounter);
    }

    @Override
    public int getBillNo()
    {
        return mockedCounter;
    }

    @Override
    public void writeBillNo(int billNo)
    {
        mockedCounter = billNo;
    }

    @Override
    public void writeBill(Bill bill) {
        mockedBills.add(bill);
    }

    @Override
    public ArrayList<Bill> getBills() {
        return mockedBills;
    }

    @Override
    public void createBill(Bill bill) {
        mockedCounter++;
    }
}