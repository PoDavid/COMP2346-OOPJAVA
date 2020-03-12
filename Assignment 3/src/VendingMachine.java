import java.util.ArrayList;
import java.util.Collections;

/**
 * The VendingMachine class, used to model a vending machine. The machine allows customers to insert coins,
 * purchase a product and reject inserted coins.
 * @author Po Yat Ching David UID:3035372098
 */
public class VendingMachine {
    private ArrayList<Integer> coinChanger;
    private ArrayList<Integer> coinSlot;
    private ArrayList<SoftDrinkSlot> softDrinkSlots;

    /**
     * Instantiates a new Vending machine.
     * With 3 components, coinChanger, coinSlot and softDrinkSlots
     */
    public VendingMachine() {
        coinChanger = new ArrayList<Integer>();
        coinSlot = new ArrayList<Integer>();
        softDrinkSlots = new ArrayList<SoftDrinkSlot>();
    }

    /**
     * Add coin to coin changer.
     *
     * @param c The face value of the coin to be added to the coin changer.
     */
    public void addCoinToCoinChanger(Integer c) {
        coinChanger.add(c);
    }

    /**
     * Add new soft drink slot.
     *
     * @param s the SoftDrinkSlot object to be added to the ArrayList.
     */
    public void addSoftDrinkSlot(SoftDrinkSlot s) {
        softDrinkSlots.add(s);
    }

    /**
     * Add coin to coin slot when customer insert a coin.
     *
     * @param c the face value of the inserted coin.
     * @return the total value of the coins currently in the coinSlot.
     */
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
    private String giveChange(Integer amount){
        ArrayList<Integer> changesBuffer = new ArrayList<Integer>();
        Collections.sort(coinChanger);
        Integer coin;
        for(int i=coinChanger.size()-1;i>=0;i--){
            coin = coinChanger.get(i);
            if (coin<=amount && amount-coin>=0){
                coinChanger.remove(i);
                changesBuffer.add(coin);
                amount-=coin;
            }
        }
        if(amount==0){
            String response=null;
            Collections.sort(changesBuffer);
            for(Integer change:changesBuffer){
                if(response==null)
                    response=" $" + change.toString() + ",";
                else
                    response+=" $" + change.toString() + ",";
            }
            response=response.substring(0, response.length() - 1) + ".";
            return response;
        }
        else
            return "Insufficient change!";
    }

    /**
     * Reject all coins from the coin slot.
     *
     * @return the response of the machine, the value of coins and the total value rejected.
     */
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

    /**
     * Purchase soft drink with the given product name,
     * if stock is available and enough coins inserted and enough changes
     *
     * @param product the name of the product
     * @return the response of the machine, either successful with/without changes or insufficient amount of coins
     * or insufficient changes.
     */
    public String purchaseSoftDrinks(String product){
        boolean stock = false;
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
            return "Out of stock!";
        //Check if needed changes
        if(inserted_coin>price){
            //Gives the changes if possible
            String change_response = giveChange(inserted_coin-price);
            if (change_response.equals("Insufficient change!"))
                return "Insufficient change!";
            else{
                String response = "Success! Paid $" + inserted_coin.toString() + ". Change:" + change_response;
                coinChanger.addAll(coinSlot);
                coinSlot.clear();
                softDrinkSlots.get(slot_index).buy();
                return response;
            }
        }
        else{
            softDrinkSlots.get(slot_index).buy();
            coinChanger.addAll(coinSlot);
            coinSlot.clear();
            return "Success! Paid $" + inserted_coin.toString() + ". No change.";
        }
    }
}
