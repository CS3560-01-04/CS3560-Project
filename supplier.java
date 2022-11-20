public class Supplier {

    private int SupplierID;
    private String SupplierName;
    private String EmailAdd;
    private int PhoneNum;

    public Supplier() {
        this.SupplierID = 0;
        this.SupplierName = "";
        this.EmailAdd = "";
        this.PhoneNum = 0;
    }

    public Supplier(int SupplierID, String SupplierName, String EmailAdd, int PhoneNum) {
        this.SupplierID = SupplierID;
        this.SupplierName = SupplierName;
        this.EmailAdd = EmailAdd;
        this.PhoneNum = PhoneNum;
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

    public String getEmailAdd() {
        return EmailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        EmailAdd = emailAdd;
    }

    public int getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        PhoneNum = phoneNum;
    }

}