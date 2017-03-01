package logApp.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import logApp.Facility.Connections.FacilityInformationErrorException;
import logApp.Facility.Connections.DirectLinksManager;
import logApp.Facility.Facility.FacilityManager;
import logApp.Facility.Facility.InventoryNegativeException;
import logApp.ItemCatalog.ItemManager;
import logApp.ItemCatalog.ActiveInventoryNotFoundException;
import logApp.OrderRequest.OrderManager;
import logApp.OrderRequest.OrderInformationInAccessibleException;
import logApp.Facility.*;

public class OrderProcessor {

	private ItemManager itmManager;
	private DirectLinksManager dirLinksManager;
	private FacilityManager FacilManager;
	private OrderManager odrManager;
	private ArrayList<String> orders;
	private ArrayList<String> itemList;

	public OrderProcessor(ItemManager itmManager, DirectLinksManager dirLinksManager, FacilityManager FacilManager, OrderManager odrManager) {
		this.itmManager = itmManager;
		this.dirLinksManager = dirLinksManager;
		this.FacilManager = FacilManager;
		this.odrManager = odrManager;
		orders = odrManager.getOrderIds();
		itemList = itmManager.getItemList();

	}

	public void processOrders() throws InventoryNegativeException, Exception {

		for (String o : orders) {
			processOrder(o);
		}

		FacilityOrderReportGenerator.generateAll(FacilManager, dirLinksManager, itmManager);

	}

	private void processOrder(String orderId) throws InventoryNegativeException, Exception {

		HashMap<String, Integer> loadInfo = odrManager.getInfo(orderId);
		ArrayList<OrderRecord> solutions = new ArrayList<OrderRecord>();

		for (String i : itemList) {
			if (loadInfo.containsKey(i)) {
				solutions.add(processOrderItem(orderId, i, loadInfo));
			}
		}

		PrintDel.genertateOutput(solutions);
	}

	private OrderRecord processOrderItem(String orderId, String ItemCatalogId, HashMap<String, Integer> loadInfo)
			throws InventoryNegativeException, Exception {
		ArrayList<FacilityRecord> facilityRecords = new ArrayList<FacilityRecord>();

		ArrayList<String> facilitiesWithItem = FacilManager.getFacilitiesWithItem(ItemCatalogId);

		for (String facility : facilitiesWithItem) {
			int travelTime = Integer.MAX_VALUE;
			travelTime = (int) Math.ceil(dirLinksManager.shortestPathDays(facility, odrManager.getDestination(orderId), 8, 50));
			int processingTime = FacilManager.getProcessingTime(facility,
					Math.min(FacilManager.numItems(facility, ItemCatalogId), loadInfo.get(ItemCatalogId)), odrManager.getDay(orderId));

			facilityRecords.add(new FacilityRecord(facility,
					Math.min(FacilManager.numItems(facility, ItemCatalogId), loadInfo.get(ItemCatalogId)), odrManager.getDay(orderId),
					processingTime, travelTime));
		}

		Collections.sort(facilityRecords);
		ArrayList<FacilityRecord> solution = new ArrayList<FacilityRecord>();
		int qtyNeeded = loadInfo.get(ItemCatalogId);
		while (qtyNeeded > 0) {
			if (facilityRecords.isEmpty()) {
				break;
			}

			FacilityRecord current = facilityRecords.remove(0);

			if ((qtyNeeded - current.getNumOfItemsAvailible()) <= 0) {

				try {
					FacilManager.removeInventory(current.getFacilityCity(), ItemCatalogId, qtyNeeded);
					current.processItems(qtyNeeded);
				} catch (logApp.Format.NegativeInventoryException e) {
					e.printStackTrace();
				}

				FacilManager.orderTime(current.getFacilityCity(), qtyNeeded, odrManager.getDay(orderId));
				solution.add(current);
				qtyNeeded = 0;
			} else {

				try {
					FacilManager.removeInventory(current.getFacilityCity(), ItemCatalogId, current.getNumOfItemsAvailible());
					current.processItems(current.getNumOfItemsAvailible());
				} catch (logApp.Format.NegativeInventoryException e) {
					e.printStackTrace();
				}

				FacilManager.orderTime(current.getFacilityCity(), current.getNumOfItemsAvailible(), odrManager.getDay(orderId));
				solution.add(current);
				qtyNeeded -= current.getNumOfItemsAvailible();
			}

		}

		return new OrderRecord(solution, orderId, ItemCatalogId);

	}

}
