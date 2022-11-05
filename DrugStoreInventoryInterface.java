
public interface DrugStoreInventoryInterface 
{
	/**Gets information from multiple suppliers
	 @return information of suppler such as: Supplier Name, Supplier ID, Supplier Contact
	 			If supplier doesn't exist, method will return "No entry found" message to user*/
	public String[] supplier();
	
	/*
	 * Get Suppler Name
	 * Get Supplier ID
	 * Get Supplier Contact
	 * 
	 * return all available information
	 */
	
	/**Gets information for all products in inventory
	 @return information of products such as: Prudoct ID, Product Type, Price, Max Amount
	 			If product doesn't exist, method will return "No entry found" message to user*/
	public String[] product();
	
	/*
	 * Get Prudoct ID
	 * Get Product Type
	 * Get Price 
	 * Get Max Amount
	 * 
	 * return all available information
	 */
	
	/**inventory method branches from the product method.
	 Allows user to edit information of the products, allocate products
	 It will notify employees when products expire or low on stock*/
	public void inventory();
	
	/*
	 * Display information of inventory
	 * user edit/allocate product information
	 * 
	 * if (product stock < specified amount)
	 * 		notify for restock
	 * 
	 * if (current date == few days before product expiration date)
	 * 		notify to throw away product
	 */
	
	/**Gets customer prescription
	 @return prescription*/
	public String[] customerPrescription();
	
	/*
	 * Get prescription
	 * 
	 * return prescription
	 */
	
	/**Allows user to add products to inventory or prescriptions to customer
	 @param entry, product/prescription
	 @return true if add was successful, false if failed*/
	public boolean add(String entry);
	
	/**Allows user to remove products to inventory or prescriptions to customer
	 @param entry, product/prescription
	 @return true if removal was successful, false if failed*/
	public boolean remove(String entry);
}
