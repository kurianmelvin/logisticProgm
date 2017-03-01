package logApp.Facility.Main;

import logApp.Facility.Connections.FacilityInformationErrorException;
import logApp.Facility.Connections.DirectLinksManager;
import logApp.Facility.Facility.FacilityManager;
import logApp.Facility.Facility.InventoryNegativeException;
import logApp.Format.OrderProcessor;
import logApp.ItemCatalog.ItemManager;
import logApp.ItemCatalog.ActiveInventoryNotFoundException;
import logApp.OrderRequest.OrderManager;
import logApp.OrderRequest.OrderInformationInAccessibleException;

public class Main {
	
	public static void main(String[] args) throws InventoryNegativeException, Exception
	{
		ItemManager itmManager = ItemManager.getInstance();
		DirectLinksManager dirLinksManager = DirectLinksManager.getInstance();
		FacilityManager FacilManager = FacilityManager.getInstance();	
		OrderManager odrManager = OrderManager.getInstance();
		
		OrderProcessor op = new OrderProcessor(itmManager,dirLinksManager,FacilManager,odrManager);
		try {
			op.processOrders();
		} catch (OrderInformationInAccessibleException e) {
			e.printStackTrace();
		} catch (FacilityInformationErrorException e) {
			e.printStackTrace();
		} catch (ActiveInventoryNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
