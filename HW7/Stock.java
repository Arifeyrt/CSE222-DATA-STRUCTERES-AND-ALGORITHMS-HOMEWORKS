/**
 * The Stock class represents a stock.
 */
public class Stock {
    /**
     * The symbol of the stock (e.g., AAPL)
     */
    private String symbol;

    /**
     * The price of the stock
     */
    private double price;

    /**
     * The volume of the stock
     */
    private long volume;

    /**
     * The market capitalization of the stock
     */
    private long marketCap;

    /**
     * Constructs a new Stock object.
     *
     * @param symbol    The symbol of the stock
     * @param price     The price of the stock
     * @param volume    The volume of the stock
     * @param marketCap The market capitalization of the stock
     */
    public Stock(String symbol, double price, long volume, long marketCap) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * Returns the symbol of the stock.
     *
     * @return The symbol of the stock
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol of the stock.
     *
     * @param symbol The new symbol of the stock
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the price of the stock.
     *
     * @return The price of the stock
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the stock.
     *
     * @param price The new price of the stock
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the volume of the stock.
     *
     * @return The volume of the stock
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Sets the volume of the stock.
     *
     * @param volume The new volume of the stock
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Returns the market capitalization of the stock.
     *
     * @return The market capitalization of the stock
     */
    public long getMarketCap() {
        return marketCap;
    }

    /**
     * Sets the market capitalization of the stock.
     *
     * @param marketCap The new market capitalization of the stock
     */
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    /**
     * Returns a string representation of the stock.
     *
     * @return A string representation of the stock
     */
    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", marketCap=" + marketCap +
                '}';
    }
}
