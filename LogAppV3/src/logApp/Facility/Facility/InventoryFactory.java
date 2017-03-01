package logApp.Facility.Facility;

public class InventoryFactory {
	
	public static Inventory makeInventory(String identifier)
	{
		return new InventoryImpl();
	}

}
