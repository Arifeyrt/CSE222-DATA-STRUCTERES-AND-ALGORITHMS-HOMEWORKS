class CorporateCustomer extends Customer {
    private String companyName;

    public CorporateCustomer(String name, String surname, String address, String phone, int ID, int operatorID,
            String companyName) {
        super(name, surname, address, phone, ID, operatorID);
        this.companyName = companyName;
    }

    public void print_customer() {
        super.print_customer(); // First we call the print_customer method of the Customer class.
        System.out.println("Company Name: " + companyName); // write company name

    }

}