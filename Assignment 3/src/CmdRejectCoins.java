/**
 * The CmdRejectCoins class, used to execute the Reject Coin command and return the result of the command.
 *
 * @author Po Yat Ching David UID:3035372098
 */
public class CmdRejectCoins extends Command{

    @Override
    public String execute(VendingMachine v, String cmdPart) {
        // Reject All Coin From the Coin Slot
        return v.rejectCoinFromCoinSlot();
    }
}
