import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main class for the Social Network Analysis program.
 */
public class Main {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Adding some people for demonstration
        network.addPerson("Arife", 25, Arrays.asList("reading", "hiking", "cooking"));
        network.addPerson("Gulbeyaz", 22, Arrays.asList("swimming", "cooking"));
        network.addPerson("Berke", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Elif", 30, Arrays.asList("reading", "swimming"));
        network.addPerson("Emir", 28, Arrays.asList("running", "swimming"));
        network.addPerson("Feraye", 21, Arrays.asList("watching", "listening", "driving", "cooking"));
        network.addPerson("Burak", 26, Arrays.asList("reading", "travelling", "watching"));
        network.addPerson("Akif", 28, Arrays.asList("reading", "travelling", "watching"));
      //  network.addPerson("Yaman", 28, Arrays.asList("reading", "hiking", "cooking"));

        while (running) {
            System.out.println("\n===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1; // Geçersiz giriş
            }

            switch (choice) {
                case 1:
                    addPerson(network, scanner);
                    break;
                case 2:
                    removePerson(network, scanner);
                    break;
                case 3:
                    addFriendship(network, scanner);
                    break;
                case 4:
                    removeFriendship(network, scanner);
                    break;
                case 5:
                    findShortestPath(network, scanner);
                    break;
                case 6:
                    suggestFriends(network, scanner);
                    break;
                case 7:
                    countClusters(network);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Adds a person to the social network.
     *
     * @param network the social network graph
     * @param scanner the scanner for user input
     */
    private static void addPerson(SocialNetwork network, Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        int age = 0;
        boolean valid = false;
    
        // Yaşın doğru bir şekilde girilmesini sağlama döngüsü
        while (!valid) {
            System.out.print("Enter age: ");
            String ageInput = scanner.nextLine();
            try {
                age = Integer.parseInt(ageInput);
                valid = true; // Giriş başarılı, döngüden çık
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age. Please enter a valid number.");
            }
        }
    
        System.out.print("Enter hobbies (comma-separated): ");
        String hobbies = scanner.nextLine();
        List<String> hobbiesList = Arrays.asList(hobbies.split(","));
    
        network.addPerson(name, age, hobbiesList);
    }
    

    /**
     * Removes a person from the social network.
     *
     * @param network the social network graph
     * @param scanner the scanner for user input
     */
    private static void removePerson(SocialNetwork network, Scanner scanner) {
        System.out.print("Enter name: ");
        String removeName = scanner.nextLine();
        System.out.print("Enter timestamp (YYYY-MM-DD HH:MM:SS): ");
        String timestampStr = scanner.nextLine();
        try {
            Date timestamp = parseDate(timestampStr);
            network.removePerson(removeName, timestamp);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use 'YYYY-MM-DD HH:MM:SS'.");
        }
    }

    /**
     * Adds a friendship between two people in the social network.
     *
     * @param network the social network graph
     * @param scanner the scanner for user input
     */
    private static void addFriendship(SocialNetwork network, Scanner scanner) {
        System.out.print("Enter first person's name: ");
        String person1 = scanner.nextLine();
        System.out.print("Enter first person's timestamp (YYYY-MM-DD HH:MM:SS): ");
        String timestampStr1 = scanner.nextLine();
        System.out.print("Enter second person's name: ");
        String person2 = scanner.nextLine();
        System.out.print("Enter second person's timestamp (YYYY-MM-DD HH:MM:SS): ");
        String timestampStr2 = scanner.nextLine();
        try {
            Date timestamp1 = parseDate(timestampStr1);
            Date timestamp2 = parseDate(timestampStr2);
            network.addFriendship(person1, timestamp1, person2, timestamp2);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use 'YYYY-MM-DD HH:MM:SS'.");
        }
    }

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param network the social network graph
     * @param scanner the scanner for user input
     */
    private static void removeFriendship(SocialNetwork network, Scanner scanner) {
        System.out.print("Enter first person's name: ");
        String removePerson1 = scanner.nextLine();
        System.out.print("Enter first person's timestamp (YYYY-MM-DD HH:MM:SS): ");
        String timestampStr1 = scanner.nextLine();
        System.out.print("Enter second person's name: ");
        String removePerson2 = scanner.nextLine();
        System.out.print("Enter second person's timestamp (YYYY-MM-DD HH:MM:SS): ");
        String timestampStr2 = scanner.nextLine();
        try {
            Date timestamp1 = parseDate(timestampStr1);
            Date timestamp2 = parseDate(timestampStr2);
            network.removeFriendship(removePerson1, timestamp1, removePerson2, timestamp2);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use 'YYYY-MM-DD HH:MM:SS'.");
        }
    }

    /**
     * Finds the shortest path between two people in the social network.
     *
     * @param network the social network graph
     * @param scanner the scanner for user input
     */
    private static void findShortestPath(SocialNetwork network, Scanner scanner) {
        System.out.print("Enter start person's name: ");
        String startPerson = scanner.nextLine();
        System.out.print("Enter start person's timestamp (YYYY-MM-DD HH:MM:SS): ");
        String startTimestampStr = scanner.nextLine();
        System.out.print("Enter end person's name: ");
        String endPerson = scanner.nextLine();
        System.out.print("Enter end person's timestamp (YYYY-MM-DD HH:MM:SS): ");
        String endTimestampStr = scanner.nextLine();
        try {
            Date startTimestamp = parseDate(startTimestampStr);
            Date endTimestamp = parseDate(endTimestampStr);
            network.findShortestPath(startPerson, startTimestamp, endPerson, endTimestamp);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use 'YYYY-MM-DD HH:MM:SS'.");
        }
    }

    /**
     * Suggests friends for a person in the social network.
     *
     * @param network the social network graph
     * @param scanner the scanner for user input
     */
    private static void suggestFriends(SocialNetwork network, Scanner scanner) {
        System.out.print("Enter person's name: ");
        String suggestPerson = scanner.nextLine();
        System.out.print("Enter person's timestamp (YYYY-MM-DD HH:MM:SS): ");
        String suggestTimestampStr = scanner.nextLine();
        try {
            Date suggestTimestamp = parseDate(suggestTimestampStr);
            System.out.print("Enter maximum number of friends to suggest: ");
            int maxSuggestions = scanner.nextInt();
            scanner.nextLine(); // consume newline
            network.suggestFriends(suggestPerson, suggestTimestamp, maxSuggestions);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use 'YYYY-MM-DD HH:MM:SS'.");
        }
    }

    /**
     * Counts the number of clusters in the social network.
     *
     * @param network the social network graph
     */
    private static void countClusters(SocialNetwork network) {
        network.countClusters();
    }

    /**
     * Parses a date string into a Date object.
     *
     * @param dateStr the date string to parse
     * @return the parsed Date object
     * @throws ParseException if the date string is in an invalid format
     */
    private static Date parseDate(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
    }
}
