public class CmdPurchase extends Command {

    @Override
    public String execute(VendingMachine v, String cmdPart) {
        String product = cmdPart;
        // Add the coin to Coin Slot
        return "Purchasing " + product + "... " + v.purchaseSoftDrinks(product);
    }
}
