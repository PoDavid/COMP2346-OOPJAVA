/**
 * The SoftDrinkSlot class, used to model the soft drink slot in the vending machine
 * @author Po Yat Ching David UID:3035372098
 */
public class SoftDrinkSlot {
    private String name;
    private int price;
    private int quantity;

    /**
     * Instantiates a new Soft drink slot.
     *
     * @param name     the name of the product
     * @param price    the price of the product
     * @param quantity the quantity of the product remaining in the machine
     */
    public SoftDrinkSlot(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Get the product name.
     *
     * @return the name of the product
     */
    /* You may add other non-static properties and methods */
    public String getName(){
        return this.name;
    }

    /**
     * Check if any stock left
     *
     * @return the true if stock is available, otherwise false.
     */
    public boolean checkStock(){
        return this.quantity > 0;
    }

    /**
     * Get the product price.
     *
     * @return the price of the product
     */
    public int getPrice(){
        return this.price;
    }

    /**
     * Perform the buy action.
     */
    public void buy(){
        if (this.quantity>0)
            this.quantity-=1;
    }

}
