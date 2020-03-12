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
    private void clearCoinSlot(){
        coinSlot.clear();
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
        clearCoinSlot();
        return response;
    }
    public String purchaseSoftDrinks(String product){
        boolean stock = false;
        boolean enough_coin = false;
        int slot_index=-1;
        Integer inserted_coin = null;
        Integer price = null;
        // Check Stock, Check if inserted enough coins
        for (int i = 0; i < softDrinkSlots.size(); i++) {
            SoftDrinkSlot drinkSlot = softDrinkSlots.get(i);
            if (drinkSlot.getName().equals(product)){
                inserted_coin = this.getCoinSlotValue();
                price = drinkSlot.getPrice();
                if(inserted_coin>=price){
                    if(drinkSlot.checkStock()) {
                        stock = true;
                        slot_index = i;
                    }
                }
                else{
                    return "Insufficient amount! Inserted $" + inserted_coin.toString() + " but needs $" + price.toString() + ".";
                }
            }
        }
        if (!stock)
            return "Out of Stock!";
        //Check if needed changes
        if(inserted_coin>price){
            //Check if enough changes
            if(false){
                return "Insufficient change!";
            }
            else{
                String response = "Success! Paid $" + price.toString() + ". Change:";
                clearCoinSlot();
                return response;
            }
        }
        else{
            softDrinkSlots.get(slot_index).buy();
            clearCoinSlot();
            return "Success! Paid $" + price.toString() + ". No change.";
        }
    }
}
