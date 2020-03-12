import java.util.ArrayList;
public class VendingMachine {
    private ArrayList<Integer> coinChanger;
    private ArrayList<Integer> coinSlot;
    private ArrayList<SoftDrinkSlot> softDrinkSlots;
    public VendingMachine() {
    coinChanger = new ArrayList<Integer>();
    coinSlot = new ArrayList<Integer>();
    softDrinkSlots = new ArrayList<SoftDrinkSlot>();
    }
    public void addCoinToCoinChanger(Integer c) {
    coinChanger.add(c);
    }
    public void addSoftDrinkSlot(SoftDrinkSlot s) {
    softDrinkSlots.add(s);
    }
/* You may add other non-static properties and methods */
}
