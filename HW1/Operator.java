class Operator extends Person {
    private int wage;
    private Customer[] customers = new Customer[100];

    public Operator(String name, String surname, String address, String phone, int ID, int wage) {
        super(name, surname, address, phone, ID);
        this.wage = wage;
    }

    public void print_operator() {
        System.out.println("*** Operator Screen ***");
        System.out.println("--------------------------------------");
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Wage: " + wage);

    }

    public void define_customers(Customer[] customer) {

        int customer_count = 0;
        for (int i = 0; i < customer.length; i++) {
            if (customer[i] != null) {
                customer_count++;
            }
        }

        int a = 0;
        for (int j = 0; j < customer_count; j++) {
            if (customer[j].getOperator_ID() == ID) {
                this.customers[a] = customer[j];
                a++;
            }
        }

    }

    public void print_customers() {
        int a = 0;
        int customer_count = 0;
        for (int i = 0; i < 100; i++) {
            if (customers[i] != null) {
                customer_count++;
            }
        }
        for (int i = 0; i < customer_count; i++) {
            if (customers[i] != null) {
                if (customers[i] instanceof RetailCustomer) {
                    a = a + 1;
                    System.out.println("---------------------------------");
                    System.out.println("Customer #" + a + "(a retail customer):");
                    customers[i].print_customer();
                    customers[i].print_orders();
                }
                if (customers[i] instanceof CorporateCustomer) {
                    a = a + 1;
                    System.out.println("---------------------------------");
                    System.out.println("Customer #" + a + " (a corporator customer):");
                    customers[i].print_customer();
                    customers[i].print_orders();
                }
            }
        }
       if(customer_count==0) {
            System.out.println("---------------------------------------");
            System.out.println("This operator doesn't have any customer. ");
            System.out.println("---------------------------------------");
        }

        

    }

    public int getID() {
        return ID;
    }

}