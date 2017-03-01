package logApp.OrderRequest;

import java.util.ArrayList;

public class OrderFactory {
	
	public static Order generateOrder(String id, int day, String destination, ArrayList<ItemQuantity> loadInfo)
	{
		return new OrderImpl(id,day,destination,loadInfo);
	}

}
