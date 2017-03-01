package logApp.Format;

import java.util.ArrayList;

import logApp.Facility.Facility.FacilityManager;
import logApp.ItemCatalog.ItemManager;
import logApp.ItemCatalog.ActiveInventoryNotFoundException;

public class OrderRecord {

	private int itemCost;
	private String ItemCatalogId;
	private String orderId;
	private int quantity;
	private int cost;
	private int numSources;
	private int firstDay;
	private int lastDay;
	private String sourceCity;

	public OrderRecord(ArrayList<FacilityRecord> solutions, String orderId, String ItemCatalogId)
			throws ActiveInventoryNotFoundException {
		quantity = 0;
		firstDay = Integer.MAX_VALUE;
		lastDay = -1;
		cost = 0;
		numSources = 0;
		FacilityManager FacilManager = FacilityManager.getInstance();
		ItemManager itmManager = ItemManager.getInstance();
		itemCost = itmManager.getCostPerDay(ItemCatalogId);
		this.orderId = orderId;
		this.ItemCatalogId = ItemCatalogId;
		this.sourceCity = sourceCity;
		for (FacilityRecord fr : solutions) {
			numSources++;
			sourceCity = fr.getFacilityCity();
			quantity += fr.getNumOfItemsProcessed();
			cost += (itemCost * fr.getNumOfItemsProcessed());
			cost += (500 * fr.getTT());
			cost += (fr.getProcessingTime() * FacilManager.getDailyProcessingCost(fr.getFacilityCity()));
			if (fr.getAD() > lastDay)
				lastDay = fr.getAD();
			if (fr.getAD() < firstDay) {
				firstDay = fr.getAD();
			}
		}
	}

	public int getTotalCost() {
		return cost;
	}

	public int getQty() {
		return quantity;
	}

	public int getNumSources() {
		return numSources;
	}

	public int getFirstDay() {
		if (firstDay == Integer.MAX_VALUE)
			return 0;
		return firstDay;
	}

	public int getLastDay() {
		if (lastDay == -1)
			return 0;
		return lastDay;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getItemId() {
		return ItemCatalogId;
	}

	public String getFacilityCity() {
		return sourceCity;

	}
}
