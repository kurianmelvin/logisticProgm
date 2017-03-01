package logApp.Facility.Facility;

import java.util.HashMap;

public class InventoryImpl implements Inventory{

	
	private HashMap<String,Integer> inventory;
	
	public InventoryImpl()
	{
		inventory = new HashMap<String,Integer>();
	}
	
	
	@Override
	public boolean FacilityItemsExist(String ItemCatalogId) {
		return (inventory.containsKey(ItemCatalogId));
	}

	@Override
	public int numItems(String ItemCatalogId) {
		
		if(FacilityItemsExist(ItemCatalogId))
			return inventory.get(ItemCatalogId);
		else return 0;
	}

	@Override
	public boolean addInventory(String ItemCatalogId, int quantity) {
		
		inventory.put(ItemCatalogId,quantity);
		return true;
	}

	@Override
	public boolean removeInventory(String ItemCatalogId, int quantity) throws InventoryNegativeException{
		
		int current = inventory.get(ItemCatalogId);
		int after = current - quantity;
		if(after>=0)
		{
			inventory.put(ItemCatalogId, after);
			return true;
		}
		
		throw new InventoryNegativeException(ItemCatalogId);
	}
	
	

}
