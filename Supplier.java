package GUI;

public class Supplier {

    private int SupplierID;
    private String SupplierName;
    private String merchandise;
    private int Quantity;
    private int Price;

    public Supplier() {
        this.SupplierID = 0;
        this.SupplierName = ""; 
        this.merchandise = "";
        this.Quantity = 0;
        this.Price = 0;
    }

    public Supplier(int SupplierID, String SupplierName, String merchandise, int Quantity, 
            int Price) {
        this.SupplierID = SupplierID;
        this.SupplierName = SupplierName;
        this.merchandise = merchandise;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(String merchandise) {
        this.merchandise = merchandise;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

}