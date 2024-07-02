/**
 * Tv class represents a television device.
 */
public class Tv implements Device {

    private String category = "Tv"; // Category of the device
    private String name; // Name of the TV
    private double price; // Price of the TV
    private int quantity; // Quantity of the TV

    /**
     * Constructor to initialize a TV with its name, price, and quantity.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @param name     The name of the TV.
     * @param price    The price of the TV.
     * @param quantity The quantity of the TV.
     */
    public Tv(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @return string The category of the device.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the name of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @return string The name of the device.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @return double The price of the device.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @return int The quantity of the device.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the name of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @param name The name of the device.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @param price The price of the device.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @param quantity The quantity of the device.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the string representation of the device.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * 
     * @return string The string representation of the device.
     */
    public String toString() {
        return "Category: " + category + ", Name: " + name + ", Price: " + price + "$, Quantity: " + quantity;
    }

}
