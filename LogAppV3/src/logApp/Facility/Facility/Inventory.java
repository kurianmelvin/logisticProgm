package logApp.Facility.Facility;

public interface Inventory {

	public boolean FacilityItemsExist(String ItemCatalogId);

	public int numItems(String ItemCatalogId);

	public boolean addInventory(String ItemCatalogId, int quantity);

	public boolean removeInventory(String ItemCatalogId, int quantity) throws InventoryNegativeException;

}
