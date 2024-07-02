import java.util.Scanner;

/**
 * The main class for managing a file system.
 */
public class Main {
    /** The file system instance. */
    private static FileSystem fs = new FileSystem();

    /** Scanner for user input. */
    private static Scanner scanner = new Scanner(System.in);

    /** The current directory in the file system. */
    private static Directory currentDirectory;

    /**
     * Main method to start the file system management.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        currentDirectory = fs.getRoot();

        while (true) {
            System.out.println(" ");
            System.out.println("===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date created");
            System.out.println("9. Exit");
            System.out.println("Please select an option");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1:
                    changeDirectory();
                    break;
                case 2:
                    listContents();
                    break;
                case 3:
                    create();
                    break;
                case 4:
                    // createDirectory();
                    delete_();
                    break;
                case 5:
                    move();
                    break;
                case 6:
                    search();
                    // deleteDirectory();
                    break;
                case 7:
                    // sortDirectoryByDate();
                    printDirectoryTree();
                    break;
                case 8:
                    sortDirectoryByDate();
                    break;

                case 9:
                    System.out.println("Existing...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Changes the current directory.
     */
    private static void changeDirectory() {
        System.out.println("current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Enter new directory path: ");
        currentDirectory = fs.changeDirectory(scanner.nextLine());
        fs.setCurrent(currentDirectory);
    }

    /**
     * Lists the contents of the current directory.
     */
    private static void listContents() {
        System.out.println("Listing contents of test: " + fs.getCurrentPath());
        fs.listContents(currentDirectory);
    }

    /**
     * Creates a new file or directory.
     */
    private static void create() {
        System.out.println("current directory: " + fs.getCurrentPath());
        System.out.print("Create file or directory (f/d): ");
        String name = scanner.nextLine();
        if (name.equals("d")) {
            System.out.print("Enter name for new directory: ");
            String directoryName = scanner.nextLine();
            fs.createDirectory(directoryName, currentDirectory);
        } else if (name.equals("f")) {
            System.out.print("Enter name for new directory: ");
            String directoryName = scanner.nextLine();
            fs.createFile(directoryName, currentDirectory);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Deletes a file or directory.
     */
    private static void delete_() {
        System.out.println("current directory: " + fs.getCurrentPath());
        System.out.println("Enter file/directory name to delete: ");
        String name = scanner.nextLine();
        FileSystemElement element = fs.find(name, currentDirectory);
        if (element != null) {
            fs.delete(name, currentDirectory); // currentDirectory'yi delete fonksiyonuna yolla
            System.out.println("Directory deleted: " + name);
        } else {
            System.out.println("Error: File/directory not found.");
        }
    }

    /**
     * Moves a file or directory.
     */
    private static void move() {
        System.out.println("current directory: " + fs.getCurrentPath());
        String name;
        // System.out.println(fs.getCurrentPath(currentDirectory));
        System.out.print("Enter the name of file/directory to move: ");
        name = scanner.nextLine();
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
        Directory dir = fs.findcurrentpath(newPath);
        if (dir == null) {
            System.out.println("Error: Destination directory not found.");
            return;
        }
        fs.moveElement(name, dir);
        System.out.println("File moved: " + name + " to " + newPath);

    }

    /**
     * Searches for a file or directory.
     */
    private static void search() {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        fs.search(query);

    }

    /**
     * Prints the directory tree.
     */
    private static void printDirectoryTree() {
        fs.printDirectoryTree(currentDirectory);
    }

    /**
     * Sorts directory contents by date created.
     */
    private static void sortDirectoryByDate() {
        fs.sortDirectoryByDate(currentDirectory);
    }

}