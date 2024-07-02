class Order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public Order(String productName, int count, int totalPrice, int status, int customerID) {
        this.product_name = productName;
        this.count = count;
        this.total_price = totalPrice;
        this.status = status;
        this.customer_ID = customerID;

    }

    public void print_order() {
        String corresponding_status = "";
      

        switch (status) {
            case 0:
                corresponding_status = "Initialized";
                break;
            case 1:
                corresponding_status = "Processing ";
                break;
            case 2:
                corresponding_status = "Completed ";
                break;
            case 3:
                corresponding_status = "Cancelled ";
                break;
            default:
                corresponding_status = "Unknown";
        }
      
        System.out.println("Product name: " + product_name + " - Count: " + count
                + " - Total price: " + total_price + " - Status: " + corresponding_status + ".");

    }

    public int getCustomer_ID() {
        return customer_ID;
    }

}
