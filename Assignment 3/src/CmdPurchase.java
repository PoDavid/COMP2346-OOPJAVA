/**
 * The CmdPurchase class, used to execute the Purchase command and return the result of the command.
 *
 * @author Po Yat Ching David UID:3035372098
 */
public class CmdPurchase extends Command {
    @Override
    public String execute(VendingMachine v, String cmdPart) {
        String product = cmdPart;
        // Purchase the product
        return "Purchasing " + product + "... " + v.purchaseSoftDrinks(product);
    }
}
