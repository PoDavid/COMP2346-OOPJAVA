public class CmdPurchase extends Command {
    @Override
    public String execute(VendingMachine v, String cmdPart) {
        String product = cmdPart;
        // Purchase the product
        return "Purchasing " + product + "... " + v.purchaseSoftDrinks(product);
    }
}
