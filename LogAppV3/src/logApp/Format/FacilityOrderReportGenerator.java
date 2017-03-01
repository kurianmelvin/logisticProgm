package logApp.Format;

import java.util.ArrayList;

import logApp.Facility.Connections.FacilityInformationErrorException;
import logApp.Facility.Connections.DirectLinksManager;
import logApp.Facility.Facility.FacilityManager;
import logApp.ItemCatalog.ItemManager;



public class FacilityOrderReportGenerator {
	
	public static void generateAll(FacilityManager FacilManager, DirectLinksManager dirLinksManager,ItemManager itmManager)
	{
		ArrayList<String> facilityNames = new ArrayList<String>();
		facilityNames=FacilManager.getFacilities();
		for(String s:facilityNames)
		{
			
			System.out.println("-------------------------");
			System.out.println(s);
			try
			{
		
			System.out.println("Direct Links: \n");
			System.out.println("Links: "+dirLinksManager.getLinks(s));
			}
			catch(FacilityInformationErrorException e)
			{
				System.out.println("No Links found.");
			}
			System.out.println("");
			System.out.println("Inventory:");
			ArrayList<String> depleted = new ArrayList<String>();
			for(String item:itmManager.getItemList())
			{
				if(FacilManager.FacilityItemsExist(s, item))
				{
					System.out.println(item+" : "+FacilManager.numItems(s, item));
				}
				
				else if(FacilManager.depletedItem(s, item))
					depleted.add(item);
				
					
					
			}
			System.out.println("");
			System.out.print("Depleted Items: ");
			for(String item:depleted)
			{
				System.out.print(" "+item+",");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Schedule:");
			FacilManager.printScheduleRepresentation(s);
			
			System.out.println("-------------------------");
		}
	}

}
