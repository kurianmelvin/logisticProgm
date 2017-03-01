package logApp.Facility.Facility;

import logApp.Format.NegativeInventoryException;

public interface Facility {

	public String getFacilityCity();

	public int getCostPerDay();

	public int getRatePerDay();

	public boolean FacilityItemsExist(String ItemCatalogId);

	public boolean activeInventoryTracker(String ItemCatalogId);

	public int getFacilityItemList(String ItemCatalogId);

	public boolean addInventory(String ItemCatalogId, int quantity);

	public boolean removeInventory(String ItemCatalogId, int quantity) throws NegativeInventoryException;

	public int processRatePerDayCounter(int startingDay, int quantity);

	public boolean orderTime(int startingDay, int quantity);

	public void renderSchedule();

}
