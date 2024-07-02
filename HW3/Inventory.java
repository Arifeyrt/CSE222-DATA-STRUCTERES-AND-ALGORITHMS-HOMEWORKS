import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Inventory
 * 
 * This class is responsible for managing the inventory of the electronics store
 * and contains functions related to this.
 * It contains a list of devices and provides methods to add, remove, update,
 * display,sort, restock and export the inventory.
 * The inventory is divided into 5 categories: Headphone, Smart
 * Watches,Tv,computer and Smart phone
 * The inventory is stored in a linked list of array lists.
 * 
 */

public class Inventory {
    private LinkedList<ArrayList<Device>> devices;

    /**
     * Constructor
     * Initializes the devices list with 5 empty array lists
     * 
     * timeComplexity O(1) : There are no loops or repetitive processes. The time it
     * takes to call a method is fixed. It is independent of the input size.
     * Therefore, the time complexity is expressed as O(1).
     */
    public Inventory() {
        devices = new LinkedList<ArrayList<Device>>();
        for (int i = 0; i < 5; i++) {
            devices.add(new ArrayList<Device>());
        }

        devices.get(0).add(new Headphone("Sony WH-1000XM4", 330.90, 11));
        devices.get(0).add(new Headphone("Bose QuietComfort 35 II", 223.23, 12));
        devices.get(0).add(new Headphone("JBL Live 650BTNC", 299.99, 23));
        devices.get(1).add(new SmartWatch("Xiaomi Mi Watch", 499.89, 1));
        devices.get(1).add(new SmartWatch("Samsung Galaxy Watch 3", 599.19, 3));
        devices.get(1).add(new SmartWatch("Garmin Forerunner 945", 329.799, 19));
        devices.get(2).add(new Tv("Samsung Q90T QLED", 1999.99, 30));
        devices.get(2).add(new Tv("Philips 65OLED805/12 65-Inch 4K OLED TV", 1029.99, 10));
        devices.get(2).add(new Tv("Toshiba 65LF711U20 65-Inch 4K Fire TV Edition", 1249.99, 3));
        devices.get(3).add(new Computer("Apple MacBook Pro", 999.99, 4));
        devices.get(3).add(new Computer("HP Spectre x360", 1199.99, 2));
        devices.get(3).add(new Computer("Lenovo ThinkPad X1 Carbon", 1499.13, 5));
        devices.get(3).add(new Computer("Huawei MateBook X Pro", 999.51, 15));
        devices.get(4).add(new SmartPhone("Apple iPhone 15 Pro", 2999, 10));
        devices.get(4).add(new SmartPhone("Samsung S22 pink", 1193.99, 10));
        devices.get(4).add(new SmartPhone("Xiaomi Mi 11 Ultra", 1699.12, 10));
        devices.get(4).add(new SmartPhone("Oppo Find X3 Pro", 1799.12, 10));

    }

    /**
     * Checks if there is a device with the same name.
     * 
     * timeComplexity O(n) Let's assume that there are m categories and that there
     * are n vehicles in each category. There will be m*n transactions, so the time
     * complexity is 0(n).
     * 
     * @param name The name of the device
     * @return boolean true if the device with the same name exists, false otherwise
     */
    public boolean isExist(String name) {
        for (int i = 0; i < devices.size(); i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                if (devices.get(i).get(j).getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Adds a device to the inventory
     * The device is added to the inventory according to the category received from
     * the user
     * 
     * time complexity O(n) where n is the number of devices. In the worst case all
     * elements in the list will be tried so the time complexity is reduced to O(n)
     * 
     * @param device The device to be added
     * @param index  The index of the category
     */
    public void addDevice(Device device, int index) {
        devices.get(index).add(device);
    }

    /**
     * Removes a device from the inventory
     * The device is removed according to the name received from the user
     * time complexity O(n) where n is the number of devices. In the worst case all
     * elements in the list will be tried so the time complexity is reduced to O(n)
     * 
     * @param name The name of the device
     */
    public void removeDevice(String name) {
        for (int i = 0; i < devices.size(); i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                if (devices.get(i).get(j).getName().equals(name)) {
                    devices.get(i).remove(j);
                    return;
                }
            }
        }

    }

    /**
     * Updates the details of a device
     * The device is updated according to the name received from the user
     * 
     * time complexity O(n) where n is the number of devices. In the worst case all
     * elements in the list will be tried so the time complexity is reduced to O(n)
     * 
     * @param name     The name of the device
     * @param newName  The new name of the device
     * @param price    The new price of the device
     * @param quantity The new quantity of the device
     */
    public void upDate(String name, String newName, double price, int quantity) {
        for (int i = 0; i < devices.size(); i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                if (devices.get(i).get(j).getName().equals(name)) {
                    devices.get(i).get(j).setName(newName);
                    devices.get(i).get(j).setPrice(price);
                    devices.get(i).get(j).setQuantity(quantity);
                    System.out.println(devices.get(i).get(j).getName() + " details updated: Price - "
                            + devices.get(i).get(j).getPrice() + "$,  Quantity - "
                            + devices.get(i).get(j).getQuantity());
                    return;
                }
            }
        }
    }

    /**
     * Returns the current price of the device
     * 
     * time complexity O(n) where n is the number of devices. In the worst case all
     * elements in the list will be tried so the time complexity is reduced to O(n)
     * 
     * @param name The name of the device
     * @return double the price of the device
     */
    public double getCurrentPrice(String name) {
        for (int i = 0; i < devices.size(); i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                if (devices.get(i).get(j).getName().equals(name)) {
                    return devices.get(i).get(j).getPrice();
                }
            }
        }
        return 0;
    }

    /**
     * Returns the current quantity of the device
     * 
     * time complexity O(n) where n is the number of devices. In the worst case all
     * elements in the list will be tried so the time complexity is reduced to O(n)
     * 
     * @param name The name of the device
     * @return int the quantity of the device
     */
    public int getCurrentQuantity(String name) {
        for (int i = 0; i < devices.size(); i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                if (devices.get(i).get(j).getName().equals(name)) {
                    return devices.get(i).get(j).getQuantity();
                }
            }
        }
        return 0;
    }

    /**
     * Displays all devices in inventory
     * Checks if there are any vehicles in the list and displays them
     * If there are no vehicles, a message is displayed on the screen
     * 
     * time complexity O(n) The first loop returns the size of the devices list.
     * Let's say this dimension
     * is n. The inner loop has a complexity of O(m) for each sublist, where m is
     * the size of that sublist. Consequently, the time complexity of this function
     * will be O(n * m), where n represents the size of the devices list and m
     * represents the maximum size of a sublist It does. The time complexity of this
     * function will be O(n).
     */

    public void display() {
        int count = 1;
        boolean anyDevices = false;
        for (int i = 0; i < devices.size(); i++) {
            if (!devices.get(i).isEmpty()) {
                anyDevices = true;
                break;
            }
        }
        if (!anyDevices) {
            System.out.println("No devices found");
        } else {
            System.out.println("Device List:");
            for (int i = 0; i < devices.size(); i++) {
                for (int j = 0; j < devices.get(i).size(); j++) {
                    System.out.println(count + ". " + devices.get(i).get(j).toString());
                    count++;
                }
            }
        }
    }

    /**
     * Displays the devices in a specific category
     * To find the minimum value, the entire list is browsed and the cheapest
     * product is determined.
     * The cheapest product is displayed on the screen.
     * 
     * Time complexity O(n) The first loop returns the size of the devices list.
     * Let's say this dimension is n. The inner loop has a complexity of O(m) for
     * each sublist, where m is the size of that sublist. Consequently, the time
     * complexity of this function will be O(n * m), where n represents the size of
     * the devices list and m represents the maximum size of a sublist It does. The
     * time complexity of this function will be O(n)
     *
     */
    public void findCheapest() {
        boolean anyDevices = false;
        for (int i = 0; i < devices.size(); i++) {
            if (!devices.get(i).isEmpty()) {
                anyDevices = true;
                break;
            }
        }
        if (!anyDevices) {
            System.out.println("No devices found");
        } else {
            double min = Double.MAX_VALUE;
            Device cheapest = null;
            for (int i = 0; i < devices.size(); i++) {
                for (int j = 0; j < devices.get(i).size(); j++) {
                    if (devices.get(i).get(j).getPrice() < min) {
                        min = devices.get(i).get(j).getPrice();
                        cheapest = devices.get(i).get(j);
                    }
                }
            }
            System.out.println("The cheapest device is: ");
            System.out.println(cheapest.toString());
        }
    }

    /**
     * Sorts and prints all vehicles from lowest priced to highest priced
     * The list of vehicles is sorted by price and displayed on the screen
     * 
     * Time complexity O(nlogn) The time complexity of the sort method is
     * O(nlogn).The sorting method used typically has O(n log n) time complexity.
     * where n is the total number of all devices. The time complexity of the nested
     * loop in the function is also O(n). but the ranking function dominates.
     * Therefore, the time complexity of the function is O(n log n).
     * 
     */

    public void sortDevices() {
        boolean anyDevices = false;
        for (int i = 0; i < devices.size(); i++) {
            if (!devices.get(i).isEmpty()) {
                anyDevices = true;
                break;
            }
        }
        if (!anyDevices) {
            System.out.println("No devices found");
        } else {
            ArrayList<Device> allDevices = new ArrayList<Device>();
            for (int i = 0; i < devices.size(); i++) {
                for (int j = 0; j < devices.get(i).size(); j++) {
                    allDevices.add(devices.get(i).get(j));
                }
            }
            Collections.sort(allDevices, new Comparator<Device>() {
                public int compare(Device d1, Device d2) {
                    return Double.compare(d1.getPrice(), d2.getPrice());
                }
            });
            int cout = 1;
            System.out.println(" Devices sorted by price::");
            for (int i = 0; i < allDevices.size(); i++) {
                System.out.println(cout + " " + allDevices.get(i).toString());
                cout++;
            }
        }
    }

    /**
     * Total inventory value is calculated and printed
     * The total value of the inventory is calculated by multiplying the price of
     * each device by its quantity and adding it to the total.
     * The total value is displayed on the screen.
     * 
     * time complexity O(n) The first loop returns the size of the devices list.
     * Let's say this dimension
     * is n. The inner loop has a complexity of O(m) for each sublist, where m is
     * the size of that sublist. Consequently, the time complexity of this function
     * will be O(n * m), where n represents the size of the devices list and m
     * represents the maximum size of a sublist It does. The time complexity of this
     * function will be O(n).
     * 
     */
    public void totalValue() {
        double total = 0;
        for (int i = 0; i < devices.size(); i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                total += devices.get(i).get(j).getPrice() * devices.get(i).get(j).getQuantity();
            }
        }
        System.out.println("Total value of inventory: " + total);
    }

    /**
     * Restocks a device
     * The device is restocked according to the name received from the user
     * The user is asked if they want to add or remove stock
     * The quantity is updated according to the user's choice
     * 
     * time complexity O(n) The first loop returns the size of the devices list.
     * Let's say this dimension
     * is n. The inner loop has a complexity of O(m) for each sublist, where m is
     * the size of that sublist. Consequently, the time complexity of this function
     * will be O(n * m), where n represents the size of the devices list and m
     * represents the maximum size of a sublist It does. The time complexity of this
     * function will be O(n).
     * 
     * @param name The name of the device
     */
    public void restock(String name) {
        boolean isfound = false;
        for (int i = 0; i < devices.size(); i++) {
            for (int j = 0; j < devices.get(i).size(); j++) {
                if (devices.get(i).get(j).getName().equals(name)) {
                    while (!isfound) {
                        System.out.println("Do you want to add or remove stock? (Add/Remove) ");
                        Scanner scanner = new Scanner(System.in);
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("Add")) {
                            System.out.println(" Enter the quantity to add: ");
                            int quantity = scanner.nextInt();
                            devices.get(i).get(j).setQuantity(devices.get(i).get(j).getQuantity() + quantity);
                            System.out.println(devices.get(i).get(j).getName() + "  restocked. New quantity "
                                    + devices.get(i).get(j).getQuantity());
                            isfound = true;
                            return;
                        } else if (choice.equalsIgnoreCase("Remove")) {
                            System.out.println("Enter the quantity to remove: ");
                            int quantity = scanner.nextInt();
                            devices.get(i).get(j).setQuantity(devices.get(i).get(j).getQuantity() - quantity);
                            System.out.println(devices.get(i).get(j).getName() + " restocked. New quantity "
                                    + devices.get(i).get(j).getQuantity());
                            isfound = true;
                            return;
                        } else {
                            System.out.println("Invalid choice");
                            isfound = false;
                        }
                    }

                }

            }
        }
        System.out.println("Device not found");
    }

    /**
     * Imports devices from a file
     * The file is read and the devices are added to the inventory
     * The file is in the format: category, name, price, quantity
     * 
     * time complexity O(n) The first loop returns the size of the devices list.
     * Let's say this dimension
     * is n. The inner loop has a complexity of O(m) for each sublist, where m is
     * the size of that sublist. Consequently, the time complexity of this function
     * will be O(n * m), where n represents the size of the devices list and m
     * represents the maximum size of a sublist It does. The time complexity of this
     * function will be O(n).
     */
    public void exportReport() {

        String fileName = "report.txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Electronics Shop Inventory Report\n");
            writer.write("Generated on: 26th March 2024\n");
            writer.write("--------------------------------------------------\n");
            writer.write("| No. | Category | Name     | Price | Quantity |\n");
            writer.write("--------------------------------------------------\n");
            int count = 1;
            double total = 0;
            for (int i = 0; i < devices.size(); i++) {
                for (int j = 0; j < devices.get(i).size(); j++) {
                    total += devices.get(i).get(j).getPrice() * devices.get(i).get(j).getQuantity();
                    writer.write("| " + count + " | " + devices.get(i).get(j).getCategory() + " | "
                            + devices.get(i).get(j).getName() + " | " + devices.get(i).get(j).getPrice() + " | "
                            + devices.get(i).get(j).getQuantity() + " |\n");
                    count++;
                }
            }
            writer.write("--------------------------------------------------\n");
            writer.write("Summary\n");
            writer.write("Total number of devices: " + count + "\n");
            writer.write("Total value of inventory: " + total + "\n");
            writer.close();
            System.out.println("Report exported to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
