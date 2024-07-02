import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * InputGenerator class generates random commands 
 */
public class InputGenerator {
    /** Array of stock symbols for generating random commands */
    private static final String[] SYMBOLS = {"AA", "YBB", "MS", "BAR", "TESLA", "FB", "GS", "TK"};

    /**
     * Main method to generate random commands and write them to a file.
     * @param args command-line arguments (expects output file path)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java InputGenerator <output_file>");
            return;
        }

        Random random = new Random();
        String outputFile = args[0];
        int number_input = random.nextInt(101); // Generate a random number between 0 and 100

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < number_input; i++) {
                String command = generateRandomCommand(random);
                writer.write(command);
                writer.newLine();
            }
            System.out.println("Random commands generated: " + number_input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a random command for stock management.
     * @param random Random object to generate random values
     * @return A randomly generated stock management command
     */
    private static String generateRandomCommand(Random random) {
        int commandType = random.nextInt(4);
        String symbol = SYMBOLS[random.nextInt(SYMBOLS.length)];
        if (commandType == 0) { // ADD
            double price = random.nextDouble() * 100;
            long volume = random.nextInt(1000000);
            long marketCap = Math.abs(random.nextLong() % 1000000000); // Ensure positive value
            return String.format("ADD %s %.2f %d %d", symbol, price, volume, marketCap);
        } else if (commandType == 1) { // REMOVE
            return "REMOVE " + symbol;
        } else if (commandType == 2) { // SEARCH
            return "SEARCH " + symbol;
        } else if (commandType == 3) { // UPDATE
            double newPrice = random.nextDouble() * 100;
            long newVolume = random.nextInt(10000);
            long newMarketCap = Math.abs(random.nextLong() % 100000); // Ensure positive value
            return String.format("UPDATE %s %s %.2f %d %d", symbol, symbol + "U", newPrice, newVolume, newMarketCap);
        } else {
            return "";
        }
    }
}
