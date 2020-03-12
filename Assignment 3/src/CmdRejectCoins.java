public class CmdRejectCoins extends Command{
    @Override
    public String execute(VendingMachine v, String cmdPart) {
        // Reject All Coin From the Coin Slot
        return v.rejectCoinFromCoinSlot();
    }
}
