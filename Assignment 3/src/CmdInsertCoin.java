public class CmdInsertCoin extends Command{
    @Override
    public String execute(VendingMachine v, String cmdPart) {
        Integer coin = Integer.valueOf(cmdPart);
        // Add the coin to Coin Slot
        Integer sum = v.addCoinToCoinSlot(coin);
        return "Inserted a $" + cmdPart + " coin. $" + sum.toString() + " in Total.";
    }
}
