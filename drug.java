public class Drug {

    // declare the following attributes for the Drug class
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String supplier;
    private int expyear;
    private int expmonth;
    private int expday;

    // create default constructors for the following attributes
    // default values
    public Drug() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.quantity = 0;
        this.price = 0;
        this.supplier = "";
        this.expyear = 0;
        this.expmonth = 0;
        this.expday = 0;

    }

    // take the listed parameters and load into one constructer
    public Drug(String id, String name, String description, int quantity, double price, String supplier, int expyear,
            int expmonth, int expday) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
        this.expyear = expyear;
        this.expmonth = expmonth;
        this.expday = expday;
    }

    // get and set all parameters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getExpyear() {
        return expyear;
    }

    public void setExpyear(int expyear) {
        this.expyear = expyear;
    }

    public int getExpmonth() {
        return expmonth;
    }

    public void setExpmonth(int expmonth) {
        this.expmonth = expmonth;
    }

    public int getExpday() {
        return expday;
    }

    public void setExpday(int expday) {
        this.expday = expday;
    }

}