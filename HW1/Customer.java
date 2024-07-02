class Customer extends Person {
    private Order[] orders = new Order[100];
    private int operator_ID;

    public Customer(String name, String surname, String address, String phone, int ID, int operatorID) {
        super(name, surname, address, phone, ID);
        this.operator_ID = operatorID;
    }

    public void print_customer() {
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Operator ID: " + operator_ID);
    }

    public void define_orders(Order[] order) {
        int order_count = 0;
       
       // finding the order count
        for (int i = 0; i < order.length; i++) {
            // System.out.println("This"+ order[i]);
            if (order[i] != null) {
                order_count++;
            }
        }
        int a = 0;
        //To find customer orders
        for (int j = 0; j < order_count; j++) {
            if (order[j].getCustomer_ID() == ID) {
                this.orders[a] = order[j];
                a++;
            }
        }
    }
   int a=0;
    public void print_orders() {
        if (orders != null) {
            for (Order order : orders) {
                if (order != null) {
                    a=a+1;
                    System.out.print("Order #" + a + " => ");
                    order.print_order();

                }
            }
        } else {
            System.out.println("This operator doesn't have any customer. ");
        }
    }

    public int getOperator_ID() {
        return operator_ID;
    }

    public int getID() {
        return ID;
    }
}
