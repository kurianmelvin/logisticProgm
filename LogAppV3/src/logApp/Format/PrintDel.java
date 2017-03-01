package logApp.Format;

import java.util.ArrayList;
import java.util.HashMap;

import logApp.Facility.Connections.DirectLinksManager;
import logApp.OrderRequest.OrderManager;
import logApp.OrderRequest.OrderInformationInAccessibleException;




public class PrintDel {

	
	
	
	public static void genertateOutput(ArrayList<OrderRecord> solutions) throws OrderInformationInAccessibleException
	{
		DirectLinksManager dirLinksManager = DirectLinksManager.getInstance();
		OrderManager odrManager = OrderManager.getInstance();
		String orderId = solutions.get(0).getOrderId();
		
		System.out.println("------------------");
		  for(int i=1; i<orderId.length(); i++){
			  System.out.println("Order #"+i);
		 
		System.out.println("  Order ID: "+orderId);
		System.out.println("  Order Time: Day "+odrManager.getDay(orderId));
		System.out.println("  Destination: "+odrManager.getDestination(orderId));
		System.out.println("  \nList of Order Items:");
		
//		System.out.println("-----------------");
//		for(OrderItemLogisticsRecord oi: solutions)
//		{
//			System.out.println(oi.getFacilityCity());
//			System.out.println(oi.getNumSources());
//			System.out.println(oi.getItemId());
//			System.out.println(oi.getTotalCost()+oi.getTotalCost());
////			System.out.println(oi.);
//			
//		}
//		System.out.println("-----------------");
//		
		int totalCost=0;
		int firstDay=Integer.MAX_VALUE;
		int lastDay=Integer.MIN_VALUE;
		  
		HashMap<String, Integer> loadInfo = odrManager.getInfo(orderId);
		
		for(String oid:loadInfo.keySet())
		{
			System.out.println("    Item ID: \t"+oid+",  Quantity: "+loadInfo.get(oid));
		}
		
		for(OrderRecord s:solutions)
		{
			totalCost+=s.getTotalCost();
			if(s.getFirstDay()<firstDay)
				firstDay=s.getFirstDay();
			if(s.getLastDay()>lastDay)
				lastDay=s.getLastDay();
		}
		
		
		System.out.println("\n Processing Solution: \n");
		System.out.println("Order ID: "+orderId);
		System.out.println("Destination: "+odrManager.getDestination(orderId));
		System.out.println("Total Cost: $"+totalCost);

		System.out.println("Order Items:");
//		System.out.println("Item ID      quantity         Cost       Number of Sources    First Day       Last Day");
		for(OrderRecord s:solutions)
		{
		System.out.println(s.getItemId()+"       "+s.getQty()+"          "+s.getTotalCost()+"          "+s.getNumSources()+"                   "+s.getFirstDay()+"                 "+s.getLastDay());
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
	
	}
	}
	
	
	

}
