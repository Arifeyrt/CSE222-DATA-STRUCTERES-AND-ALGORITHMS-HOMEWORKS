import java.util.Scanner;

/**
 * Main
 * Master class for operating Electronic Inventory Management System.
 * This class contains the main methods and option list to launch the
 * application.
 * The user can add, remove, update, list devices in the inventory, find the
 * cheapest device, sort devices by price, calculate the total inventory value,
 * restock devices, and export the inventory report.
 * 
 * 
 */
public class Main {

    /**
     * Main method to start the Electronics Inventory Management System.
     * 
     * @param args Command line arguments are not used in this application.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        System.out.println("Welcome to the Electronics Inventory Management System!");

        int choice;
        do {
            System.out.println("Please select an option:");
            System.out.println("1. Add a new device");
            System.out.println("2. Remove a device");
            System.out.println("3. Update device details");
            System.out.println("4. List all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // add new device
                    System.out.print("Enter category name: ");
                    String category = new String();
                    // Continue the loop until the user enters a valid catego
                    while (true) {
                        if (scanner.hasNextLine()) {
                            category = scanner.nextLine();
                            if (category.equalsIgnoreCase("Computer") || category.equalsIgnoreCase("Smart Phone")
                                    || category.equalsIgnoreCase("Smart Watch") || category.equalsIgnoreCase("Tv")
                                    || category.equalsIgnoreCase("HeadPhone")) {
                                break;
                            } else {
                                System.out.println(
                                        "Invalid category. Just enter one of the categories 'Computer', 'Smart Phone', 'HeadPhone', 'Tv' or 'Smart Watch':");
                            }
                        }
                    }
                    System.out.print("Enter device name: ");
                    String name = new String();
                    // Continue the loop until the user enters a valid device name
                    while (true) {
                        if (scanner.hasNextLine()) {
                            name = scanner.nextLine();
                            if (!name.isEmpty()) {

                                if (inventory.isExist(name)) {
                                    System.out.println(
                                            "A device with the same name already exists. Please enter a different name:");
                                    continue;
                                }
                                break;
                            } else {
                                System.out.println("Invalid device name. Please enter a device name:");
                            }
                        }
                    }

                    System.out.print("Enter price: ");
                    double price = 0;
                    while (true) {
                        String input = scanner.nextLine();
                        // Check if there is a $ sign at the end of the entry
                        if (!input.endsWith("$")) {
                            System.out.println(
                                    "Invalid login. There must be a $ sign at the end of the price. Try again:");
                            continue;
                        }
                        int indexOfDollar = input.indexOf("$");
                        price = Double.parseDouble(input.substring(0, indexOfDollar));
                        if (price <= 0) {
                            System.out.println("Invalid price. Please enter a price:");
                            continue;
                        }
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int quantity = 0;
                    while (true) {
                        if (scanner.hasNextInt()) {
                            quantity = scanner.nextInt();
                            if (quantity > 0) {
                                break;
                            } else {
                                System.out.println("Invalid amount. Please enter an amount:");
                            }
                        } else {
                            System.out.println("Invalid amount. Please enter an amount:");
                            scanner.next();
                        }
                    }

                    if (category.equalsIgnoreCase("Computer")) {
                        Device device = new Computer(name, price, quantity);
                        inventory.addDevice(device, 3);
                        System.err.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice()
                                + "$, " + device.getQuantity() + " amount added...");
                    } else if (category.equalsIgnoreCase("Smart Phone")) {
                        Device device = new SmartPhone(name, price, quantity);
                        inventory.addDevice(device,4);
                        System.err.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice()
                                + "$, " + device.getQuantity() + " amount added...");
                    } else if (category.equalsIgnoreCase("Smart Watch")) {
                        Device device = new SmartWatch(name, price, quantity);
                        inventory.addDevice(device,1);
                        System.err.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice()
                                + "$, " + device.getQuantity() + " amount added...");
                    } else if (category.equalsIgnoreCase("Tv")) {
                        Device device = new Tv(name, price, quantity);
                        inventory.addDevice(device,2);
                        System.err.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice()
                                + "$, " + device.getQuantity() + " amount added...");
                    } else if (category.equalsIgnoreCase("HeadPhone")) {
                        Device device = new Headphone(name, price, quantity);
                        inventory.addDevice(device,0);
                        System.err.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice()
                                + "$, " + device.getQuantity() + " amount added...");
                    }
                    break;
                case 2:
                    // a remove device
                    System.out.print("Enter the name of the device you want to remove: ");
                    String removeName = scanner.nextLine();
                    if (!inventory.isExist(removeName)) {
                        System.out.println("A device with the name " + removeName + " does not exist.");
                        break;
                    }
                    inventory.removeDevice(removeName);
                    System.out.println("Device remove...");
                    break;

                case 3:
                    String updateName = new String();
                    while (true) {
                        System.out.print(" Enter the name of the device to update: ");
                        updateName = scanner.nextLine();
                        if (!inventory.isExist(updateName)) {
                            System.out.println("A device with the name " + updateName + " does not exist.");

                        } else {
                            break;
                        }
                    }
                    System.out.print("Enter new name(leave blank to keep current quantity): ");
                    String newName = scanner.nextLine();
                    if (newName.isEmpty()) {
                        newName = updateName;
                    }
                    System.out.print("Enter new price(leave blank to keep current quantity): ");
                    String newPrice_dolar = scanner.nextLine();
                    Double newPrice = 0.0;
                    if (newPrice_dolar.isEmpty()) {
                        newPrice = inventory.getCurrentPrice(updateName);
                    } else {
                        int indexOfDollar = newPrice_dolar.indexOf("$");
                        newPrice = Double.parseDouble(newPrice_dolar.substring(0, indexOfDollar));
                    }
                    System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                    String newQuantity = scanner.nextLine();
                    int newQuantity_int = 0;
                    if (newQuantity.isEmpty()) {
                        newQuantity_int = inventory.getCurrentQuantity(updateName);
                    } else {
                        newQuantity_int = Integer.parseInt(newQuantity);
                    }
                    inventory.upDate(updateName, newName, newPrice, newQuantity_int);
                    break;
                case 4:
                    inventory.display();
                    break;
                case 5:

                    inventory.findCheapest();
                    break;
                case 6:
                    inventory.sortDevices();

                    break;
                case 7:
                    inventory.totalValue();
                    break;
                case 8:
                    String name_stock = new String();
                    System.out.println("Enter the name of the device to restock: ");
                    name_stock = scanner.nextLine();
                    inventory.restock(name_stock);
                    break;
                case 9:
                    inventory.exportReport();
                    break;
                case 0:
                    // Exit
                    System.out.println("Çıkış yapılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");

            }
        } while (choice != 0);

        scanner.close();
    }
}