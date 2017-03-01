package logApp.Facility.Facility;

import java.util.ArrayList;

public class FacilityManager {
	private static volatile FacilityManager instance;

	private ArrayList<Facility> facilities;

	/**
	 * 
	 * private constructor to prevent client from instantiating.
	 */
	private FacilityManager() {

		// to prevent instantiating by Reflection call
		if (instance != null) {
			throw new IllegalStateException("Already initialized.");
		}
		facilities = FacilityLoader.gatherInput();

	}

	/**
	 * Public accessor.
	 *
	 * @return an instance of the class.
	 */
	public static FacilityManager getInstance() {

		FacilityManager result = instance;
		if (result == null) {
			synchronized (FacilityManager.class) {
				result = instance;
				if (result == null) {
					instance = result = new FacilityManager();
				}
			}
		}
		return result;
	}

	private Facility findByName(String name) {
		for (Facility f : facilities) {
			if (f.getFacilityCity().equalsIgnoreCase(name))
				return f;
		}
		return null;
	}

	public int getDailyProcessingCost(String facility) {
		Facility f = findByName(facility);
		return f.getCostPerDay();
	}

	public ArrayList<String> getFacilitiesWithItem(String ItemCatalogId) {
		ArrayList<String> info = new ArrayList<String>();

		for (Facility f : facilities) {
			if (f.FacilityItemsExist(ItemCatalogId)) {
				info.add(f.getFacilityCity());
			}
		}
		return info;
	}

	public int getProcessingTime(String facility, int quantity, int startingDay) {
		Facility f = findByName(facility);
		return f.processRatePerDayCounter(startingDay, quantity);
	}

	public ArrayList<String> getFacilities() {
		ArrayList<String> info = new ArrayList<String>();
		for (Facility f : facilities) {
			info.add(f.getFacilityCity());
		}
		return info;
	}

	public boolean FacilityItemsExist(String facility, String ItemCatalogId) {
		return findByName(facility).FacilityItemsExist(ItemCatalogId);
	}

	public boolean depletedItem(String facility, String ItemCatalogId) {
		return findByName(facility).activeInventoryTracker(ItemCatalogId);
	}

	public int numItems(String facility, String ItemCatalogId) {
		return findByName(facility).getFacilityItemList(ItemCatalogId);
	}

	public boolean addInventory(String facility, String ItemCatalogId, int quantity) {
		return findByName(facility).addInventory(ItemCatalogId, quantity);
	}

	public boolean removeInventory(String facility, String ItemCatalogId, int quantity)
			throws InventoryNegativeException, logApp.Format.NegativeInventoryException {
		return findByName(facility).removeInventory(ItemCatalogId, quantity);
	}

	public void printScheduleRepresentation(String facility) {
		Facility f = findByName(facility);
		f.renderSchedule();
	}

	public boolean orderTime(String facility, int quantity, int startingDay) {
		return findByName(facility).orderTime(startingDay, quantity);
	}
	public Facility getRate(){
		for (Facility f : facilities) {
			f.getRatePerDay();
				return f;
		}
		return null;
		
		
	}
	public Facility getCost(){
		for (Facility f : facilities) {
			f.getCostPerDay();
				return f;
		}
		return null;
		
		
	}

}
