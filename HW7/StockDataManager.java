/**
 * StockDataManager is responsible for managing stock information using an AVL tree.
 * It provides methods to add, update, remove, and search stocks as well as display the tree structure.
 */
public class StockDataManager {
    private AVLTree avlTree;

    /**
     * Constructs a new StockDataManager with an empty AVL tree.
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    /**
     * Adds a new stock or updates an existing stock in the AVL tree.
     *
     * @param symbol    the symbol of the stock
     * @param price     the price of the stock
     * @param volume    the volume of the stock
     * @param marketCap the market capitalization of the stock
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = avlTree.searchStock(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock stock = new Stock(symbol, price, volume, marketCap);
            avlTree.addStock(stock);
        }
    }

    /**
     * Removes a stock from the AVL tree based on its symbol.
     *
     * @param symbol the symbol of the stock to be removed
     */
    public void removeStock(String symbol) {
        avlTree.removeStock(symbol);
    }

    /**
     * Searches for a stock in the AVL tree based on its symbol.
     *
     * @param symbol the symbol of the stock to be searched
     * @return the stock if found, null otherwise
     */
    public Stock searchStock(String symbol) {
        return avlTree.searchStock(symbol);
    }

    /**
     * Updates an existing stock's details. If the symbol is changed, it removes the old stock and adds a new one.
     *
     * @param oldSymbol the current symbol of the stock
     * @param newSymbol the new symbol of the stock
     * @param price     the new price of the stock
     * @param volume    the new volume of the stock
     * @param marketCap the new market capitalization of the stock
     */
    public void updateStock(String oldSymbol, String newSymbol, double price, long volume, long marketCap) {
        Stock existingStock = searchStock(oldSymbol);
        if (existingStock != null) {
            // If the stock symbol changes, remove the old stock and add the new stock
            removeStock(oldSymbol);
            addOrUpdateStock(newSymbol, price, volume, marketCap);
        } else {
            // If the stock does not exist, add a new stock
            addOrUpdateStock(newSymbol, price, volume, marketCap);
        }
    }

    /**
     * Displays the AVL tree in an in-order traversal.
     */
    public void displayInOrder() {
        avlTree.printInOrder();
    }

    /**
     * Displays the AVL tree in a pre-order traversal.
     */
    public void displayPreOrder() {
        avlTree.printPreOrder();
    }

    /**
     * Displays the AVL tree in a post-order traversal.
     */
    public void displayPostOrder() {
        avlTree.printPostOrder();
    }

    /**
     * Prints the structure of the AVL tree.
     */
    public void printTree() {
        avlTree.printTree();
    }
}
