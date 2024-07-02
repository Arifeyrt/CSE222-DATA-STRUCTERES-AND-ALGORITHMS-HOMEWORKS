/**
 * The Device interface represents an electronic device.
 * It defines methods to get and set the category, name, price, and quantity of the device.
 */
interface Device {
    /**
     * Returns the category of the device.
     * 
     * @return string The category of the device.
     * 
     */
    String getCategory();

    /**
     * Returns the name of the device.
     * 
     * @return string The name of the device.
     * 
     */
    String getName();

    /**
     * Returns the price of the device.
     * 
     * @return double The price of the device.
     * 
     */
    double getPrice();

    /**
     * Returns the quantity of the device.
     * 
     * @return int The quantity of the device.
     */

    int getQuantity();

  /** 
   * Sets the name of the device.
   * 
   * @param name The name of the device.
  */
    void setName(String name);

    /**
     * Sets the price of the device.
     * @param price The price of the device.
     */
    void setPrice(double price);

    /**
     * Sets the quantity of the device.
     * @param quantity The quantity of the device.
     */
    void setQuantity(int quantity);

}