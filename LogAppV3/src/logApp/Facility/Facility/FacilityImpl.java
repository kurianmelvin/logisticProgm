package logApp.Facility.Facility;

public class FacilityImpl implements Facility{
	
	private String name;
	private int cost;
	private int processingRate;
	private Inventory inventory;
	private Schedule schedule;
	
	public FacilityImpl(String name, int cost, int processingRate)
	{
		this.name=name.trim();
		this.cost=cost;
		this.processingRate=processingRate;
		inventory=InventoryFactory.makeInventory(null);
		schedule=ScheduleFactory.makeSchedule(null, processingRate);
	}
	
	
	public String getFacilityCity()
	{
		return name;
	}
	
	public int getCostPerDay()
	{
		return cost;
	}
	
	public int getRatePerDay()
	{
		return processingRate;
	}
	
	public boolean FacilityItemsExist(String ItemCatalogId)
	{
		return (inventory.FacilityItemsExist(ItemCatalogId))&&inventory.numItems(ItemCatalogId)>0;
	}
	
	
	public int getFacilityItemList(String ItemCatalogId)
	{
		return inventory.numItems(ItemCatalogId);
	}
	
	
	public boolean addInventory(String ItemCatalogId, int quantity)
	{
		return inventory.addInventory(ItemCatalogId, quantity);
	}
	
	
	
	public boolean removeInventory(String ItemCatalogId, int quantity) 
	{
		try {
			return inventory.removeInventory(ItemCatalogId, quantity);
		} catch (InventoryNegativeException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public boolean activeInventoryTracker(String ItemCatalogId)
	{
		return (inventory.FacilityItemsExist(ItemCatalogId))&&inventory.numItems(ItemCatalogId)==0;
	}
	
	public int processRatePerDayCounter(int startingDay,int quantity)
	{
		return schedule.processRatePerDayCounter(startingDay, quantity);
	}
	
	public boolean orderTime(int startingDay,int quantity)
	{
		return schedule.orderTime(startingDay, quantity);
	}
	public void renderSchedule()
	{
		schedule.print();
	}

}
