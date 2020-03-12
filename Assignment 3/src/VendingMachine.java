import java.util.ArrayList;
import java.util.Collections;

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
    public Integer addCoinToCoinSlot(Integer c) {
        coinSlot.add(c);
        Collections.sort(coinSlot);
        return getCoinSlotValue();
    }
    private Integer getCoinSlotValue(){
        Integer sum=0;
        for (Integer coin : coinSlot){
            sum+=coin;
        }
        return sum;
    }
    public String rejectCoinFromCoinSlot(){
        String response;
        if (coinSlot.size()==0){
            response = "Rejected no coin!";
        }
        else{
            response = "Rejected";
            for(Integer coin : coinSlot) {
                response += " $"+coin+",";
            }
            Integer total = getCoinSlotValue();
            response = response.substring(0,response.length()-1) + ". $" + "" + total.toString() + " in Total.";
        }
        coinSlot.clear();
        return response;
    }
}
