public class Main {
    public static void main(String[] args) {
        VendingMachine v = new VendingMachine();
        v.addCoinToCoinChanger(Integer.valueOf(2));
        v.addCoinToCoinChanger(Integer.valueOf(2));
        v.addCoinToCoinChanger(Integer.valueOf(1));
        v.addSoftDrinkSlot(new SoftDrinkSlot("Cocacola", 4, 1)); // Price: $4, Quantity: 1
        v.addSoftDrinkSlot(new SoftDrinkSlot("Pepsi", 5, 3)); // Price: $5, Quantity: 3
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "10"));
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
        System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));
        System.out.println();
        System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));
        System.out.println();
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
        System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
        System.out.println();
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "10"));
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
        System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
        System.out.println();
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
        System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
        System.out.println();
        System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));
        System.out.println();
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
        System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
        System.out.println();
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
        System.out.println(((Command) new CmdPurchase()).execute(v, "Cocacola"));
        System.out.println();
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
        System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
        System.out.println(((Command) new CmdPurchase()).execute(v, "Cocacola"));
        System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));
    }
}

