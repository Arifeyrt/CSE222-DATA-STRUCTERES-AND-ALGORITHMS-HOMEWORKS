/**
 * Smartwatch class represents a television device.
 */
public class SmartWatch implements Device {
    private String category = "Smart Watch";
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructor to initialize a Smartwatch with its name, price, and quantity.
     * Time complexity O(1) : There are no loops or repetitive processes. The time
     * it takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     * @param name     The name of the SmartWatch
     * @param price    The price of the SmartWatch
     * @param quantity The quantity of the SmartWatch
     */
    public SmartWatch(String name, double price, int quantity) {
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
