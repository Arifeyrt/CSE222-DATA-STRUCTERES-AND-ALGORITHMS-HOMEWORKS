import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String file_name = "content.txt";
        Operator[] operators = new Operator[100];
        Customer[] customers = new Customer[100];
        Order[] orders = new Order[100];
        int[] usedOperatorcustomerIDs = new int[100];
        int operatorCount = 0;
        int customerCount = 0;
        int orderCount = 0;
        int operatorcustomerCount = 0;

        File file_txt = new File(file_name);
        try {
            Scanner scanner = new Scanner(file_txt);
            while (scanner.hasNextLine()) {
                String lines = scanner.nextLine();//First, it reads line by line from the file
                String[] line = lines.split(";");//after, it reads items by linitemse from the file
                operatorcustomerCount = operatorCount + customerCount;//Total number of customers and operators to check ID equality
                if (isValidLine(lines, usedOperatorcustomerIDs, operatorcustomerCount) == -1) {
                    continue;
                }

                //Rows read are listed by category according to the first element
                if (line[0].equals("operator")) {
                    operators[operatorCount] = new Operator(line[1], line[2], line[3], line[4],
                            Integer.parseInt(line[5]), Integer.parseInt(line[6]));
                    usedOperatorcustomerIDs[operatorcustomerCount] = Integer.parseInt(line[5]); // Operator cutomer control with the same id
                    operators[operatorCount].define_customers(customers);
                    operatorCount++;
                } else if (line[0].equals("retail_customer")) {
                    customers[customerCount] = new RetailCustomer(line[1], line[2], line[3], line[4],
                            Integer.parseInt(line[5]), Integer.parseInt(line[6]));
                    usedOperatorcustomerIDs[operatorcustomerCount] = Integer.parseInt(line[5]); // Operator cutomer control with the same id
                    customerCount++;
                } else if (line[0].equals("corporate_customer")) {
                    customers[customerCount] = new CorporateCustomer(line[1], line[2], line[3], line[4],
                            Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7]);
                    usedOperatorcustomerIDs[operatorcustomerCount] = Integer.parseInt(line[5]); //Operator cutomer control with the same id
                    customerCount++;
                } else if (line[0].equals("order")) {
                    orders[orderCount] = new Order(line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]),
                            Integer.parseInt(line[4]), Integer.parseInt(line[5]));
                    orderCount++;
                }
            }
            scanner.close();
        } catch (Exception exceptions) {
            System.out.println(exceptions);
            System.out.println("not read file");
            return;
        }

        for (int i = 0; i < operatorCount; i++) {
            operators[i].define_customers(customers);
        }
        for (int i = 0; i < customerCount; i++) {
            customers[i].define_orders(orders);
        }

        // Get ID input from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your ID...");
        int ID=0;
        if (scanner.hasNextInt()) {
          ID = scanner.nextInt();
            if (ID < 0) {
                System.out.println("Your input is not negative!");
                System.exit(0);
            } 
        } else {
            System.out.println("NOT VALID!");
            System.exit(0);
        }
        
         // Is there an operator in this id?
        int isIDfound = -1;
        for (int i = 0; i < operatorCount; i++) {
            if (operators[i].getID() == ID) {
                isIDfound = 1;
                operators[i].print_operator();
                operators[i].print_customers();
            }
        }

        //Is there an customer in this id?
        if (isIDfound == -1) {
            for (int i = 0; i < customerCount; i++) {
                if (customers[i].getID() == ID) {
                    isIDfound = 1;
                    System.out.println("*** Customer Screen ***");
                    customers[i].print_customer();
                    customers[i].print_orders();
                }
            }
        }
        if (isIDfound == -1) {
            System.out.println(" No operator/customer was found with ID " + ID + ". Please try again. ");
        }
    }

    private static int isValidLine(String line, int[] usedOperatorcustomerIDs, int operatorcustomerCount) {
        String[] items = line.split(";");

        if (items[0].equals("operator")) {
            if (items.length != 7) { 
                return -1;
               // must not have empty elements
            } else if (items[1].isEmpty() || items[2].isEmpty() || items[3].isEmpty() || items[4].isEmpty()
                    || items[5].isEmpty() || items[6].isEmpty()) {
                return -1;

            } else if (!items[5].matches("\\d+")) { // Is items[5] an integer?
                return -1;
            } else if (!items[6].matches("\\d+")) { // Is items[6] an integer?
                return -1;

            } else if (Integer.parseInt(items[5]) < 0) {// items[5] bir pozitif tamsayı değilse
                return -1;
            } else if (Integer.parseInt(items[6]) < 0) { // items[5] bir pozitif tamsayı değilse
                return -1;
            }

            int ID = Integer.parseInt(items[5]);
            for (int i = 0; i < operatorcustomerCount; i++) {
                if (usedOperatorcustomerIDs[i] == ID) {
                    return -1; // Aynı ID daha önce kullanılmış
                }
            }
        }

        if (items[0].equals("retail_customer")) {
            if (items.length != 7) {
                return -1;
            } else if (items[1].isEmpty() || items[2].isEmpty() || items[3].isEmpty() || items[4].isEmpty()
                    || items[5].isEmpty() || items[6].isEmpty()) {
                return -1;

            } else if (!items[5].matches("\\d+")) { // Is items[5] an integer?
                return -1;
            } else if (!items[6].matches("\\d+")) { // Is items[6] an integer?
                return -1;

            } else if (Integer.parseInt(items[5]) < 0) {// if items[5] is not a positive integer
                return -1;
            } else if (Integer.parseInt(items[6]) < 0) {// if items[6] is not a positive integer
                return -1;
            }
            int ID = Integer.parseInt(items[5]);
            for (int i = 0; i < operatorcustomerCount; i++) {
                if (usedOperatorcustomerIDs[i] == ID) {
                    return -1;  // Same ID used before
                }
            }
        }

        if (items[0].equals("corporate_customer")) {
            if (items.length != 8) {
                return -1;
            } else if (items[1].isEmpty() || items[2].isEmpty() || items[3].isEmpty() || items[4].isEmpty()
                    || items[5].isEmpty() || items[6].isEmpty() || items[7].isEmpty()) {
                return -1;

            } else if (!items[5].matches("\\d+")) { // // Is items[5] an integer?
                return -1;
            } else if (!items[6].matches("\\d+")) { // // Is items[6] an integer?
                return -1;

            } else if (Integer.parseInt(items[5]) < 0) {// if items[5] is not a positive integer
                return -1;
            } else if (Integer.parseInt(items[6]) < 0) {// if items[6] is not a positive integer
                return -1;
            }
            int ID = Integer.parseInt(items[5]);
            for (int i = 0; i < operatorcustomerCount; i++) {
                if (usedOperatorcustomerIDs[i] == ID) {
                    return -1; // Same ID used before
                }
            }
        }

        if (items[0].equals("order")) {
            if (items.length != 6) {
                return -1;
            } else if (items[1].isEmpty() || items[2].isEmpty() || items[3].isEmpty() || items[4].isEmpty()
                    || items[5].isEmpty()) {
                return -1;

            } else if (!items[2].matches("\\d+")) { // items[2] is not integer
                return -1;
            } else if (!items[3].matches("\\d+")) { // items[3] is not integer
                return -1;
            } else if (!items[4].matches("\\d+")) { // items[4] is not integer
                return -1;
            } else if (!items[5].matches("\\d+")) { // items[5] is not integer
                return -1;

            } else if (Integer.parseInt(items[2]) <= 0) { // if items[2] is not a positive integer
                return -1;
            } else if (Integer.parseInt(items[3]) <= 0) { // if items[3] is not a positive integer
                return -1;
            } else if (Integer.parseInt(items[4]) != 0 && Integer.parseInt(items[4]) != 1
                    && Integer.parseInt(items[4]) != 2 && Integer.parseInt(items[4]) != 3) {
                return -1;
            } else if (Integer.parseInt(items[5]) <= 0) { // if items[5] is not a positive integer
                return -1;
            }
        }
        if (!items[0].equals("order") && !items[0].equals("retail_customer") && !items[0].equals("corporate_customer")
                && !items[0].equals("operator")) {
            return -1;
        }
        return 1;
    }
}
