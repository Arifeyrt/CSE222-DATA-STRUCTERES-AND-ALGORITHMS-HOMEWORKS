import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main class for managing and analyzing stock data using AVL trees.
 * This class reads stock commands from a file, processes them, and visualizes performance.
 */
public class Main {
    private static final int[] TREE_SIZES = {1000, 5000, 9000, 13000, 17000, 21000, 25000, 29000};
    private static final int TEST_ITERATIONS = 20;
    private static final int OPERATIONS_COUNT = 100;

    /**
     * Main method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager0 = new StockDataManager();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayTree(manager0);

        StockDataManager[] managers = new StockDataManager[TREE_SIZES.length];

        for (int i = 0; i < TREE_SIZES.length; i++) {
            managers[i] = new StockDataManager();
            for (int j = 0; j < TREE_SIZES[i]; j++) {
                managers[i].addOrUpdateStock("AYS" + j, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }
        }

        GUIVisualization frameAdd = new GUIVisualization("scatter", "ADD");
        GUIVisualization frameRemove = new GUIVisualization("scatter", "REMOVE");
        GUIVisualization frameUpdate = new GUIVisualization("scatter", "UPDATE");
        GUIVisualization frameSearch = new GUIVisualization("scatter", "SEARCH");

        performAnalysis(frameAdd, managers, "ADD");
        performAnalysis(frameRemove, managers, "REMOVE");
        performAnalysis(frameUpdate, managers, "UPDATE");
        performAnalysis(frameSearch, managers, "SEARCH");

        frameAdd.setTitle("Performance Graph Visualization - ADD Operation");
        frameAdd.setVisible(true);
        frameRemove.setTitle("Performance Graph Visualization - REMOVE Operation");
        frameRemove.setVisible(true);
        frameUpdate.setTitle("Performance Graph Visualization - UPDATE Operation");
        frameUpdate.setVisible(true);
        frameSearch.setTitle("Performance Graph Visualization - SEARCH Operation");
        frameSearch.setVisible(true);
    }

    /**
     * Processes a command line and executes the corresponding stock operation.
     *
     * @param line    the command line to process
     * @param manager the StockDataManager to use for the operation
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                System.out.println("Added: " + tokens[1]);
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                System.out.println("Removed: " + tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                System.out.println("Searching for: " + tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                System.out.println("Updated: " + tokens[1] + " to " + tokens[2]);
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    /**
     * Performs a performance analysis on the specified StockDataManager and operation type.
     *
     * @param manager   the StockDataManager to analyze
     * @param size      the number of operations to perform
     * @param operation the type of operation to perform ("ADD", "REMOVE", "UPDATE", "SEARCH")
     */
    private static void performPerformanceAnalysis(StockDataManager manager, int size, String operation) {
        switch (operation) {
            case "ADD":
                for (int i = 0; i < size; i++) {
                    manager.addOrUpdateStock("GTU" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                }
                break;
            case "REMOVE":
                for (int i = 0; i < size; i++) {
                    manager.removeStock("AYS" + i);
                }
                break;
            case "UPDATE":
                for (int i = 0; i < size; i++) {
                    manager.updateStock("AYS" + i, "NEW" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                }
                break;
            case "SEARCH":
                for (int i = 0; i < size; i++) {
                    manager.searchStock("AYS" + i);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    /**
     * Performs analysis on multiple StockDataManager instances for the specified operation and visualizes the results.
     *
     * @param frame     the GUIVisualization frame to display the results
     * @param managers  the array of StockDataManager instances to analyze
     * @param operation the type of operation to perform ("ADD", "REMOVE", "UPDATE", "SEARCH")
     */
    private static void performAnalysis(GUIVisualization frame, StockDataManager[] managers, String operation) {
        for (int i = 0; i < TREE_SIZES.length; i++) {
            long totalTime = 0;

            for (int j = 0; j < TEST_ITERATIONS; j++) {
                long startTime = System.nanoTime();
                performPerformanceAnalysis(managers[i], OPERATIONS_COUNT, operation);
                long endTime = System.nanoTime();
                long averageTime = (endTime - startTime) / OPERATIONS_COUNT;
                totalTime += averageTime;
            }

            frame.addDataPointX(TREE_SIZES[i]);
            frame.addDataPointY(totalTime / TEST_ITERATIONS);
        }
    }

    /**
     * Displays the tree structure and traversals (in-order, pre-order, post-order) for the given StockDataManager.
     *
     * @param manager the StockDataManager to display
     */
    private static void displayTree(StockDataManager manager) {
        manager.printTree();
        System.out.println("Display in-order:");
        manager.displayInOrder();
        System.out.println("Display pre-order:");
        manager.displayPreOrder();
        System.out.println("Display post-order:");
        manager.displayPostOrder();
    }
}
