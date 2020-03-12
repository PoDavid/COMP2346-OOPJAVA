public class SoftDrinkSlot {
    private String name;
    private int price;
    private int quantity;
    public SoftDrinkSlot(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    /* You may add other non-static properties and methods */
    public String getName(){
        return this.name;
    }
    public boolean checkStock(){
        return this.quantity > 0;
    }
    public int getPrice(){
        return this.price;
    }
    public void buy(){
        if (this.quantity>0)
            this.quantity-=1;
    }

}
